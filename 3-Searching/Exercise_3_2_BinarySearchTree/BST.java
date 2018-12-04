package Exercise_3_2_BinarySearchTree;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author baozzz1 
 * 2018年12月3日
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		// Exercise 3.2.6 way2
		private int height;
		// Exercise 3.2.7 way2
		private int totalCompares;

		public Node(Key key, Value val, int N, int height, int totalCompares) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height = height;
			this.totalCompares = totalCompares;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1, 1, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		// Exercise 3.2.6 way2
		x.height = Math.max(heightWay2(x.left), heightWay2(x.right)) + 1;
		// Exercise 3.2.7 way2
		x.totalCompares = totalComparesWay2(x.left) + size(x.left) 
						+ totalComparesWay2(x.right) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		return max(x.right);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp > 0)
			return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.left);
		if (t > k)
			return select(x.left, k);
		else if (t < k)
			return select(x.right, k - t - 1);
		else
			return x;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		// Exercise 3.2.6
		x.height = Math.max(heightWay2(x.left), heightWay2(x.right)) + 1;
		// Exercise 3.2.7 way2
		x.totalCompares = totalComparesWay2(x.left) + size(x.left) 
						+ totalComparesWay2(x.right) + size(x.right) + 1;
		return x;
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		// Exercise 3.2.6
		x.height = Math.max(heightWay2(x.left), heightWay2(x.right)) + 1;
		// Exercise 3.2.7 way2
		x.totalCompares = totalComparesWay2(x.left) + size(x.left) 
						+ totalComparesWay2(x.right) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		// Exercise 3.2.6
		x.height = Math.max(heightWay2(x.left), heightWay2(x.right)) + 1;
		// Exercise 3.2.7 way2
		x.totalCompares = totalComparesWay2(x.left) + size(x.left) 
						+ totalComparesWay2(x.right) + size(x.right) + 1;
		return x;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);
	}

	/*
	 * Exercise 3.2.6 
	 * 1. 递归的方法，用时N，所需空间和树高成正比；
	 */
	public int heightWay1() {
		return heightWay1(root);
	}

	private int heightWay1(Node x) {
		if (x == null)
			return 0;
		else
			return Math.max(heightWay1(x.left), heightWay1(x.right)) + 1;
	}

	/*
	 * Exercise 3.2.6 
	 * 2. 模仿size()
	 */
	public int heightWay2() {
		return heightWay2(root);
	}

	private int heightWay2(Node x) {
		if (x == null)
			return 0;
		else
			return x.height;
	}
	
	/*
	 * Exercise 3.2.7 
	 * 1. 递归的方法，用时N，所需空间和树高成正比
	 */
	public double avgComparesWay1() {
		return totalComparesWay1(root)/(double)size() + 1;
	}

	private int totalComparesWay1(Node x) {
		if (x == null) return 0;
		int totalComparesLeft = totalComparesWay1(x.left) + size(x.left);
		int totalComparesRight = totalComparesWay1(x.right) + size(x.right);
		return totalComparesLeft + totalComparesRight + 1;
	}
	
	/*
	 * Exercise 3.2.7 
	 * 2. 模仿size()
	 */
	public double avgComparesWay2() {
		return totalComparesWay1(root)/(double)size() + 1;
	}
	
	private int totalComparesWay2(Node x) {
		if (x == null) return 0;
		else return x.totalCompares;
	}
	
	/*
	 * Exercise 3.2.8
	 *  接受一个整型N，返回完美二叉树平均一次命中所需的时间
	 */
	public static double optCompares(int N) {
		if (N == 0)
			return 0.0;
		else if (N == 1)
			return 1.0;
		long totalCompares = 0;
		long number = 1;
		int height = 1;
		while (true) {
			totalCompares += height * number;
			height++;
			number *= 2;
			if (number > N)
				break;
		}
		totalCompares -= (height - 1) * (number - 1 - N);
		return totalCompares/(double)N;
	}
	
	public static void main(String[] args) {
		StdOut.printf("%.2f\n",optCompares((int)Math.pow(2, 30)));
	}
}
