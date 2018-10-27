package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;

public class Bridge {
	private int bridges;
	private int cnt;
	private int[] pre;
	private int[] low;

	public Bridge(Graph G) {
		low = new int[G.V()];
		pre = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			low[v] = -1;
			pre[v] = -1;
		}
		for (int v = 0; v < G.V(); v++)
			if (pre[v] == -1)
				dfs(G, v, v);
	}

	public int components() {
		return bridges + 1;
	}

	private void dfs(Graph G, int u, int v) {
		pre[v] = cnt++;
		low[v] = pre[v];
		for (int w : G.adj(v)) {
			if (pre[w] == -1) {
				dfs(G, v, w);
				low[v] = Math.min(low[v], low[w]);
				if (low[w] == pre[w]) {
					StdOut.println(v + "-" + w + " is a bridge");
					bridges++;
				}
			} else if (w != u)
				low[v] = Math.min(low[v], pre[w]);
		}
	}

	public static void main(String[] args) {
		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		Graph G = GraphGenerator.simple(V, E);
		StdOut.println(G);

		Bridge bridge = new Bridge(G);
		StdOut.println("Edge connected components = " + bridge.components());
	}
}
