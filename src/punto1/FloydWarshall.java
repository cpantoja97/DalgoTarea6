package punto1;

import java.util.ArrayList;

public class FloydWarshall implements CostosMinimosAlgorithm {

	double[][] m;
	int[][] imd;

	@Override
	public void calculateAllShortPaths(Graph G) {
		// m(i,j) son los caminos directos, es decir que k = 0
		//m = G.getWeights();	

		int n = G.size();
		m = new double[n][n];
		
		// inicializar intermedios con 0 si hay camino directo y -1 si no lo hay
		imd = new int[n][n];
		for( int i = 0 ; i < n ; i++ ) {
			for( int j = 0 ; j < n ; j++ ) {
				//
				if ( G.w(i, j) == -1 ) {
					m[i][j] = Double.POSITIVE_INFINITY;
					imd[i][j] = -1;
				} else {
					m[i][j] =  G.w(i, j);
					imd[i][j] = 0;
				}
			}
		}

		//printIMD();

		// Empezando entonces desde k = 1
		for(int k = 1 ; k < n ; k++ ) {
			for( int i = 0 ; i < n ; i++ ) {
				for( int j = 0 ; j < n ; j++ ) {
					// Actualiza el costo y el intermedio
					if ( m[i][j] > m[i][k] + m[k][j] ) {
						m[i][j] = m[i][k] + m[k][j];
						imd[i][j] = k;
					}
				}
			}
		}
	}

	@Override
	public void imprimirCostos() {
		for( int s = 0 ; s < m.length ; s++) {
			for( int to = 0; to < m.length ; to++ ) {
				System.out.print( m[s][to] + " " );
			}
			System.out.println();
		}
	}

	@Override
	public void imprimirPath(int s, int v) {
		System.out.println("Costo: " + m[s][v]);
		ArrayList<Integer> pathInv = new ArrayList<Integer>();
		int act = v;
		while( act != s ) {
			pathInv.add( imd[s][act] );
			act = imd[s][act];
		}

		for( int i = pathInv.size()-1 ; i >= 0 ; i-- ) {
			System.out.print( pathInv.get(i) +" --> " );
		}
		System.out.println(v);
	}

	@SuppressWarnings("unused")
	private void printIMD() {
		for(int i = 0; i < imd.length ; i++) {
			for(int j = 0 ; j < imd.length ; j++) {
				System.out.print( imd[i][j] + " ");
			}
			System.out.println();
		}
	}

}
