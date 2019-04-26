package punto3;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Integer>[] adjacents;
	private int[][] w;
	private int size;


	@SuppressWarnings("unchecked")
	public Graph( int[][] costos  ) {
		w = costos;
		size = costos.length;
		adjacents = new ArrayList[ costos.length ];

		for( int i = 0 ; i < costos.length ; i++ ) {
			adjacents[i] = new ArrayList<Integer>();

			for( int j = 0 ; j < costos.length ; j++ ) {
				if( w[i][j] != 0 && w[i][j] != -1 ) {
					adjacents[i].add(j);
				}
			}
		}
		
		
	}

	public int size() {
		return size;
	}

	public ArrayList<Integer> adjacentes( int u ) {
		return adjacents[u];
	}

	public int w( int u, int v ) {
		return w[u][v];
	}

	public int[][] getWeights(){
		return w;
	}
	
	@Override
	public String toString() {
		String str = "";
		int u = 0;
		for( ArrayList<Integer> adj : adjacents) {
			str += u + ":";
			for( int v : adj ) {
				str += " " + v;
			}
			str += "\n";
			u++;
		}
		return str;
	}
}
