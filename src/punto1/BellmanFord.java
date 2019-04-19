package punto1;

public class BellmanFord implements CostosMinimosAlgorithm {

	
	int[] paths = null;
	public void calculateAllShortPaths(Graph G) {
		//para cada uno de los ejes
		for (int a = 0; a < G.getWeights().length; a++) {
			
			if (paths ==null) {
				paths = new int[G.size()];
				//se rellena el origen de como 0 y el resto como "infinito"
				paths[a]= 0;
				for (int b = 1; b < paths.length; b++) {
					paths[b]=Integer.MAX_VALUE;
				}
			}
			
			//relajacion de de ejes
			for (int c = a; c < G.getWeights().length; c++) {
				for (int d = 0; d < G.getWeights().length; d++) {
					if((G.getWeights()[c][d]!=0)&&(G.getWeights()[c][d]!=-1)&&(paths[c]+G.getWeights()[c][d]<paths[d])) {
						paths[d]=paths[c]+G.getWeights()[c][d];
					}
				}
			}
			
			
		
		}

	}

}
