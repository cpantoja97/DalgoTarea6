package punto1;

public class Dijkstra implements CostosMinimosAlgorithm {

	private double[] dist;
	private int[] pred;
	private int s;
	private minPQ pq;

	@Override
	public void calculateAllShortPaths( Graph G ) {
		
	}
	
	public void calculateCostsFrom( int source, Graph G) {
		dist = new double[G.size()];
		pred = new int[G.size()];
		
		s = source;

		inicializar(G);
		// Crear minPQ que es un binary heap
		minPQ pq = new minPQ( G.size() );
		for( int i = 0 ; i < G.size() ; i++) {
			pq.insert(i);
		}

		while( !pq.isEmpty() ) {
			int u = pq.extractMin();
			for( int v : G.adj(u) ) {
				relajar( u, v, G.w(u, v) );
			}
		}

	}

	public void inicializar( Graph G ) {
		for(int i = 0 ; i < G.size() ; i ++) {
			dist[i] = Double.POSITIVE_INFINITY;
			pred[i] = -1;			
		}
		dist[s] = 0;
	}

	public void relajar( int u, int v, int w) {
		if( dist[v] > dist[u] + w ) {
			dist[v] = dist[u] + w;
			pred[v] = u;

			pq.decreaseKey( v );
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

		public void insert( int k ) {
			N++;
			pos[k] = N;
			queue[N] = k;
			swim(N);
		}

		public int extractMin() {
			int min = queue[1];
			change(1, N--);
			sink(1);

			//Borrar del queue y borrar la posición
			pos[min] = -1;
			queue[N+1] = -1;

			return min;
		}

		public void decreaseKey(int k) {
			swim(pos[k]);
		}

		public void swim(int k) {
			while (k > 1 && mayor(k/2, k)) {
				change(k, k/2);
				k = k/2;
			}
		}
		public void sink( int k ) {
			while (2*k <= N) {
				int j = 2*k;
				//Escoge el menor de los hijos
				if ( j < N && mayor(j, j+1) ) {
					j++;	            
				}
				if (!mayor(k, j)) {
					break;
				}
				change(k, j);
				k = j;
			}
		}

		private void change( int i, int j ) {
			int temp = queue[i];
			queue[i] = queue[j];
			queue[j] = temp;

			pos[queue[i]] = i;
			pos[queue[j]] = j;
		}

		private boolean mayor( int i , int j ) {
			return dist[ queue[i] ] - dist[ queue[j] ] > 0 ;
		}
	}
}
