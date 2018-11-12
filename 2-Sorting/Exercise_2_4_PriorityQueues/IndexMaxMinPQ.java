package Exercise_2_4_PriorityQueues;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * @author baozzz1 
 * 2018年11月12日
 */
public class IndexMaxMinPQ<Key extends Comparable<Key>> {
	private int[] pq;	//Index of maxPQ
	private int[] qp;	//Index of minPQ
	private int[] index2pq;
	private int[] index2qp;
	private Key[] array;
	private int N = 0;

	public IndexMaxMinPQ(int maxN) {
		array = (Key[]) new Comparable[maxN + 1];
		pq = new int[maxN];
		qp = new int[maxN];
		index2pq = new int[maxN];
		index2qp = new int[maxN];
	}

	public void insert(Key v) {
		N++;
		array[N]=v;
		pq[N] = N;
		qp[N] = N;
		index2pq[N]=N;
		index2qp[N]=N;
		maxSwim(N);
		minSwim(N);
	}
	
	
	

	private void maxSink(int k) {
		while (2 * k < N) {
			int j = 2 * k;
			if (less(pq, j, j + 1))	j++;
			if (!less(pq, k, j))	break;
			exch(pq, k, j);
			k = j;
		}
		if (2 * k == N && less(pq, k, 2 * k))
			exch(pq, k, 2 * k);
	}

	private void minSink(int k) {
		while (2 * k < N) {
			int j = 2 * k;
			if (more(qp, j, j + 1))	j++;
			if (!more(qp, k, j))	break;
			exch(qp, k, j);
			k = j;
		}
		if (2 * k == N && more(qp, k, 2 * k))
			exch(qp, k, 2 * k);
	}
	
	private void maxSwim(int k) {
		while (k > 1 && less(pq, k / 2, k)) {
			exch(pq, k, k / 2);
			k /= 2;
		}
	}

	private void minSwim(int k) {
		while (k > 1 && more(qp, k / 2, k)) {
			exch(qp, k, k / 2);
			k /= 2;
		}
	}

	private boolean less(int[] heapIndex, int i, int j) {
		return array[heapIndex[i]].compareTo(array[heapIndex[j]]) < 0;
	}

	private boolean more(int[] heapIndex, int i, int j) {
		return array[heapIndex[i]].compareTo(array[heapIndex[j]]) > 0;
	}
	
/*	private void exch(int[] heapIndex, int i, int j) {
		int tempIndex = heapIndex[i];
		heapIndex[i] = heapIndex[j];
		heapIndex[j] = tempIndex;
	}*/
	private void PQexch(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Key delMax() {
		int maxIndex = pq[1];
		exch(pq, 1, N);
		N--;
		pq[N + 1] = null;
		maxSink(1);
		return max;
	}

	public Key delMin() {
		Key min = qp[1];
		exch(qp, 1, N--);
		qp[N + 1] = null;
		minSink(1);
		return min;
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
