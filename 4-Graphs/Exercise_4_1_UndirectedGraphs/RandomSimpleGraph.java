package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdOut;
/**
 * 
 * @author baozzz1
 * 2018年10月28日
 */
public class RandomSimpleGraph {
	public Graph[] G;

	public RandomSimpleGraph(int vertices, int edges) {
		
	}

	public String toString() {
		String s = "";
		for (int g = 0; g < G.length; g++) {
			s += "This is Graph " + (g + 1) + " of all " + G.length + "Graphs.\n";
			s += G[g].V() + " vertices, " + G[g].E() + " edges:\n";
			for (int v = 0; v < G[g].V(); v++) {
				s += v + ": ";
				for (int w : G[g].adj(v))
					s += w + " ";
				s += "\n";
			}
		}
		return s;
	}

	public static void main(String[] args) {
		//RandomSimpleGraph ERGraph = new RandomSimpleGraph(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		//StdOut.println(ERGraph);
	}

}
