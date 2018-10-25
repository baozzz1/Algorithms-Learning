package Lesson__4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//使用深度优先搜索标记与起点连通的所有顶点所需的时间与顶点的度数之和成正比
public class DepthFirstSearch {
	private boolean[] marked;
	private int count;

	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean marked(int w) {
		return marked[w];
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch search = new DepthFirstSearch(G, s);

		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				StdOut.print(v + " ");
		StdOut.println();

		if (search.count() != G.V())
			StdOut.print("NOT ");
		StdOut.println("connected");
	}
}
