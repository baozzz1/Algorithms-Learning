package Exercise_4_1_UndirectedGraphs;

public class Cycle {
	private boolean[] marked; // marked[v] = has vertex v been marked?
	private boolean hasCycle;

	public Cycle(Graph G) {
		marked = new boolean[G.V()];
		for (int s = 0; s < G.V(); s++)
			if (!marked[s]) {
				dfs(G, s, s);
			}
	}

	//Exercise 4.1.28
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			// 允许自环，自环时hasCycle置true
			if(w==v)
				hasCycle = true;
			if (!marked[w])
				dfs(G, w, v);
			else/* if (w != u)*/ //允许平行边
				hasCycle = true;
		}
	}

	public boolean hasCycle() {
		return hasCycle;
	}
}
