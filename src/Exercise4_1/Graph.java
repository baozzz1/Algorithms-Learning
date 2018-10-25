package Exercise4_1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
	private final int V; // 顶点数目
	private int E; // 边数目
	private Bag<Integer>[] adj; // 邻接表

	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V]; // 创建邻接表
		for (int v = 0; v < V; v++) // 将所有链表初始化为空
			adj[v] = new Bag<Integer>();
	}

	/*
	 * public Graph(In in) { this(in.readInt()); // 读取V并初始化图 int E = in.readInt();
	 * // 读取E for (int i = 0; i < E; i++) { // 添加一条边 int v = in.readInt(); // 读取一个顶点
	 * int w = in.readInt(); // 读取另一个顶点 addEdge(v, w); // 添加一条连接它们的边 } }
	 */

	// Exercise 4.1.15
	public Graph(In in) {
		this(in.readInt()); // 读取V并初始化图
		//this.E = in.readInt(); // 读取E
		Queue<String> reverse = new Queue<String>();
		while (!in.isEmpty()) {
			String[] line = in.readLine().split(" ");
			String v = line[0];
			for (int i = 1; i < line.length; i++)
				reverse.enqueue(v + " " + line[i]);
		}
		for (String edge : reverse) {
			int v = Integer.parseInt(edge.split(" ")[0]);
			int w = Integer.parseInt(edge.split(" ")[1]);
			addEdge(v, w);
		}
	}

	// Exercise 4.1.3
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v])
				reverse.push(w);
			for (int w : reverse)
				adj[v].add(w);
		}
	}

	// Exercise 4.1.4
	public boolean hasEdge(int v, int w) {
		Bag<Integer> adj_v = (Bag<Integer>) this.adj(v);
		if (adj_v.isEmpty())
			return false;
		for (int item : adj_v)
			if (item == w)
				return true;
		return false;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	/*
	 * public void addEdge(int v, int w) { adj[v].add(w); // 将w添加到v的链表中
	 * adj[w].add(v); // 将v添加到w的链表中 E++; }
	 */
	// Exercise 4.1.5
	public void addEdge(int v, int w) {
		if (v == w)
			return;
		for (int u : adj[v])
			if (u == w)
				return;
		adj[v].add(w);
		for (int u : adj[w])
			if (u == v)
				return;
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (int w : this.adj(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}

	// Exercise 4.1.7
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		StdOut.println(G);
		StdOut.println(G.hasEdge(12, 9));
	}
}