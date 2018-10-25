package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreeOfSeparation {
	/**
	 * 
	 * @param args<br>
	 *        such as: movies.txt "/" "Bacon, Kevin" "2000"<br>
	 *        input: one actor
	 */
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.G();

		String source = args[2];
		if (!sg.contains(source)) {
			StdOut.println(source + "not in database.");
			return;
		}
		String inputY = args[3];
		int y = Integer.parseInt(inputY);
		int s = sg.index(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
		while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine();
			if (sg.contains(sink)) {
				int t = sg.index(sink);
				if (bfs.hasPathTo(t))
					for (int v : bfs.pathTo(t)) {
						String name = sg.name(v);
						if(name.contains("\\(") && name.contains("\\)")) {
							String year = name.split("\\(")[1].split("\\)")[0];
							if (Integer.parseInt(year) < y)
								continue;
						}					
						StdOut.println(" " + name);
					}
				else
					StdOut.println("Not connected");
			} else
				StdOut.println("Not in database.");
		}
	}
}
