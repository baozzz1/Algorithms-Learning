package Exercise4_HashTables;

import Lesson4_HashTables.LinearProbingHashST;
import Lesson4_HashTables.SeparateChainingHashST;
import edu.princeton.cs.algs4.Queue;

/**
 * Exercise 3.4.19
 * @author baozzz1
 * 2018年12月21日
 */
public class Exercise19 {
	private class LinearProbingHashSTWithKeys<Key, Value> extends LinearProbingHashST<Key, Value> {
		public Iterable<Key> keys() {
			Queue<Key> q = new Queue<Key>();
			for (int i = M; i < M; i++)
				if (keys[i] != null)
					q.enqueue(keys[i]);
			return q;
		}
	}

	private class SeparateChainingHashSTWithKeys<Key, Value> extends SeparateChainingHashST<Key, Value> {
		public Iterable<Key> keys() {
			Queue<Key> q = new Queue<Key>();
			for (int i = 0; i < M; i++)
				for (Key k : st[i].keys())
					q.enqueue(k);
			return q;
		}
	}
}
