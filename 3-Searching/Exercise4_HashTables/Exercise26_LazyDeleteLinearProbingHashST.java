package Exercise4_HashTables;

import Lesson4_HashTables.LinearProbingHashST;

/**
 * Exercise 3.4.26 LazyDeleteLinearProbingHashST
 * @author baozzz1 
 * 2018年12月21日
 */
public class Exercise26_LazyDeleteLinearProbingHashST {

	private class LazyDeleteLinearProbingHashST<Key, Value> extends LinearProbingHashST<Key, Value> {
		private int deadItemCount;

		public LazyDeleteLinearProbingHashST() {
			super();
			deadItemCount = 0;
		}

		public LazyDeleteLinearProbingHashST(int cap) {
			super(cap);
			deadItemCount = 0;
		}

		// Override
		protected void resize(int cap) {
			LazyDeleteLinearProbingHashST<Key, Value> t;
			t = new LazyDeleteLinearProbingHashST<Key, Value>(cap);
			for (int i = 0; i < M; i++)
				if (vals[i] != null)
					t.put(keys[i], vals[i]);
			keys = t.keys;
			vals = t.vals;
			M = t.M;
			deadItemCount = 0;
		}

		// Override
		public void put(Key key, Value val) {
			if (key == null)
				throw new IllegalArgumentException("Key cannot be null");
			if (val == null) {
				delete(key);
				return;
			}

			// 限定散列表的使用率小于1/2
			if (N + deadItemCount >= M / 2)
				resize(2 * M);
			int i;
			for (i = hash(key); keys[i] != null; i = (i + 1) % M)
				if (keys[i].equals(key)) {
					if (vals[i] == null) {
						deadItemCount--;
						N++;
					}
					vals[i] = val;
					return;
				}
			keys[i] = key;
			vals[i] = val;
			N++;
		}

		// Override
		public void delete(Key key) {
			if (key == null)
				throw new IllegalArgumentException("Key cannot be null");
			if (!contains(key))
				return;
			int i = hash(key);
			while (!key.equals(keys[i]))
				i = (i + 1) % M;
			vals[i] = null;
			deadItemCount++;
			N--;
			if (N > 0 && N <= M / 8)
				resize(M / 2);
		}
	}
}
