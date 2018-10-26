package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * The {@code MoviesGraphProperties} class's argument is<br>
 * movies.txt "/"<br>
 * if there is <b>stackOverFlowError</b> in eclipse, 
 * open the Run Configuration, 
 * find the <b>Vm arguments</b> under the table <b>arguments</b>, 
 * enter (such as) <i>-Xss512m</i> (it should be more than 1m)
 * 
 * @author baozzz1
 *
 */
public class MoviesGraphProperties {
	
	public static void main(String[] args) {
		 String filename  = args[0];
	        String delimiter = args[1];
	        SymbolGraph sg = new SymbolGraph(filename, delimiter);
	        Graph G = sg.G();
	        
	        CC cc = new CC(G);
	        
	     // number of connected components
			int m = cc.count();
			StdOut.println(m + " components");
			
			// compute list of vertices in each connected component
			Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
			for (int i = 0; i < m; i++) {
				components[i] = new Queue<Integer>();
			}
			for (int v = 0; v < G.V(); v++) {
				components[cc.id(v)].enqueue(v);
			}

			// print results
			int countLowerThanTen = 0;
			int maxNumberComponentSize = 0;
			int maxNumberComponentIndex = 0;
			for (int i = 0; i < m; i++) {
				if(components[i].size()<10)
					countLowerThanTen++;
				if(components[i].size()>maxNumberComponentSize) {
					maxNumberComponentSize = components[i].size();
					maxNumberComponentIndex = i;
				}
			}
			StdOut.println(countLowerThanTen + " components lower than 10.");
			
			GraphProperties gp = new GraphProperties(G);
			for (int v :components[maxNumberComponentIndex])
				StdOut.println("eccentricities " + v + ": " + gp.eccentricities[v]);
			StdOut.println("diameter: " + gp.diameter());
			StdOut.println("radius: " + gp.radius());
			StdOut.println("center: " + gp.center());
			
	}
}
