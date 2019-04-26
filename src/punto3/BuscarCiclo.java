package punto3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class BuscarCiclo {

	/**
	 * Main method for the coin change example. It requires three parameters:
	 * args[0]: Matrix size (number of vertices in graph)
	 * args[1]: Input file with weights of directed graph. It must be a text file containing n lines of n integers separated by spaces. -1 weight means there's no edge.
	 * @param args Array with the arguments described above
	 */
	public static void main(String[] args) throws Exception {

		//Load input data
		int tamanioMatriz = Integer.parseInt(args[0]);
		String archivoMatriz = args[1];
		int[][] matrizAdyacencia = loadGraph( tamanioMatriz, archivoMatriz );


		Graph grafo = new Graph( matrizAdyacencia );	
		
		


		DFS dfs = new DFS( );
		dfs.verificarCiclos(grafo);



	}

	public static int[][] loadGraph( int n, String file ) {
		int[][] grafo = new int[n][n];

		FileReader reader = null;
		BufferedReader in = null;
		try {
			reader = new FileReader( file );
			in = new BufferedReader(reader);

			String line = in.readLine();

			for( int i = 0; line != null; i++ ) {
				String[] numString = line.split(" ");

				for( int j = 0; j < numString.length ; j++ ) {
					int costo = Integer.parseInt( numString[j] );
					grafo[i][j] = costo;
				}

				line = in.readLine();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		} finally {
			try {
				in.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return grafo;
	}
}
