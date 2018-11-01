package Lesson__2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Insertion {
	public static void sort(Comparable[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++)
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i < hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
		}
	}

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// exchange a[i] and a[j] (for indirect sort)
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			StdOut.println(a[i]);
	}

	/**
	 * Reads in a sequence of strings from standard input; insertion sorts them; and
	 * prints them to standard output in ascending order.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Insertion.sort(a);
		show(a);
	}
}
