package Lesson__2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Exercise 2.3.5
 * @author baozzz1
 * 2018年10月30日
 */
public class Quick2distinct {
	public static void sort(Comparable[] a) {
		int lt = 0,gt = a.length-1;
		int i=0;
		while(i<=gt) {
			int cmp = a[i].compareTo(a[lt]);
			if		(cmp<0) exch(a,lt++,i++);
			else if	(cmp>0) exch(a,i,gt--);
			else i++;
		}
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0;
	}
	
	public static void main(String[] args) {
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i+1);

        sort(a);
        for (int i = 0; i < n; i++)
            StdOut.print(a[i]);
        StdOut.println();
    }
	
}
