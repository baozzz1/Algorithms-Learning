package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreeOfSeparationDFS {
	/**
	 * argument: <br>
	 * movies.txt "/" "Bacon, Kevin"<br>
	 * input(for example): <br>
	 * Kidman, Nicole<br>
	 * if there is <b>stackOverFlowError</b> in eclipse, 
	 * open the Run Configuration, 
	 * find the <b>Vm arguments</b> under the table <b>arguments</b>, 
	 * enter (such as) <i>-Xss512m</i> (it should be more than 1m)
	 * @param args
	 */
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.G();

		// 读取source
		String source = args[2];
		StdOut.println("Source: " + source);
		if (!sg.contains(source)) {
			StdOut.println(source + "not in database.");
			return;
		}
		int s = sg.index(source);
		DepthFirstPaths dfs = new DepthFirstPaths(G, s);

		// 读取sink
		String sink = "";
		StdOut.print("Query: ");
		if (!StdIn.isEmpty()) {
			sink = StdIn.readLine();
		}
		if (sg.contains(sink)) {
			int t = sg.index(sink);
			if (dfs.hasPathTo(t))
				for (int v : dfs.pathTo(t))
					StdOut.println("    " + sg.name(v));
			else
				StdOut.println("Not connected");
		} else
			StdOut.println("Not in database.");

	}
}
