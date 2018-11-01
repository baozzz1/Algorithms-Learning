package Lesson__2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author baozzz1
 * 2018年10月30日
 */
public class Quick3way {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo,i = lo+1,gt=hi;
		Comparable v = a[lo];
		while(i<=gt) {
			int cmp = a[i].compareTo(v);
			if(cmp<0) exch(a,lt++,i++);
			else if(cmp>0) exch(a,i,gt--);
			else i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0;
	}
	
}
