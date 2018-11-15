package Exercise_2_4_PriorityQueues;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * @author baozzz1 
 * 2018年11月12日
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	private Key min;

	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN + 1];
	}
	
	// Exercise 2.3.19
	public MaxPQ(Key[] keys) {
		for (int i = 0; i < keys.length; i++) {
			pq[++N] = keys[i];
			swim(N);
		}
	}
	
	// Exercise 2.4.22
	public void resize(int n) {
		assert n > N;
		Key[] temp = (Key[]) new Comparable[n];
		for (int i = 1; i < n; i++)
			temp[i] = pq[i];
		pq = temp;
	}
	
	public Key min() {
		return min;
	}
	
	// Exercise 2.3.27
	public void insert(Key v) {
		if (v.compareTo(min) < 0)
			min = v;
		pq[++N] = v;
		swim(N);
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		if (N == 0) min = null;
		return max;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k /= 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public int size() {
		return N;
	}
	public static void main(String[] args) {
		int M = Integer.parseInt(args[0]);
		MaxPQ<Transaction> pq = new MaxPQ<Transaction>(M + 1);
		while (StdIn.hasNextLine()) {
			pq.insert(new Transaction(StdIn.readLine()));
			if (pq.size() > M)
				pq.delMax();
		}
		Stack<Transaction> stack = new Stack<Transaction>();
		while (!pq.isEmpty())
			stack.push(pq.delMax());
		for (Transaction t : stack)
			StdOut.println(t);
	}
}
