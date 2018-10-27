package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdOut;
/**
 * 
 * @author baozzz1
 * 2018年10月28日
 */
public class ErdoRenyiGraph {
	public Graph G;

	public ErdoRenyiGraph(int vertices, int edges) {
		this.G = new Graph(vertices);
		int u, w;
		for (int e = 0; e < edges; e++) {
			do {
				u = (int) (Math.random() * vertices);
				w = (int) (Math.random() * vertices);
				if (vertices * (vertices - 1) <= 2 * e)
					break;
			} while (u == w || this.G.hasEdge(u, w));
			this.G.addEdge(u, w);
		}
	}

	public String toString() {
		String s = G.V() + " vertices, " + G.E() + " edges\n";
		for (int v = 0; v < G.V(); v++) {
			s += v + ": ";
			for (int w : G.adj(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		ErdoRenyiGraph ERGraph = new ErdoRenyiGraph(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		StdOut.println(ERGraph);
	}

}
