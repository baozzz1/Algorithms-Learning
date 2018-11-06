package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.15 Nuts and bolts</a><br>
 * @author baozzz1
 * 2018年10月30日
 */
public class Quick_NutsAndBolts {

	private static int partition(Comparable[] a, int lo, int hi, Comparable target) {
		int i = lo,firstFind = lo;
		int gt = hi;
		while(i<=gt) {
			int cmp = a[i].compareTo(target);
			if(cmp<0)		i++;
			else if(cmp>0)	exch(a,i,gt--);
			else 			firstFind = i++;
		}
		exch(a,firstFind,i-1);
		return i-1;
	}

	private static void sort(Comparable[] a, Comparable[] b, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi, b[lo]);
		partition(b, lo, hi, b[lo]);
		sort(a, b, lo, j - 1);
		sort(a, b, j + 1, hi);
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void println(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
 
	public static void main(String[] args) {
		int N = StdIn.readInt();
		Comparable[] nuts = new Comparable[N];
		Comparable[] bolts = new Comparable[N];
		for (int i = 0; i < N; i++) {
			nuts[i] = i;
			bolts[i] = i;
		}
		StdRandom.shuffle(nuts);
		StdRandom.shuffle(bolts);
		StdOut.print("nuts' never sorted array is: ");
		println(nuts);
		StdOut.print("bolts' never sorted array is: ");
		println(bolts);

		sort(nuts, bolts, 0, N - 1);
		StdOut.print("nuts' sorted array is: ");
		println(nuts);
		StdOut.print("bolts' sorted array is: ");
		println(bolts);
	}
}
