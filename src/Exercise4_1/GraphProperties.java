// Exercise 4.1.16
package Exercise4_1;

/*
 * The {@code GraphProperties} class is defined in <i>Algorthms 4th</i> Exercise 4.1.16
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
			for (int w : G.adj(v))
				if (maxLength < bfp.distTo(G, w))
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
}
