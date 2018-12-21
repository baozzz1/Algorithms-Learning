package Lesson1_ElementarySymbolTables;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于无序链表的顺序查找
 * @author baozzz1 
 * 2018年11月26日
 */
public class SequentialSearchST<Key, Value> {
	private Node first;
	private int N;

	private class Node {
		Key key;
		Value val;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key))
				return x.val;
		return null;
	}

	public void put(Key key, Value val) {
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		first = new Node(key, val, first);
		N++;
	}

	// Exercise 3.1.5
	public int size() {
		return N;
	}

	// Exercise 3.1.5
	public void delete(Key key) {
		/*
		 * for (Node x = first; x != null; x = x.next) if (key.equals(x.key)) { // if x
		 * is the first node if (x == first) { // if x is the first and the last node if
		 * (x.next == null) { first.val = null; first.next = null; first = null; } else
		 * { first.val = null; first = new Node(first.next.key, first.next.val,
		 * first.next.next); } } else if (x.next == null) { x.val = null; x = null; }
		 * else { x.key = x.next.key; x.val = x.next.val; x = x.next; x.next =
		 * x.next.next; } N--; return; }
		 */
		if (first.key.equals(key)) {
			first = first.next;
			N--;
			return;
		}

		for (Node node = first; node != null; node = node.next) {
			if (node.next != null && node.next.key.equals(key)) {
				node.next = node.next.next;
				N--;
				return;
			}
		}
	}

	// Exercise 3.1.5
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			q.enqueue(x.key);
		return q;
	}
}
