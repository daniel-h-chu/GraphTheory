

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 20;
		Graph g = new Graph(x);
		for(int i = 0; i < 200 ; i++) {
			g.addWeightedEdge((int)(Math.random()*x), (int)(Math.random()*x), (int)(Math.random()*x));
			int[] d = g.BFS(0);
			for(int ds: d) {
				System.out.print(ds + " ");
			}
			System.out.println();
		}
		g.printMatrix();
/*		g.addWeightedEdge(0, 4, 3);
		g.addWeightedEdge(2, 3, 4);
		g.addWeightedEdge(2, 5, 5);
		g.addWeightedEdge(1, 6, 6);
		g.addWeightedEdge(3, 4, 7);
		g.addWeightedEdge(5, 7, 3);
		g.addWeightedEdge(2, 6, 4);
		g.addWeightedEdge(0, 1, 5);
		g.addWeightedEdge(4, 7, 6);
		g.addWeightedEdge(6, 8, 7);
		g.addWeightedEdge(3, 7, 3);
		g.addWeightedEdge(5, 9, 4);
		g.addWeightedEdge(8, 9, 5);
		g.addWeightedEdge(5, 10, 6);
		g.addWeightedEdge(9, 10, 7);
		g.printMatrix();
		int[] d = g.Dijkstras(0);
		for(int ds: d) {
			System.out.print(ds + " ");
		}*/
	}
}
