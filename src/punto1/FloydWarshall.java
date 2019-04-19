package punto1;

public class FloydWarshall implements CostosMinimosAlgorithm {

	int[][] m;
	int[][] imd;

	@Override
	public void calculateAllShortPaths(Graph G) {
		// m(i,j) son los caminos directos, es decir que k = 0
		m = G.getWeights();		
		int n = G.size();

		// inicializar intermedios con 0 si hay camino directo y -1 si no lo hay
		imd = new int[n][n];
		for( int i = 0 ; i < n ; i++ ) {
			for( int j = 0 ; j < n ; j++ ) {
				//
				if ( m[i][j] == -1 ) {
					imd[i][j] = -1;
				} else {
					imd[i][j] = 0;
				}
			}
		}


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

}
