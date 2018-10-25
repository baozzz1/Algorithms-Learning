// Exercise 4.1.16
package Exercise4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * The {@code GraphProperties} class is defined in <i>Algorithms 4th</i> Exercise 4.1.16
 * <p>
 * @author baozzz1
 */
public class GraphProperties {
	private int[] eccentricities;
	private int diameter = 0;
	private int radius = Integer.MAX_VALUE;
	private int center;

	public GraphProperties(Graph G) {
		Cycle cycle = new Cycle(G);
		if (!cycle.hasCycle())
			throw new IllegalArgumentException("This input is invalid");

		eccentricities = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			BreadthFirstPaths bfp = new BreadthFirstPaths(G, v);
			int maxLength = 0;
			for (int w = 0; w < G.V(); w++)
				if (maxLength < bfp.distTo(G, w) && bfp.distTo(G, w) < G.V())
					maxLength = bfp.distTo(G, w);
			eccentricities[v] = maxLength;

			if (diameter < maxLength)
				diameter = maxLength;
			if (radius > maxLength)
				radius = maxLength;
		}

		for (int v = 0; v < G.V(); v++)
			if (eccentricities[v] == radius)
				center = v;
	}

	//Exercise 4.1.16
	public int eccentricity(int v) {
		return eccentricities[v];
	}

	public int diameter() {
		return diameter;
	}

	public int radius() {
		return radius;
	}

	public int center() {
		return center;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		GraphProperties gp = new GraphProperties(G);
		StdOut.println(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.println("eccentricities " + v + ": " + gp.eccentricities[v]);
		StdOut.println("diameter: " + gp.diameter());
		StdOut.println("radius: " + gp.radius());
		StdOut.println("center: " + gp.center());
	}
}
