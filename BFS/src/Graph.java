public class Graph {
	public int[][] g;
	public int nodes;
	public Graph() {
		g = new int[0][0];
		nodes = 0;
	}
	public Graph(int size) {
		g = new int[size][size];
		nodes = size;
	}
	public void addNode() {
		nodes++;
		int[][] temp = new int[nodes][nodes];
		for(int i = 0 ; i < nodes - 1 ; i++) {
			for(int j = 0 ; j < nodes - 1 ; j++) {
				temp[i][j] = g[i][j];
			}
		}
		g = temp;
	}
	public void addEdge(int a, int b) {
		if(a >= nodes || b >= nodes || a < 0 || b < 0 || a == b) {
			System.out.println("Invalid Edge");
			return;
		}
		g[a][b] = 1;
		g[b][a] = 1;
	}
	public void addDirectedEdge(int a, int b) {
		if(a >= nodes || b >= nodes || a < 0 || b < 0 || a == b) {
			System.out.println("Invalid Edge");
			return;
		}
		g[a][b] = 1; 
	}
	public void addWeightedEdge(int a, int b, int w) {
		if(a >= nodes || b >= nodes || a < 0 || b < 0 || a == b) {
			System.out.println("Invalid Edge");
			return;
		}
		g[a][b] = w;
		g[b][a] = w;
	}
	public void removeEdge(int a, int b) {
		if(a >= nodes || b >= nodes || a < 0 || b < 0 || a == b) {
			System.out.println("Invalid Edge");
			return;
		}
		g[a][b] = 0;
		g[b][a] = 0;
	}
	public void removeNode(int a) {
		if(a >= nodes || a < 0) {
			System.out.println("Invalid Node");
			return;
		}
		nodes--;
		int[][] temp = new int[nodes][nodes];
		int row = 0;
		boolean remrow = false;
		for(int i = 0 ; i < nodes - 1 ; i++) {
			int col = 0;
			boolean remcol = false;
			if(row != a || remrow) {
				for(int j = 0 ; j < nodes - 1 ; j++) {
					if(col != a || remcol) {
						temp[row][col] = g[row][col];
						col++;
					}
					else {
						remcol = true;
					}
				}
				row++;
			}
			else {
				remrow = true;
			}
		}
		g = temp;
	}
	void printGraph() {
		for(int i = 0 ; i < nodes ; i++) {
			System.out.print(i + ": ");
			for(int j = 0 ; j < nodes ; j++) {
				if(g[i][j] != 0) {
					System.out.print(j + " ");
				}
			}
			System.out.println();
		}
	}
	void printMatrix() {
		for(int i = 0 ; i < nodes ; i++) {
			if(i == 0) {
				System.out.print("\t");
				for(int j = 0 ; j < nodes ; j++) {
					System.out.print(j + "\t");
				}
				System.out.println();
			}
			System.out.print(i + "\t");
			for(int j = 0 ; j < nodes ; j++) {
					System.out.print(g[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
