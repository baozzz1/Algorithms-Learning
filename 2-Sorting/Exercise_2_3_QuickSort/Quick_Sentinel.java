package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.17 Sentinel</a><br>
 * Result: this Algorithm Quick_Sentinel is 17% faster than Quick.
 * @author baozzz1
 * 2018年11月06日
 */
public class Quick_Sentinel {
	public static void sort(Comparable[] a) {
		if(a.length==0 || a.length==1)
			return;
		StdRandom.shuffle(a);
		Comparable max = a[0];
		int maxIndex=0;
		for(int i=1;i<a.length;i++)
			if(less(max,a[i])) {
				maxIndex = i;
				max=a[i];
			}
		exch(a,maxIndex,a.length-1);
		sort(a,0,a.length-1);
	}
	
	//a[hi] is a sentinel, actually it sorts a[lo] to a[hi-1]
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi - 1 <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j);
		sort(a, j + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo,j = hi;
		while(true) {
			while(less(a[++i],v));
			while(less(v,a[--j]));
			if(i>=j)break;
			exch(a,i,j);
		}
		exch(a,lo,j);
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
