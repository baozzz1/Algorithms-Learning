Exercises 4.1
==
### 4.1.1
`V(V-1)/2`<br>
`V-1`<br>

### 4.1.2
	      adj[]
		0	->	6 -> 2 -> 5
		1	->	11-> 8 -> 1
		2	->	3 -> 0 -> 6 -> 5
		3	->	2 -> 6 -> 10
		4	->	8 -> 1
		5	->	2 -> 10-> 0
		6	->	0 -> 3 -> 2
		7	->	11-> 8
		8	->	4 -> 7 -> 11-> 1
		9
		10	->	3 -> 5
		11	->	1 -> 7 -> 8
### 4.1.3
```Java
public Graph(Graph G) {
	this.V = G.V();
	this.E = G.E();
	adj = (Bag<Integer>[]) new Bag[V];
	for (int v = 0; v < this.V; v++) {
		adj[v] = (Bag<Integer>) G.adj(v);
	}
}
```
click [Graph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Graph.java) to get the better one.
```Java
//better one
public Graph(Graph G) {
	this(G.V());
	this.E = G.E();
	for (int v = 0; v < G.V(); v++) {
		Stack<Integer> reverse = new Stack<Integer>();
		for(int w: G.adj[v])
			reverse.push(w);
		for(int w: reverse)
			adj[v].add(w);
	}
}
```
### 4.1.4
[Graph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Graph.java)
```Java
public boolean hasEdge(int v,int w) {
	Bag<Integer> adj_v = (Bag<Integer>) this.adj(v);
	if(adj_v.isEmpty()) return false;
	for(int item: adj_v)
		if(item==w)
			return true;
	return false;
}
```
### 4.1.5
[Graph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Graph.java)
```Java
// Exercise 4.1.5
public void addEdge(int v,int w) {
	if(v==w)
		return;
	for(int u:adj[v])
		if(u==w)
			return;
	adj[v].add(w);
	for(int u:adj[w])
		if(u==v)
			return;
	adj[w].add(v);
	E++;
}
```
### 4.1.7
[Graph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Graph.java)
```Java
public static void main(String[] args) {
	In in = new In(args[0]);
	Graph G = new Graph(in);
	//the function toString() make G be the parameter of StdOut.println()
	StdOut.println(G);
}
```
### 4.1.10
[DepthFirstSearch.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/DepthFirstSearch.java)
```Java
private void dfs(Graph G, int v) {
	marked[v] = true;
	count++;
	boolean allMarked = true;
	for (int w : G.adj(v))
		if (!marked[w]) {
			dfs(G, w);
			allMarked=false;
		}
	if(allMarked)
		StdOut.println(v + " can be deleted.");
}
```
### 4.1.13
[BreadthFirstPaths.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/BreadthFirstPaths.java)
```Java
public int distTo(Graph G, int target) {
	return distTo[target];
}

private void bfs(Graph G, int s) {
	Queue<Integer> queue = new Queue<Integer>();
	for(int i=0;i<G.V();i++)
		distTo[i] = Integer.MAX_VALUE;
	distTo[s] = 0;
	marked[s] = true;
	queue.enqueue(s);
	while (!queue.isEmpty()) {
		int v = queue.dequeue();
		for (int w : G.adj(v))
			if (!marked[w]) {
				edgeTo[w] = v;
				distTo[w] = distTo[v] + 1;
				marked[w] = true;
				queue.enqueue(w);
			}
	}
}
```
### 4.1.15
[Graph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Graph.java)
```Java
public Graph(In in) {
	this(in.readInt()); // 读取V并初始化图
	this.E = in.readInt(); // 读取E
	Stack<String> reverse = new Stack<String>();
	while(!in.isEmpty()) {
		String[] line = in.readLine().split(" ");
		String v = line[0];
		for(int i = 1;i<line.length;i++)
			reverse.push(v+" "+line[i]);			
	}
	for(String edge: reverse) {
		int v = Integer.parseInt(edge.split(" ")[0]);
		int w = Integer.parseInt(edge.split(" ")[1]);
		addEdge(v, w);
	}
}
```
### 4.1.16
[GraphProperties.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/GraphProperties.java)
### 4.1.22
[BaconHistogram.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/BaconHistogram.java)
### 4.1.23
>I cannot solve it,because of the StackOverflowError when running the CC class.<br>

The question is solved. if there is <b>stackOverFlowError</b> in eclipse,open the Run Configuration, 
find the <b>Vm arguments</b> under the table <b>arguments</b>, enter (such as) <i>-Xss512m</i> (it should be more than 1m).<br>
[MoviesGraphProperties.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/MoviesGraphProperties.java)
### 4.1.24
[DegreeOfSeparation.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/DegreeOfSeparation.java)
```Java
String name = sg.name(v);
if(name.contains("\\(") && name.contains("\\)")) {
	String year = name.split("\\(")[1].split("\\)")[0];
	if (Integer.parseInt(year) < y)
		continue;
}					
StdOut.println(" " + name);
```
### 4.1.25
[DegreeOfSeparationDFS.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/DegreeOfSeparationDFS.java)
### 4.1.28
[Cycle.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Cycle.java)
```Java
//Exercise 4.1.28
private void dfs(Graph G, int v, int u) {
	marked[v] = true;
	for (int w : G.adj(v)) {
		// 允许自环，自环时hasCycle置true
		if(w==v)
			hasCycle = true;
		if (!marked[w])
			dfs(G, w, v);
		else/* if (w != u)*/ //允许平行边
			hasCycle = true;
	}
}
```
# Creative Problems
###4.1.30 Graph Enumeration
when there are V points and E edges:<br>
- the 1st edge get `C 2 V = V*(V-1)/2` solutions, <br>
- the 2nd edge get `(C 2 V) - 1 = V*(V-1)/2 - 1` solutions,<br>
- ...<br>
- the Eth edge get `(C 2 V) - (E-1) = V*(V-1)/2 - (E-1)` solutions.<br>

So, <br>
- when `V(V-1) > 2(E-1)`, there are `[V(V-1)-E+1]*E/2` solutions;<br>
- when `V(V-1) =   2E  `, there are `[V(V-1)+ 2 ]*E/4` solutions;<br>
- besides, V(V-1) cannot be less than 2E.<br> 
### 4.1.31 Parallel Edge Detection
This algorithm has order of N time complexity.<br>
`T(E) = O(N)`
```Java
% Input (Graph)G
int count = 0;
for(int v = 0;v<G.V();v++) {
	int[] isParallel = new int[G.V()];
	for(int w:G.adj(v)) {
		isParallel[w]++;
		if(isParallel[w]>=2)
			count++;
	}
}
StdOut.println("The parallel lines' number is " + count/2);
```
### 4.1.35 Bitconnectedness
[Bridge.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/Bridge.java)
# Experiment Problems
### 4.1.38 Random Graphs
[ErdoRenyiGraph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/ErdoRenyiGraph.java)
```Java
public ErdoRenyiGraph(int vertices, int edges) {
	this.G = new Graph(vertices);
	int u, w;
	for (int e = 0; e < edges; e++) {
		do {
			u = (int) (Math.random() * vertices);
			w = (int) (Math.random() * vertices);
			if (vertices * (vertices - 1) <= 2 * e)
				break;
		} while (u == w || this.G.hasEdge(u, w));
		this.G.addEdge(u, w);
	}
}
```
### 4.1.39 Random Simple Graph
[RandomSimpleGraph.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/RandomSimpleGraph.java)
<br>`Not finished.`
### 4.1.40 Random Sparse Graphs
[RandomSparseGraphs.java](https://github.com/baozzz1/Algorithms-Learning/blob/master/4-Graphs/Exercise_4_1_UndirectedGraphs/RandomSparseGraphs.java)
<br>`Not finished.`



