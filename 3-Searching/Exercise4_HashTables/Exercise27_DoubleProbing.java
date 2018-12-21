package Exercise4_HashTables;

import Lesson4_HashTables.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.4.27
 * @author baozzz1
 * 2018年12月21日
 */
public class Exercise27_DoubleProbing {
	private class SeparateChainingHashSTWithDoubleProbing<Key, Value> extends SeparateChainingHashST<Key, Value> {

		public SeparateChainingHashSTWithDoubleProbing() {
			this(DEFAULT_HASH_TABLE_SIZE);
		}

		public SeparateChainingHashSTWithDoubleProbing(int M) {
			super(M);
		}

		private int hash1(Key key) {
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
		public Value get(Key key) {
			if (key == null)
				throw new IllegalArgumentException("Argument to get() cannot be null");
			int hash1 = hash1(key);
			int hash2 = hash2(key);
			Value val;
			if (st[hash1].size() <= st[hash2].size()) {
				val = st[hash1].get(key);
				if (val == null && hash1 != hash2)
					val = st[hash2].get(key);
			} else {
				val = st[hash2].get(key);
				if (val == null)
					val = st[hash1].get(key);
			}
			return val;
		}

		// Override
		public void put(Key key, Value val) {
			if (key == null)
				throw new IllegalArgumentException("Argument to put() cannot be null");
			if (val == null) {
				delete(key);
				return;
			}
			if (N >= 8 * M)
				resize(2 * M);
			int hash1 = hash1(key);
			int hash2 = hash2(key);
			if (!contains(key)) {
				N++;
				if (st[hash1].size() <= st[hash2].size())
					st[hash1].put(key, val);
				else
					st[hash2].put(key, val);
			} else {
				boolean isInlist1 = false;
				for (Key k : st[hash1].keys())
					if (k.equals(key)) {
						isInlist1 = true;
						break;
					}
				if (isInlist1)
					st[hash1].put(key, val);
				else
					st[hash2].put(key, val);
			}
		}

		// Override
		public void delete(Key key) {
			if (key == null)
				throw new IllegalArgumentException("Argument to delete() cannot be null");
			if (!contains(key))
				return;
			int hash1 = hash1(key);
			int hash2 = hash2(key);
			if (st[hash1].size() != 0 && (st[hash1].size() <= st[hash2].size() || st[hash2].size() == 0)) {
				int stN = st[hash1].size();
				st[hash1].delete(key);
				if (stN == st[hash1].size())
					st[hash2].delete(key);
			} else {
				int stN = st[hash2].size();
				st[hash2].delete(key);
				if (stN == st[hash2].size())
					st[hash1].delete(key);
			}
			N--;
			//if (N > 0 && N <= 2 * M)
			//	resize(M / 2);
		}

		private void resize(int cap) {
			SeparateChainingHashSTWithDoubleProbing<Key, Value> t;
			t = new SeparateChainingHashSTWithDoubleProbing<Key, Value>(cap);
			for (int i = 0; i < M; i++)
				for (Key key : st[i].keys())
					t.put(key, st[i].get(key));
			st = t.st;
			M = t.M;
		}

		public void print() {
			for (int i = 0; i < M; i++) {
				StdOut.print("  "+ i + ": ");
				for (Key k : st[i].keys())
					StdOut.print("{" + k + "(" + hash1(k) + "," + hash2(k) + "), " + st[i].get(k) + "} ");
				StdOut.println();
			}
		}

		public double AverageSearchingHitTime() {
			int sum = 0;
			int length = 0;
			for (int i = 0; i < M; i++) {
				length = st[i].size();
				sum += (1 + length) * length / 2;
			}
			return (double) sum / (double) N;
		}

		public double AverageSearchingMissTime() {
			int sum = 0;
			for (int i = 0; i < M; i++)
				sum = st[i].size();
			return (double) sum / (double) M;
		}
	}

	public static void main(String[] args) {
		Exercise27_DoubleProbing doubleProbing = new Exercise27_DoubleProbing();
		SeparateChainingHashSTWithDoubleProbing<String, Integer> schDP;
		schDP = doubleProbing.new SeparateChainingHashSTWithDoubleProbing<String, Integer>(3);
		String input = "EASYQUESTION";
		StdOut.println("Insert process:");
		for (int i = 0; i < input.length(); i++) {
			schDP.put(input.substring(i, i + 1), i);
			StdOut.printf("Step %d: \n", i + 1);
			schDP.print();
		}
		StdOut.printf("Avearge searching hit time: %.2f\n", schDP.AverageSearchingHitTime());
		StdOut.printf("Avearge searching miss time: %.2f\n", schDP.AverageSearchingMissTime());
		StdOut.println();
		StdOut.println("Delete process:");
		for (int i = 0; i < input.length(); i++) {
			schDP.delete(input.substring(i, i + 1));
			StdOut.printf("Step %d: \n", i + 1);
			schDP.print();
		}
	}
}

/***********************************
 * Insert process:
	Step 1: 
	  0: {E(0,0), 0} 
	  1: 
	  2: 
	Step 2: 
	  0: {E(0,0), 0} 
	  1: {A(1,1), 1} 
	  2: 
	Step 3: 
	  0: {E(0,0), 0} 
	  1: {S(1,1), 2} {A(1,1), 1} 
	  2: 
	Step 4: 
	  0: {E(0,0), 0} 
	  1: {Y(1,1), 3} {S(1,1), 2} {A(1,1), 1} 
	  2: 
	Step 5: 
	  0: {Q(0,0), 4} {E(0,0), 0} 
	  1: {Y(1,1), 3} {S(1,1), 2} {A(1,1), 1} 
	  2: 
	Step 6: 
	  0: {Q(0,0), 4} {E(0,0), 0} 
	  1: {Y(1,1), 3} {S(1,1), 2} {A(1,1), 1} 
	  2: {U(2,2), 5} 
	Step 7: 
	  0: {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 2} {A(1,1), 1} 
	  2: {U(2,2), 5} 
	Step 8: 
	  0: {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {U(2,2), 5} 
	Step 9: 
	  0: {T(0,0), 8} {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {U(2,2), 5} 
	Step 10: 
	  0: {T(0,0), 8} {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {I(2,2), 9} {U(2,2), 5} 
	Step 11: 
	  0: {T(0,0), 8} {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 12: 
	  0: {N(0,0), 11} {T(0,0), 8} {Q(0,0), 4} {E(0,0), 6} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Avearge searching hit time: 2.20
	Avearge searching miss time: 1.00
	
	Delete process:
	Step 1: 
	  0: {N(0,0), 11} {T(0,0), 8} {Q(0,0), 4} 
	  1: {Y(1,1), 3} {S(1,1), 7} {A(1,1), 1} 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 2: 
	  0: {N(0,0), 11} {T(0,0), 8} {Q(0,0), 4} 
	  1: {Y(1,1), 3} {S(1,1), 7} 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 3: 
	  0: {N(0,0), 11} {T(0,0), 8} {Q(0,0), 4} 
	  1: {Y(1,1), 3} 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 4: 
	  0: {N(0,0), 11} {T(0,0), 8} {Q(0,0), 4} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 5: 
	  0: {N(0,0), 11} {T(0,0), 8} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} {U(2,2), 5} 
	Step 6: 
	  0: {N(0,0), 11} {T(0,0), 8} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} 
	Step 7: 
	  0: {N(0,0), 11} {T(0,0), 8} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} 
	Step 8: 
	  0: {N(0,0), 11} {T(0,0), 8} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} 
	Step 9: 
	  0: {N(0,0), 11} 
	  1: 
	  2: {O(2,2), 10} {I(2,2), 9} 
	Step 10: 
	  0: {N(0,0), 11} 
	  1: 
	  2: {O(2,2), 10} 
	Step 11: 
	  0: {N(0,0), 11} 
	  1: 
	  2: 
	Step 12: 
	  0: 
	  1: 
	  2: 
 * *********************************/
