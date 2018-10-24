package UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class CC {
	private boolean[] marked; // marked[v] = has vertex v been marked?
	private int[] id; // id[v] = id of connected component containing v
	private int[] size; // size[id] = number of vertices in given component
	private int count; // number of connected components

	/**
	 * Computes the connected components of the undirected graph {@code G}.
	 *
	 * @param G the undirected graph
	 */
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int s = 0; s < G.V(); s++)
			if (!marked[s]) {
				dfs(G, s);
				count++;
			}
	}

	// depth-first search for a Graph
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	/**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(int v) {
        return id[v];
    }
    
	/**
	 * Returns the number of connected components in the graph {@code G}.
	 *
	 * @return the number of connected components in the graph {@code G}
	 */
	public int count() {
		return count;
	}

	/**
	 * Unit tests the {@code CC} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		CC cc = new CC(G);

		// number of connected components
		int m = cc.count();
		StdOut.println(m + " components");

		// compute list of vertices in each connected component
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
		for (int i = 0; i < m; i++) {
			components[i] = new Queue<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[cc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < m; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}
