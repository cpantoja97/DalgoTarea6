package punto2;

import java.util.ArrayList;

public class GraphUndirected {

	private ArrayList<Integer>[] adjacents;
	private int[][] w;
	private int size;


	@SuppressWarnings("unchecked")
	public GraphUndirected( int[][] costos  ) {
		w = costos;
		size = costos.length;
		adjacents = new ArrayList[ costos.length ];

		for( int i = 0 ; i < costos.length ; i++ ) {
			adjacents[i] = new ArrayList<Integer>();
			
			for( int j = 0 ; j < costos.length ; j++ ) {
				if( w[i][j] != -1 ) {
					adjacents[i].add(j);
				}
			}
		}
	}
	
	public int size() {
		return size;
	}
	
	public Iterable<Integer> adj( int u ) {
		return adjacents[u];
	}

	public int w( int u, int v ) {
		return w[u][v];
	}
	
	public int[][] getWeights(){
		return w;
	}

	class Edge{

	}
}
