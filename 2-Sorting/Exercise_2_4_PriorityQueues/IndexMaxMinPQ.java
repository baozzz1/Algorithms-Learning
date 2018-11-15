package Exercise_2_4_PriorityQueues;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 2.4.29
 * @author baozzz1 2018年11月15日
 */
public class IndexMaxMinPQ<Key extends Comparable<Key>> {
	private int maxN;
	private int n;
	private int[] maxPQ; // binary heap using 1-based indexing
	private int[] maxQP; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
	private int[] minPQ;
	private int[] minQP;
	private Key[] keys;

	public IndexMaxMinPQ(int maxN) {
		if (maxN < 0)
			throw new IllegalArgumentException();
		this.maxN = maxN;
		n = 0;
		maxPQ = new int[maxN + 1];
		maxQP = new int[maxN + 1];
		minPQ = new int[maxN + 1];
		minQP = new int[maxN + 1];
		keys = (Key[]) new Comparable[maxN + 1];
		for (int i = 0; i <= maxN; i++) {
			maxQP[i] = -1;
			minQP[i] = -1;
		}
	}

	public void insert(int i, Key key) {
		if (i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		if (contains(i))
			throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		minQP[i] = n;
		minPQ[n] = i;
		maxQP[i] = n;
		maxPQ[n] = i;
		keys[i] = key;
		maxSwim(n);
		minSwim(n);
	}

	public int minIndex() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return minPQ[1];
	}

	public int maxIndex() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return maxPQ[1];
	}

	public Key minKey() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return keys[minPQ[1]];
	}

	public Key maxKey() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return keys[maxPQ[1]];
	}

	public int delMax() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		int max = maxPQ[1];

		maxExch(1, n);
		int minIndex = minQP[max];
		minExch(minIndex, n);
		n--;

		maxSink(1);
		// delete min
		minSwim(minIndex);
		minSink(minIndex);

		assert max == maxPQ[n + 1];
		maxQP[max] = -1; // delete
		minQP[max] = -1;
		keys[max] = null; // to help with garbage collection
		maxPQ[n + 1] = -1; // not needed
		minPQ[n + 1] = -1; // not needed

		return max;
	}

	public int delMin() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		int min = minPQ[1];

		minExch(1, n);
		int maxIndex = maxQP[min];
		maxExch(maxIndex, n);
		n--;

		minSink(1);
		// delete max
		maxSwim(maxIndex);
		maxSink(maxIndex);

		assert min == minPQ[n + 1];
		minQP[min] = -1; // delete
		maxQP[min] = -1;
		keys[min] = null; // to help with garbage collection
		minPQ[n + 1] = -1; // not needed
		maxPQ[n + 1] = -1; // not needed

		return min;
	}

	public boolean contains(int i) {
		if (i < 0 || i >= maxN)
			throw new IllegalArgumentException();
		// return indexMin.contains(i) && indexMax.contains(i);
		return (maxQP[i] != -1) && (minQP[i] != -1);
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public static void main(String[] args) {
		// insert a bunch of strings
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

		IndexMaxMinPQ<String> pq = new IndexMaxMinPQ<String>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			pq.insert(i, strings[i]);
		}

		// delete and print each key
		while (!pq.isEmpty()) {
			int i = pq.delMin();
			StdOut.println(i + " " + strings[i]);
		}
		StdOut.println();

		// reinsert the same strings
		for (int i = 0; i < strings.length; i++) {
			pq.insert(i, strings[i]);
		}

		// delete and print each key
		while (!pq.isEmpty()) {
			int i = pq.delMax();
			StdOut.println(i + " " + strings[i]);
		}
		StdOut.println();

		// reinsert the same strings
		for (int i = 0; i < strings.length; i++) {
			pq.insert(i, strings[i]);
		}

		StdOut.println("Max value is: " + pq.maxKey());
		StdOut.println("Min value is: " + pq.minKey());

		while (!pq.isEmpty()) {
			pq.delMin();
		}
	}

	/***************************************************************************
	 * General helper functions.
	 ***************************************************************************/
	private boolean greater(int[] pq, int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}

	private boolean less(int[] pq, int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}

	private void minExch(int i, int j) {
		exch(minPQ, minQP, i, j);
	}

	private void maxExch(int i, int j) {
		exch(maxPQ, maxQP, i, j);
	}

	private void exch(int[] pq, int[] qp, int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	/***************************************************************************
	 * Heap helper functions of indexMin.
	 ***************************************************************************/
	private void minSwim(int k) {
		while (k > 1 && greater(minPQ, k / 2, k)) {
			exch(minPQ, minQP, k, k / 2);
			k = k / 2;
		}
	}

	private void minSink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(minPQ, j, j + 1))
				j++;
			if (!greater(minPQ, k, j))
				break;
			exch(minPQ, minQP, k, j);
			k = j;
		}
	}

	/***************************************************************************
	 * Heap helper functions of indexMax.
	 ***************************************************************************/
	private void maxSwim(int k) {
		while (k > 1 && less(maxPQ, k / 2, k)) {
			exch(maxPQ, maxQP, k, k / 2);
			k = k / 2;
		}
	}

	private void maxSink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(maxPQ, j, j + 1))
				j++;
			if (!less(maxPQ, k, j))
				break;
			exch(maxPQ, maxQP, k, j);
			k = j;
		}
	}
}
