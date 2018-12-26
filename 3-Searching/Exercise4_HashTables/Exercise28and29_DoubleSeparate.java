package Exercise4_HashTables;

import Lesson4_HashTables.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.4.28
 * @author baozzz1 
 * 2018年12月21日
 */
public class Exercise28and29_DoubleSeparate {
	private class LinearProbingHashSTWithDoubleSeparate<Key, Value> extends LinearProbingHashST<Key, Value> {
		private int deadItemCount;
		private final int[] PRIMES = { 1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749,
				65521, 131071, 262139, 524287, 1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859,
				134217689, 268435399, 536870909, 1073741789, 2147483647 };
		private static final int DEFAULT_HASH_TABLE_SIZE = 31;
		private int lgM;

		public LinearProbingHashSTWithDoubleSeparate() {
			this(DEFAULT_HASH_TABLE_SIZE);
		}

		public LinearProbingHashSTWithDoubleSeparate(int M) {
			super(M);
			lgM = (int) (Math.log(M) / Math.log(2));
			deadItemCount = 0;
		}

		protected int hash(Key key) {
			int hash = (key.hashCode() & 0x7fffffff) % M;
			hash = ((11 * hash) & 0x7fffffff) % M;
			return hash;
		}

		private int hash2(Key key) {
			int hash = (key.hashCode() & 0x7fffffff) % M;
			hash = ((17 * hash) & 0x7fffffff) % M;
			return hash;
		}

		// Override
		protected void resize(int cap) {
			LinearProbingHashSTWithDoubleSeparate<Key, Value> t;
			t = new LinearProbingHashSTWithDoubleSeparate<Key, Value>(cap);
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
			for (i = hash(key); keys[i] != null; i = (i + hash2(key)) % M)
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
				i = (i + hash2(key)) % M;
			vals[i] = null;
			deadItemCount++;
			N--;
			if (N > 0 && N <= M / 8)
				resize(M / 2);
		}

		public Value get(Key key) {
			for (int i = hash(key); keys[i] != null; i = (i + hash2(key)) % M)
				if (keys[i].equals(key))
					return vals[i];
			return null;
		}

		public boolean contains(Key key) {
			for (int i = hash(key); keys[i] != null; i = (i + hash2(key)) % M)
				if (keys[i].equals(key))
					return true;
			return false;
		}

		public void print() {
			for (int i = 0; i < M; i++) {
				StdOut.print("  " + i + ": ");
				if (keys[i] == null) {
					StdOut.println("null");
					continue;
				}
				StdOut.print("{" + keys[i] + "(" + hash(keys[i]) + "," + hash2(keys[i]) + "), " + vals[i] + "} ");
				StdOut.println();
			}
		}
	}

	public static void main(String[] args) {
		Exercise28and29_DoubleSeparate doubleProbing = new Exercise28and29_DoubleSeparate();
		LinearProbingHashSTWithDoubleSeparate<String, Integer> schDS;
		schDS = doubleProbing.new LinearProbingHashSTWithDoubleSeparate<String, Integer>(11);
		String input = "EASYQUESTION";
		StdOut.println("Insert process:");
		for (int i = 0; i < input.length(); i++) {
			schDS.put(input.substring(i, i + 1), i);
			StdOut.printf("Step %d: \n", i + 1);
			schDS.print();
		}
		StdOut.println();
		StdOut.println("Delete process:");
		for (int i = 0; i < input.length(); i++) {
			schDS.delete(input.substring(i, i + 1));
			StdOut.printf("Step %d: \n", i + 1);
			schDS.print();
		}
	}
}

/***********************************
 Insert process:
Step 1: 
  0: {E(0,7), 0} 
  1: null
  2: null
  3: null
  4: null
  5: null
  6: null
  7: null
  8: null
  9: null
  10: null
Step 2: 
  0: {E(0,7), 0} 
  1: null
  2: null
  3: null
  4: null
  5: {A(0,5), 1} 
  6: null
  7: null
  8: null
  9: null
  10: null
Step 3: 
  0: {E(0,7), 0} 
  1: null
  2: null
  3: {S(0,3), 2} 
  4: null
  5: {A(0,5), 1} 
  6: null
  7: null
  8: null
  9: null
  10: null
Step 4: 
  0: {E(0,7), 0} 
  1: null
  2: null
  3: {S(0,3), 2} 
  4: null
  5: {A(0,5), 1} 
  6: {Y(0,6), 3} 
  7: null
  8: null
  9: null
  10: null
Step 5: 
  0: {E(0,7), 0} 
  1: null
  2: {Q(0,2), 4} 
  3: {S(0,3), 2} 
  4: null
  5: {A(0,5), 1} 
  6: {Y(0,6), 3} 
  7: null
  8: null
  9: null
  10: null
Step 6: 
  0: null
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 0} 
  12: null
  13: null
  14: {S(11,3), 2} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: null
  21: null
Step 7: 
  0: null
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: null
  13: null
  14: {S(11,3), 2} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: null
  21: null
Step 8: 
  0: null
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: null
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: null
  21: null
Step 9: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: null
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: null
  21: null
Step 10: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: null
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: {I(11,9), 9} 
  21: null
Step 11: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: null
  19: null
  20: {I(11,9), 9} 
  21: null
Step 12: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), 6} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null

Delete process:
Step 1: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), 1} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 2: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), 7} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 3: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), 3} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), null} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 4: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), 4} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), null} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), null} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 5: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), null} 
  3: null
  4: {U(11,15), 5} 
  5: null
  6: {Y(11,17), null} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), null} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 6: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), null} 
  3: null
  4: {U(11,15), null} 
  5: null
  6: {Y(11,17), null} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), null} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 7: 
  0: {T(0,20), 8} 
  1: null
  2: {Q(11,13), null} 
  3: null
  4: {U(11,15), null} 
  5: null
  6: {Y(11,17), null} 
  7: null
  8: null
  9: null
  10: null
  11: {E(11,7), null} 
  12: {O(11,1), 10} 
  13: null
  14: {S(11,3), null} 
  15: null
  16: {A(11,5), null} 
  17: null
  18: {N(0,6), 11} 
  19: null
  20: {I(11,9), 9} 
  21: null
Step 8: 
  0: {T(0,9), 8} 
  1: {O(0,1), 10} 
  2: null
  3: null
  4: null
  5: null
  6: {N(0,6), 11} 
  7: null
  8: null
  9: {I(0,9), 9} 
  10: null
Step 9: 
  0: null
  1: null
  2: null
  3: {I(3,1), 9} 
  4: null
  5: null
  6: null
  7: null
  8: {N(8,6), 11} 
  9: {O(9,3), 10} 
Step 10: 
  0: null
  1: null
  2: null
  3: {I(3,1), null} 
  4: null
  5: null
  6: null
  7: null
  8: {N(8,6), 11} 
  9: {O(9,3), 10} 
Step 11: 
  0: null
  1: null
  2: null
  3: {I(3,1), null} 
  4: null
  5: null
  6: null
  7: null
  8: {N(8,6), 11} 
  9: {O(9,3), null} 
Step 12: 
  0: null
  1: null
  2: null
  3: {I(3,1), null} 
  4: null
  5: null
  6: null
  7: null
  8: {N(8,6), null} 
  9: {O(9,3), null} 

 *********************************/
