package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BaconHistogram {
	/**
	 * The {@code BaconHistogram} class's
	 * arguments is, for example:<br> 
	 * movies.txt "/" Kevin Bacon
	 * @param args
	 * 
	 * @author baozzz1
	 */
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.G();

		String source = "Bacon, Kevin";
		int s = sg.index(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

		int maxDist = 0;
		//int noPathToCount=0;
		Bag<Integer> resIndex = new Bag<Integer>();
		for (int i = 0; i < G.V(); i++) {
			if (bfs.hasPathTo(i)) {
				int dist = bfs.distTo(G, i);
				if (dist % 2 == 0) {
					if (dist / 2 > maxDist)
						maxDist = dist / 2;
					resIndex.add(i);
				}
			}
			else {
				//问题：如何区分电影和演员？
				//noPathToCount++;
			}
		}
		Bag<String>[] res = (Bag<String>[]) new Bag[maxDist + 1];
		for (int r = 0; r < maxDist + 1; r++) // 将所有链表初始化为空
			res[r] = new Bag<String>();

		for (int i : resIndex)
			if (bfs.hasPathTo(i))
				res[bfs.distTo(G, i) / 2].add(sg.name(i));
		
		for (int i = 0; i < res.length; i++) {
			StdOut.println("Kevin Bacon Number "+i+" is "+res[i].size());
		}
	}
}
