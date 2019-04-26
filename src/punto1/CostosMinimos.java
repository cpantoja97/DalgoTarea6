package punto1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CostosMinimos {

	/**
	 * Main method for the coin change example. It requires three parameters:
	 * args[0]: Algorithm to run. It can be Dijkstra, BellmanFord y FloydWarschall
	 * args[1]: Matrix size (number of vertices in graph)
	 * args[2]: Input file with weights of directed graph. It must be a text file containing n lines of n integers separated by spaces. -1 weight means there's no edge.
	 * @param args Array with the arguments described above
	 */
	public static void main(String[] args) throws Exception {

		//Load algorithm class
		String algorithmClassName = CostosMinimosAlgorithm.class.getPackage().getName()+"."+args[0];

		//Load input data
		int tamanioMatriz = Integer.parseInt(args[1]);
		String archivoMatriz = args[2];
		int[][] costosGrafo = loadGraph( tamanioMatriz, archivoMatriz );
		
		Graph grafo = new Graph( costosGrafo );

		CostosMinimosAlgorithm calculator = (CostosMinimosAlgorithm)Class.forName(algorithmClassName).newInstance();
		

		long startTime = System.currentTimeMillis();
		
		calculator.calculateAllShortPaths( grafo );
		
		long endTime = System.currentTimeMillis();

		//Output results
		
		System.out.println("Total time spent (milliseconds): "+(endTime-startTime));
		

		
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
				String[] numString = line.split("\t");
				
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
