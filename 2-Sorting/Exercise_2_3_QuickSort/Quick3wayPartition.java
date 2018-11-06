package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.22 ReadMe</a>
 * @author baozzz1
 * 2018年11月06日
 */
public class Quick3wayPartition {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		int n=hi-lo+1;
		 int eps = n/8;
            int mid = lo + n/2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi); 
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
		
		if (hi <= lo)
			return;
		int p = lo, q = hi + 1;
		int i = lo, j = hi + 1;
		Comparable v = a[lo];
		while(true) {
			while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            
            if(i==j && eq(a[i],v))
            	exch(a,++p,i);
            if(i>=j) break;
            
            exch(a,i,j);
            if(eq(a[i],v)) exch(a,++p,i);
            if(eq(a[j],v)) exch(a,--q,j);
		}
		i=j+1;
		for(int k=lo;k<=p;k++)
			exch(a,k,j--);
		for(int k=hi;k>=q;k--)
			exch(a,k,i++);
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	   // return the index of the median element among a[i], a[j], and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0;
	}
	
	 // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        if (v == w) return true;    // optimization when reference equal
        return v.compareTo(w) == 0;
    }
}
