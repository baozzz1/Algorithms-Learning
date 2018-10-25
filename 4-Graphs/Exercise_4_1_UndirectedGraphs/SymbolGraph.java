package Exercise_4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/routes.txt
 *                https://algs4.cs.princeton.edu/41graph/movies.txt
 *                https://algs4.cs.princeton.edu/41graph/moviestiny.txt
 *                https://algs4.cs.princeton.edu/41graph/moviesG.txt
 *                https://algs4.cs.princeton.edu/41graph/moviestopGrossing.txt
 *  
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 * 
 *  Assumes that input file is encoded using UTF-8.
 *  % iconv -f ISO-8859-1 -t UTF-8 movies-iso8859.txt > movies.txt
 *
 ******************************************************************************/

/**
 * The {@code SymbolGraph} class represents an undirected graph, where the
 * vertex names are arbitrary strings. By providing mappings between string
 * vertex names and integers, it serves as a wrapper around the {@link Graph}
 * data type, which assumes the vertex names are integers between 0 and
 * <em>V</em> - 1. It also supports initializing a symbol graph from a file.
 * <p>
 * This implementation uses an {@link ST} to map from strings to integers, an
 * array to map from integers to strings, and a {@link Graph} to store the
 * underlying graph. The <em>indexOf</em> and <em>contains</em> operations take
 * time proportional to log <em>V</em>, where <em>V</em> is the number of
 * vertices. The <em>nameOf</em> operation takes constant time.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SymbolGraph {
	private ST<String, Integer> st; // string -> index
	String[] keys; // index -> string
	private Graph G; // the underlying graph

	public SymbolGraph(String stream, String sp) {
		st = new ST<String,Integer>();
		In in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			for(int i=0;i<a.length;i++)
				if(!st.contains(a[i]))
					st.put(a[i], st.size());
		}
		keys = new String[st.size()];
		for(String name:st.keys())
			keys[st.get(name)]=name;
		
		G = new Graph(st.size());
		in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i=1;i<a.length;i++)
				G.addEdge(v, st.get(a[i]));
		}
	}

	public boolean contains(String s) {
		return st.contains(s);
	}

	public int index(String s) {
		return st.get(s);
	}

	public String name(int v) {
		return keys[v];
	}

	public Graph G() {
		return G;
	}
	
	/**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.G();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }

}