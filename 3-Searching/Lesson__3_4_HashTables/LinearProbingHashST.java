package Lesson__3_4_HashTables;

import edu.princeton.cs.algs4.Queue;

/**
 * LinearProbingHashST
 * Exercise 3.4.19, 3.4.20
 * @author baozzz1
 * 2018年12月20日
 */
public class LinearProbingHashST<Key, Value> {
	private int N;
	private int M = 16;
	private Key[] keys;
	private Value[] vals;

	public LinearProbingHashST() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	public LinearProbingHashST(int cap) {
		M = cap;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	// 3.4.4
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(cap);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}

	public void put(Key key, Value val) {
		//限定散列表的使用率小于1/2
		if (N >= M / 2)
			resize(2 * M);
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public void delete(Key key) {
		if (!contains(key))
			return;
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		//限定散列表的使用率大于1/8
		if (N > 0 && N <= M / 8)
			resize(M / 2);
	}

	public boolean contains(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return true;
		return false;
	}
	
	// Exercise 3.4.19
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		for(int i=M;i<M;i++)
			if(keys[i]!=null)
				q.enqueue(keys[i]);
		return q;
	}
	
	public static void main(String[] agrs) {
		LinearProbingHashST<String, Integer> lphST;
		lphST = new LinearProbingHashST<String, Integer>(4);
		String s = "EASYQUESTION";
		for(int i=0;i<s.length();i++)
			lphST.put((String)s.substring(i, i + 1), i);
	}
}
