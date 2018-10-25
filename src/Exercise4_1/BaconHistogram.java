package Exercise4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BaconHistogram {
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);
		Graph G = sg.G();

		String source = args[2];
		//String source = "Bacon, Kevin";
		/*if (!sg.contains(source)) {
			StdOut.println(source + "not in database.");
			return;
		}*/
		int s = sg.index(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
		

		//Bag<Integer>[] res = new Bag[gp.diameter()];
		int maxDist = 0;
		Bag<Integer> resIndex = new Bag<Integer>();
		for(int i=0;i<G.V();i++) {
			if(bfs.hasPathTo(i)) {
				int dist = bfs.distTo(G,i);
				if(dist % 2 == 0 && dist / 2 >maxDist) {
					maxDist = dist / 2;
					resIndex.add(i);
				}
			}
		}
		Bag<String>[] res = (Bag<String>[]) new Bag[maxDist + 1];
		for (int r = 0; r < maxDist + 1; r++) // 将所有链表初始化为空
			res[r] = new Bag<String>();
		
		for(int i:resIndex) {
			if(bfs.hasPathTo(i)) {
				int dist = bfs.distTo(G,i);
				if(dist % 2 == 0)
					res[dist / 2].add(sg.name(i));
			}
		}
		for(int i=0;i<res.length;i++) {
			for(String resS: res[i])
			StdOut.print(resS+" ");
			StdOut.println();
		}
		/*while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine();
			if (sg.contains(sink)) {
				int t = sg.index(sink);
				if (bfs.hasPathTo(t))
					for (int v : bfs.pathTo(t))
						StdOut.println(" " + sg.name(v));
				else
					StdOut.println("Not connected");
			} else
				StdOut.println("Not in database.");
		}*/
	}
}
