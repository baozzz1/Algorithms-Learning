package Exercise4_HashTables;

import edu.princeton.cs.algs4.Queue;

/**
 * Exercise 3.4.2, 3.4.3, 3.4.9
 * @author baozzz1 
 * 2018年12月20日
 */
public class SeparateChainingHashST<Key, Value> {
	private int N; // 键值对总数
	private int M; // 散列表的大小
	private Node[] listsFirst;
	private int[] listsN;

	private class Node {
		Key key;
		Value val;
		Node next;
		int lastAmount;

		public Node() {
		}

		public Node(Key key, Value val, Node next, int lastAmount) {
			this.key = key;
			this.val = val;
			this.next = next;
			this.lastAmount = lastAmount;
		}
	}

	public SeparateChainingHashST() {
		this(997);
	}

	public SeparateChainingHashST(int M) {
		this.M = M;
		listsFirst = (Node[]) new Object[M];
		listsN = (int[]) new int[M];
		for (int i = 0; i < M; i++)
			listsFirst[i] = new Node();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key) {
		for (Node x = listsFirst[hash(key)]; x != null; x = x.next)
			if (key.equals(x.key))
				return x.val;
		return null;
	}

	public void put(Key key, Value val) {
		Node first = listsFirst[hash(key)];
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		first = new Node(key, val, first, N);
		N++;
		listsN[hash(key)]++;
	}

	// Exercise 3.4.3
	public void deleteUpNodes(int k) {
		for (int i = 0; i < M; i++) {
			Node first = listsFirst[i];
			for (Node x = first; x != null; x = x.next)
				if (x.lastAmount > k) {
					// if x is the first node
					if (x == first) {
						// if x is the first and the last node
						if (x.next == null) {
							first.val = null;
							first.next = null;
							first = null;
						} else {
							first.val = null;
							first = new Node(first.next.key, first.next.val, first.next.next, first.next.lastAmount);
						}
					} else if (x.next == null) {
						x.val = null;
						x = null;
					} else {
						x.key = x.next.key;
						x.val = x.next.val;
						x.lastAmount = x.next.lastAmount;
						x = x.next;
						x.next = x.next.next;
					}
				}
		}
	}

	public void delete(Key key) {
		for (int i = 0; i < M; i++) {
			Node first = listsFirst[M];
			for (Node x = first; x != null; x = x.next) {
				if (key.equals(x.key)) {
					// if x is the first node
					if (x == first) {
						// if x is the first and the last node
						if (x.next == null) {
							first.val = null;
							first.next = null;
							first = null;
						} else {
							first.val = null;
							first = new Node(first.next.key, first.next.val, first.next.next, first.next.lastAmount);
						}
					} else if (x.next == null) {
						x.val = null;
						x = null;
					} else {
						x.key = x.next.key;
						x.val = x.next.val;
						x = x.next;
						x.next = x.next.next;
					}
					return;
				}
			}
		}
	}

	// Exercise 3.4.19
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		for (int i = 0; i < M; i++)
			for (Node x = listsFirst[i]; x != null; x = x.next)
				q.enqueue(x.key);
		return q;
	}

}
