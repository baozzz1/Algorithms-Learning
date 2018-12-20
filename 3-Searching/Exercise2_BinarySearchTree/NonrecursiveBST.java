package Exercise2_BinarySearchTree;

import edu.princeton.cs.algs4.Queue;

/**
 * Exercise 3.2.12
 * BST without rank() and select(),also without N in every node.<br>
 * Exercise 3.2.13
 * Non-recursive put() and get().<br>
 * Exercise 3.2.14
 * Non-recursive min(), max(), floor(), ceiling(), rank() and select().<br>
 */
public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	/* ******************** Exercise 3.2.13 **************************
	 * Search BST for given key (non-recursive version).
	 * ***************************************************************/
	public Value get(Key key) {
		Node x = root;
		int cmp;
		while(x != null) {
			cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	
	/* ******************** Exercise 3.2.13 **************************
	 * Insert key-value pair into symbol table (non-recursive version).
	 * ***************************************************************/
	public void put(Key key, Value val) {
		Node z = new Node(key, val);
		if (root == null) {
			root = z;
			return;
		}
		Node parent = null, x = root;
		int cmp;
		while (x != null) {
			parent = x;
			cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else {
				x.val = val;
				return;
			}
		}
		cmp = key.compareTo(parent.key);
		if (cmp < 0) parent.left = z;
		else parent.right = z;
	}
	
	/* ******************** Exercise 3.2.14 **************************
	 * return min of BST (non-recursive version).
	 * ***************************************************************/
	public Key min() {
		if (root == null) return null;
		Node x = root;
		while (x.left != null)
			x = x.left;
		return x.key;
	}
	
	/* ******************** Exercise 3.2.14 **************************
	 * return max of BST (non-recursive version).
	 * ***************************************************************/
	public Key max() {
		if (root == null) return null;
		Node x = root;
		while (x.right != null)
			x = x.right;
		return x.key;
	}

	/* ******************** Exercise 3.2.14 **************************
	 * return floor of given key (non-recursive version).
	 * ***************************************************************/
	public Key floor(Key key) {
		if (root == null)
			return null;
		Node parent = null, x = root;
		int cmp;
		while (x != null) {
			cmp = key.compareTo(x.key);
			if (cmp > 0) {
				parent = x;
				x = x.right;
			}
			else if (cmp < 0)
				x = x.left;
			else
				return x.key;
		}
		return parent.key;
	}
	
	/* ******************** Exercise 3.2.14 **************************
	 * return ceiling of given key (non-recursive version).
	 * ***************************************************************/
	public Key ceiling(Key key) {
		if (root == null)
			return null;
		Node parent = null, x = root;
		int cmp;
		while (x != null) {
			cmp = key.compareTo(x.key);
			if (cmp > 0)
				x = x.right;
			else if (cmp < 0) {
				parent = x;
				x = x.left;
			} else
				return x.key;
		}
		return parent.key;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if(x.left==null) return x.right;
		x.left = deleteMin(x.left);
		return x;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node x) {
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		return x;
	}
	
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		keys(root,queue,lo,hi);
		return queue;
	}
	
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if(x==null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo<0) keys(x.left,queue,lo,hi);
		if(cmplo<=0 && cmphi>=0) queue.enqueue(x.key);
		if(cmphi>0) keys(x.right,queue,lo,hi);
	}
}
