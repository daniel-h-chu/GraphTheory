import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Graph {
	public int[][] edges;
	public int nodes;

	public Graph() {
		edges = new int[0][0];
		nodes = 0;
	}

	public Graph(int size) {
		edges = new int[size][size];
		nodes = size;
	}

	public void addNode() {
		nodes++;
		int[][] temp = new int[nodes][nodes];
		for (int i = 0; i < nodes - 1; i++) {
			for (int j = 0; j < nodes - 1; j++) {
				temp[i][j] = edges[i][j];
			}
		}
		edges = temp;
	}

	public void addEdge(int startNode, int endNode) {
		if (startNode >= nodes || endNode >= nodes || startNode < 0 || endNode < 0 || startNode == endNode) {
			System.out.println("Invalid Edge");
			return;
		}
		edges[startNode][endNode] = 1;
		edges[endNode][startNode] = 1;
	}

	public void addDirectedEdge(int startNode, int endNode) {
		if (startNode >= nodes || endNode >= nodes || startNode < 0 || endNode < 0 || startNode == endNode) {
			System.out.println("Invalid Edge");
			return;
		}
		edges[startNode][endNode] = 1;
	}

	public void addWeightedEdge(int startNode, int endNode, int weight) {
		if (startNode >= nodes || endNode >= nodes || startNode < 0 || endNode < 0 || startNode == endNode) {
			System.out.println("Invalid Edge");
			return;
		}
		edges[startNode][endNode] = weight;
		edges[endNode][startNode] = weight;
	}
	
	public void addWeightedDirectedEdge(int startNode, int endNode, int weight) {
		if (startNode >= nodes || endNode >= nodes || startNode < 0 || endNode < 0 || startNode == endNode) {
			System.out.println("Invalid Edge");
			return;
		}
		edges[startNode][endNode] = weight;
	}

	public void removeEdge(int startNode, int endNode) {
		if (startNode >= nodes || endNode >= nodes || startNode < 0 || endNode < 0 || startNode == endNode) {
			System.out.println("Invalid Edge");
			return;
		}
		edges[startNode][endNode] = 0;
		edges[endNode][startNode] = 0;
	}

	public void removeNode(int node) {
		if (node >= nodes || node < 0) {
			System.out.println("Invalid Node");
			return;
		}
		nodes--;
		int[][] temp = new int[nodes][nodes];
		int row = 0;
		boolean remrow = false;
		for (int i = 0; i < nodes - 1; i++) {
			int col = 0;
			boolean remcol = false;
			if (row != node || remrow) {
				for (int j = 0; j < nodes - 1; j++) {
					if (col != node || remcol) {
						temp[row][col] = edges[row][col];
						col++;
					} else {
						remcol = true;
					}
				}
				row++;
			} else {
				remrow = true;
			}
		}
		edges = temp;
	}

	void printGraph() {
		for (int i = 0; i < nodes; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < nodes; j++) {
				if (edges[i][j] != 0) {
					System.out.print(j + " ");
				}
			}
			System.out.println();
		}
	}

	void printMatrix() {
		for (int i = 0; i < nodes; i++) {
			if (i == 0) {
				System.out.print("\t");
				for (int j = 0; j < nodes; j++) {
					System.out.print(j + "\t");
				}
				System.out.println();
			}
			System.out.print(i + "\t");
			for (int j = 0; j < nodes; j++) {
				System.out.print(edges[i][j] + "\t");
			}
			System.out.println();
		}
	}

	int BFS(int startNode, int endNode) {
		if (startNode == endNode)
			return 0;
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		int[] distances = new int[nodes];
		q.addFirst(startNode);
		while (!q.isEmpty()) {
			int curNode = q.removeLast();
			for (int i = 0; i < nodes; i++) {
				if (edges[curNode][i] != 0 && distances[i] == 0) {
					distances[i] = distances[curNode] + 1;
					if (i == endNode)
						return distances[i];
					q.addFirst(i);
				}
			}
		}
		System.out.println("Nodes not connected");
		return -1;
	}

	int Dijkstras(int startNode, int endNode) {
		if (startNode == endNode)
			return 0;
		int[] distances = new int[nodes];
		for (int i = 0; i < nodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[startNode] = 0;
		class Comp implements Comparator<Integer> {
			public int compare(Integer o1, Integer o2) {
				return distances[o1] - distances[o2];
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(nodes, new Comp());
		pq.add(startNode);
		while (!pq.isEmpty()) {
			int curNode = pq.remove();
			for (int i = 0; i < nodes; i++) {
				if (edges[curNode][i] != 0) {
					int tempdist = distances[curNode] + edges[curNode][i];
					if (tempdist < distances[i]) {
						distances[i] = tempdist;
						pq.add(i);
					}
				}
			}
		}
		if (distances[endNode] == Integer.MAX_VALUE)
			System.out.println("Nodes not connected");
		return distances[endNode];
	}

	int[] BFS(int startNode) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		int[] distances = new int[nodes];
		q.addFirst(startNode);
		while (!q.isEmpty()) {
			int curNode = q.removeLast();
			for (int i = 0; i < nodes; i++) {
				if (edges[curNode][i] != 0 && distances[i] == 0) {
					distances[i] = distances[curNode] + 1;
					q.addFirst(i);
				}
			}
		}
		return distances;
	}

	int[] Dijkstras(int startNode) {
		int[] distances = new int[nodes];
		for (int i = 0; i < nodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[startNode] = 0;
		class Comp implements Comparator<Integer> {
			public int compare(Integer o1, Integer o2) {
				return distances[o1] - distances[o2];
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(nodes, new Comp());
		pq.add(startNode);
		while (!pq.isEmpty()) {
			int curNode = pq.remove();
			for (int i = 0; i < nodes; i++) {
				if (edges[curNode][i] != 0) {
					int tempdist = distances[curNode] + edges[curNode][i];
					if (tempdist < distances[i]) {
						distances[i] = tempdist;
						pq.add(i);
					}
				}
			}
		}
		return distances;
	}
}
	
