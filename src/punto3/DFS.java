package punto3;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {
	private int actual;
	
	//par hace mas facil manejar coordenadas
	private Stack<Par> stack = new Stack<Par>();
	private String recorridoCom = "";
	private String recorridoCiclo = "";
	

	
	public void verificarCiclos(Graph grafo){
		int grafoA[][] = grafo.getWeights();
		int dimension = grafo.getWeights().length;
		int recorridos[][] = new int[dimension+1][dimension+1];
				//A indica el node de inicio, si se consigue que se puede devolver a a en el recorrido, retorna true
		int a = 0, b = 0;
				while (a < grafoA.length) {
					while (b < grafoA.length) {
						//marca el recorido y lo mete al stack
						recorridos[a][b] = 1;
						recorridoCom += a+","+b+"->";
						recorridoCiclo += a+","+b+"->";

				
						
						//si la lista de adjacentes contiene el actual, retorna por lo que si tiene el loop
						if (grafo.adjacentes(a).contains(actual)) { 
							System.out.println("tiene ciclo "+recorridoCiclo);
							return;
								}
						
						else{			
									
									//busca en los adjacentes, los no recorridos
									for (int c = 0; c <= grafo.adjacentes(a).size(); c++) {
										
										if(recorridos[a][grafo.adjacentes(a).get(c)]==0) {
								
											b=grafo.adjacentes(a).get(c);
											break;
											//si todo los adjacentes estan recorridos, saca un par del stack y va a ese
										}else if(c== grafo.adjacentes(a).size()) {
							
											Par pop = stack.pop();
											//si el stack no es nulo, usa las coordenedas
											//si no, significa que ya recorrio todo el grafo y no se pudo llega a el nodo con en que comenzo
											//entonces cambia el actual si no ha llegado al final de los nodos y si no, retorna el recorrido completo
											if(pop!=null) {
											
												a= pop.getX();
												b= pop.getY();
												break;
												
											}else {
												if(actual<grafo.getWeights().length) {
													actual++;
													//reinicia para el nuev o nodo
													recorridoCiclo="";
													//recorridos = new int[dimension][dimension];
													break;
												}else {
													System.out.println("no tiene ciclos "+recorridoCom);
													return;
												}
											}

											
													
										}
									}
								}
						
						
					
					}
					
				}
	}
}