package Lesson4_HashTables;

import Lesson1_ElementarySymbolTables.SequentialSearchST;

/**
 * SeparateChainingHashST <br>
 * Exercise 3.4.9, 3.4.19
 * @author baozzz1 
 * 2018年12月20日
 */
public class SeparateChainingHashST<Key, Value> {
	protected int N; // 键值对总数
	protected int M; // 散列表的大小
	public SequentialSearchST<Key, Value>[] st; // 存放链表对象的数组
	protected static final int DEFAULT_HASH_TABLE_SIZE = 997;

	public SeparateChainingHashST() {
		this(DEFAULT_HASH_TABLE_SIZE);
	}

	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key) {
		return (Value) st[hash(key)].get(key);
	}

	public void put(Key key, Value val) {
		if (N >= 8 * M)
			resize(2 * M);
		if (!contains(key))
			N++;
		st[hash(key)].put(key, val);
	}

	// Exercise 3.4.9
	public void delete(Key key) {
		st[hash(key)].delete(key);
		N--;
		if (N > 0 && N <= 2 * M)
			resize(M / 2);
	}

	private void resize(int cap) {
		SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<Key, Value>(cap);
		for (int i = 0; i < M; i++)
			for (Key key : st[i].keys())
				t.put(key, st[i].get(key));
		st = t.st;
		M = t.M;
	}

	protected boolean contains(Key key) {
		return get(key) != null;
	}

	public static void main(String[] agrs) {
		SeparateChainingHashST<String, Integer> schST;
		schST = new SeparateChainingHashST<String, Integer>(4);
		String s = "EASYQUESTION";
		for (int i = 0; i < s.length(); i++)
			schST.put((String) s.substring(i, i + 1), i);
	}
}
