package Exercise_2_3_QuickSort;

import Lesson__2_1_ElementarySorts.Insertion;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Exercise 2.3.18 3Pick, WithSentinel<br>
 * Result: bad result.
 * @author baozzz1
 * 2018年11月06日
 */
public class Quick_3PickWithSentinel {
	public static void sort(Comparable[] a, int M) {
		if (a.length < M)
			Insertion.sort(a);
		else {
			StdRandom.shuffle(a);
			sort(a, 0, a.length - 1, M);
		}
	}

	private static void sort(Comparable[] a, int lo, int hi, int M) {
		if (hi <= lo)
			return;
		if (hi - lo + 1 < M)
			Insertion.sort(a, lo, hi);
		else {
			int j = partition(a, lo, hi);
			sort(a, lo, j - 1, M);
			sort(a, j + 1, hi, M);
		}
	}
	private static int partition(Comparable[] a, int lo, int hi) {
		int[] p = new int[3];
		p[0]=StdRandom.uniform(lo+1, hi);
		p[1]=StdRandom.uniform(p[0]+1, hi+1);
		p[2]=StdRandom.uniform(lo, p[0]);
		
		int temp0 = 0,temp1=0;
		if(less(a[p[0]],a[p[1]])) temp1++;
		else temp0++;
		if(less(a[p[1]],a[p[2]]));
		else temp1++;
		if(less(a[p[0]],a[p[2]]));
		else temp0++;
		int v;
		if(temp0==1)
			v=p[0];
		else if(temp1==1)
			v=p[1];
		else
			v=p[2];

		Comparable pick = a[v];
		exch(a,hi,v);
		int i = lo,j = hi;
		while(true) {
			while(less(a[i++],pick));
			while(less(pick,a[--j]))/*if(j==lo) break*/;
			if(i>=j)break;
			exch(a,i,j);
		}
		exch(a,hi,j);
		return j;
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
