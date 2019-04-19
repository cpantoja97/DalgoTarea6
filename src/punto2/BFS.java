package punto2;

import java.util.ArrayList;

public class BFS {

	boolean[] marked;
	int[] numComponente;
	int count;
	
	public BFS( GraphUndirected G ) {
		marked = new boolean[ G.size() ];
		numComponente = new int[ G.size() ];
		count = 0;
		
		for( int source = 0; source < G.size() ; source++ ) {
			if ( !marked[source] ) {
				bfsSearch( source, G );
				count++;
			}
		}
		
	}
	
	public void bfsSearch( int s, GraphUndirected G) {
		Queue queue = new Queue();
		queue.enqueue(s);
		
		marked[s] = true;
		
		while( !queue.isEmpty() ) {
			
			int u = queue.dequeue();
			for( int v : G.adj(u) ) {
				if( !marked[v] ) {
					marked[v] = true;
					numComponente[v] = count;
					queue.enqueue( v );
				}
			}
			
		}
	}
	
	public ArrayList<Integer>[] darComponentes(){
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] componentes = new ArrayList[count];
		for( int i = 0 ; i < count ; i++) {
			componentes[i] = new ArrayList<Integer>();
		}
		for( int i = 0 ; i < numComponente.length ; i++) {
			componentes[ numComponente[i] ].add(i);
		}
		return componentes;
	}
	
	private class Queue {
		Nodo primero;
		Nodo ultimo;
		int n;
		
		public boolean isEmpty() {
			return n == 0;
		}
		
		public void enqueue( int k ) {
			Nodo nuevo = new Nodo();
			nuevo.item = k;
			if(n==0) {
				primero = nuevo;
				ultimo = nuevo;
			} else {
				ultimo.sig = nuevo;
				ultimo = nuevo;
			}
			n++;
		}
		
		public int dequeue() {
			int temp = primero.item;
			primero = primero.sig;		
			if( n == 0 ) {
				ultimo = null;
			}
			n--;
			return temp;
		}
		
		private class Nodo{
			int item;
			Nodo sig;
		}
		
	}
	
}
