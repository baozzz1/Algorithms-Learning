package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Exercise 2.3.6
 * @author baozzz1
 * 2018年10月30日
 */
public class Quick_CountCompareTimes {
	public static int times = 0;

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return; // times;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo, j = hi + 1;
		while (true) {
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		times++;
		return a.compareTo(b) < 0;
	}

	private static Comparable[] randomArray(int N) {
		Comparable[] a = new Comparable[N];
		for (int i = 0; i < a.length; i++)
			a[i] = StdRandom.uniform(1000000000);
		return a;
	}

	public static void main(String[] args) {
		int N = 100;
		for (int i = 0; i < 3; i++) {
			Comparable[] a = randomArray(N);
			Quick_CountCompareTimes qc = new Quick_CountCompareTimes();
			qc.sort(a);
			StdOut.println("Quick sort random " + N + " numbers need compare " + qc.times + " times, ");
			StdOut.println("while 2NlnN = " + 2 * N * Math.log((double) N));
			N *= 10;
		}
	}
}
