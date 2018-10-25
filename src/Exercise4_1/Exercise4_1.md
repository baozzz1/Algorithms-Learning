# Exercises
## 4.1.1
`V(V-1)/2`<br>
`V-1`<br>

## 4.1.2
		adj[]<br>
		0	->	6	->	2	->	5<br>
		1	->	11	->	8	->	1<br>
		2	->	3	->	0	->	6	->	5<br>
		3	->	2	->	6	->	10<br>
		4	->	8	->	1<br>
		5	->	2	->	10	->	0<br>
		6	->	0	->	3	->	2<br>
		7	->	11	->	8<br>
		8	->	4	->	7	->	11	->	1<br>
		9<br>
		10	->	3	->	5<br>
		11	->	1	->	7	->	8<br>
## 4.1.3
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
	