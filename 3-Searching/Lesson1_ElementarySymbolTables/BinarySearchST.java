package Lesson1_ElementarySymbolTables;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于有序数组的二分查找
 * @author baozzz1 
 * 2018年11月26日
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return N;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}

	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		int mid, cmp;
		while (lo <= hi) {
			mid = lo + (lo + hi) / 2;
			cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public void put(Key key, Value val) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[N - 1];
	}

	public Key select(int k) {
		return keys[k];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}

	// Exercise 3.1.17
	public Key floor(Key key) {
		int i = rank(key);
		if (keys[i].compareTo(key) == 0 || i == N - 1)
			return keys[i];
		return keys[i + 1];

	}

	// Exercise 3.1.16
	public void delete(Key key) {
		// put(key, null);
		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) != 0)
			return;
		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}
		// keys[N-1] = null;
		vals[N - 1] = null;
		N--;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<Key>();
		for (int i = rank(lo); i < rank(hi); i++)
			q.enqueue(keys[i]);
		if (contains(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public int size(Key lo, Key hi) {
		if (hi.compareTo(lo) < 0)
			return 0;
		else if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}
}
