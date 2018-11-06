package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Exercise 2.3.20 Quick without recursive.
 * @author baozzz1
 * 2018年11月06日
 */
public class Quick_NoRecursive {
	public static void sort(Comparable[] a) {
		if(a.length<=1)
			return;
		StdRandom.shuffle(a);
		Stack<Integer> temp = new Stack<Integer>();
		temp.push(0);
		temp.push(a.length-1);
		while(!temp.isEmpty()) {
			int right = temp.pop();
			int left = temp.pop();
			if(left<right) {
				int mid = partition(a,left,right);
				if(mid-left>right-mid) {
					temp.push(left);
					temp.push(mid-1);
					temp.push(mid+1);
					temp.push(right);
				}
				else {
					temp.push(mid+1);
					temp.push(right);
					temp.push(left);
					temp.push(mid-1);
				}
			}
		}
	}
	
	private static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo,j = hi +1;
		while(true) {
			while(less(a[++i],v)) if(i==hi) break;
			while(less(v,a[--j])) if(j==lo) break;
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
