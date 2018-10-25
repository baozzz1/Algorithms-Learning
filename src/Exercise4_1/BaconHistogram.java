package Exercise4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BaconHistogram {
	private Bag<Integer>[] KevinBacon;
	public BaconHistogram(String s1,String s2,String s3) {
		SymbolGraph sg = new SymbolGraph(s1,s2);
		Graph G = sg.G();
		int s = sg.index(s3);	//Kevin Bacon 对应的下标
		KevinBacon = (Bag<Integer>[]) new Bag[G.V()];	
		BreadthFirstPaths bfp = new BreadthFirstPaths(G,s);	//以点Kevin Bacon为起点广度优先搜索
		for(int v :G.adj(s)) {
			KevinBacon[bfp.distTo(G, v)].add(v);
		}
		for(int i=0;i<KevinBacon.length;i++) {
			StdOut.println("Kevin Bacon Number "+i+": ");
			if(KevinBacon[i].isEmpty())
				continue;
			for(int index:KevinBacon[i]) {
				StdOut.println(sg.name(index));
			}
		}
	}
	
	public static void main(String[] args) {
		String filename = args[0];
		String delim = args[1];
		String KevinBaconStr = args[2];
		BaconHistogram bh = new BaconHistogram(filename,delim,KevinBaconStr);
		
		
	}
}
