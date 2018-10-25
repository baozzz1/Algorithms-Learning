#Exercises
##4.1.1
`V(V-1)/2`<br>
`V-1`<br>
##4.1.2
		adj[]
		0	->	6	->	2	->	5
		1	->	11	->	8	->	1
		2	->	3	->	0	->	6	->	5
		3	->	2	->	6	->	10
		4	->	8	->	1
		5	->	2	->	10	->	0
		6	->	0	->	3	->	2
		7	->	11	->	8
		8	->	4	->	7	->	11	->	1
		9
		10	->	3	->	5
		11	->	1	->	7	->	8
##4.1.3
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
	