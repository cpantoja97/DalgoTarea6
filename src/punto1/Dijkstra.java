package punto1;

import java.util.ArrayList;

public class Dijkstra implements CostosMinimosAlgorithm {

	private double[][] dist;
	private int[][] pred;
	private int s;
	private minPQ pq;

	@Override
	public void calculateAllShortPaths( Graph G ) {
		dist = new double[G.size()][G.size()];
		pred = new int[G.size()][G.size()];

		//Calculo costos desde cada source
		for( int k = 0 ; k < G.size() ; k++ ) {
			calculateCostsFrom( k, G );
		}

	}

	public void calculateCostsFrom( int source, Graph G) {
		// Asigno el parámetro como el source e inicializo las distancias y los predecesores
		s = source;
		inicializar(G);

		// Crear minPQ que es un binary heap
		pq = new minPQ( G.size() );
		for( int i = 0 ; i < G.size() ; i++) {
			pq.insert(i);
		}

		// Voy sacando del queue el de menor distancia hasta que haya atendido todos
		while( !pq.isEmpty() ) {
			int u = pq.extractMin();

			// Relajo cada vértice, es decir que voy escogiendo vértices que me minimicen los caminos
			for( int v : G.adj(u) ) {
				relajar( u, v, G.w(u, v) );
			}
		}

	}

	public void imprimirCostos() {
		for( int s = 0 ; s < dist.length ; s++) {
			for( int to = 0; to < dist.length ; to++ ) {
				System.out.print( dist[s][to] + " " );
			}
			System.out.println();
		}
	}

	@Override
	public void imprimirPath(int s, int v) {
		System.out.println("Costo: " + dist[s][v]);
		ArrayList<Integer> pathInv = new ArrayList<Integer>();
		int act = v;
		while( act != s ) {
			pathInv.add( pred[s][act] );
			act = pred[s][act];
		}
		
		for( int i = pathInv.size()-1 ; i >= 0 ; i-- ) {
			System.out.print( pathInv.get(i) +" --> " );
		}
		System.out.println(v);
	}

	private void inicializar( Graph G ) {
		for(int i = 0 ; i < G.size() ; i ++) {
			dist[s][i] = Double.POSITIVE_INFINITY;
			pred[s][i] = -1;			
		}
		dist[s][s] = 0;
	}

	private void relajar( int u, int v, int w) {	


		if( dist[s][v] > dist[s][u] + w ) {
			dist[s][v] = dist[s][u] + w;
			pred[s][v] = u;
			if (pq.contains( v )) { 
				pq.decreaseKey( v );
			} else {
				pq.insert( v );
			}
		}
	}

	private class minPQ{
		private int[] queue;
		private int[] pos;
		private int N = 0;

		public minPQ( int max ) {
			queue = new int[max+1];
			pos = new int[max];

			for( int i = 0 ; i < max; i++ ) {
				pos[i] = -1;
			}
		}

		public boolean isEmpty() {
			return N == 0;
		}

		public boolean contains( int k ) {
			return pos[k] != -1;
		}

		public void insert( int k ) {
			//Se inserta de último y se sube hasta su posición adecuada
			N++;
			pos[k] = N;
			queue[N] = k;
			swim(N);
		}

		public int extractMin() {
			//Se manda el primero para el final, para poder retirarlo fácilmente y se desciende el que se acaba de intercambiar
			int min = queue[1];
			intercambiar(1, N--);
			sink(1);

			//Borrar del queue y borrar la posición
			pos[min] = -1;
			queue[N+1] = -1;

			return min;
		}

		public void decreaseKey(int k) {
			//Como la prioridad disminuye, entonces debe ascender en el árbol
			swim(pos[k]);
		}

		private void swim(int k) {
			//Mientras que k sea menor que su padre, se intercambia con el mismo, es decir que asciende en el árbol
			while (k > 1 && esMayor(k/2, k)) {
				intercambiar(k, k/2);
				k = k/2;
			}

		}
		private void sink( int k ) {
			while (2*k <= N) {
				int j = 2*k;
				//Escoge el menor de los hijos
				if ( j < N && esMayor(j, j+1) ) {
					j++;	            
				}
				//Si k es menor al hijo menor entonces debe parar
				if (!esMayor(k, j)) {
					break;
				}
				//De lo contrario debe intercambiarlos, es decir, sinkea k una posición en el árbol
				intercambiar(k, j);
				k = j;
			}
		}

		private void intercambiar( int i, int j ) {
			int temp = queue[i];

			//Intercambiar en la cola
			queue[i] = queue[j];
			queue[j] = temp;

			//Intercambiar las posiciones
			pos[queue[i]] = i;
			pos[queue[j]] = j;
		}

		private boolean esMayor( int i , int j ) {
			//Criterio es la distancia
			return dist[s][ queue[i] ] - dist[s][ queue[j] ] > 0 ;
		}
	}
}
