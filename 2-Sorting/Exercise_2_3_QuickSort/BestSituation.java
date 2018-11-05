package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 2.3.16 Best situation of Quick.java
 * @author baozzz1
 * 2018年11月5日
 */
public class BestSituation {
	public static Comparable[] getBestArray(int N) {
		Comparable[] a = new Comparable[N];
		for(int i=0;i<N;i++)
			a[i] = i;
		reverseSort(a,0,N-1);
		return a;
	}

	private static void reverseSort(Comparable[] a, int lo,int hi) {
		if(lo>=hi)
			return;
		int mid = (lo+hi)/2;
		Comparable temp = a[mid];
		for(int i=mid;i>lo;i--)
			a[i] = a[i - 1];
		a[lo] = temp;
		//print(a);
		reverseSort(a,lo+1,mid);
		reverseSort(a,mid+1,hi);
	}
	private static void print(Comparable[] a) {
		for(int i = 0;i<a.length;i++)
			StdOut.print(a[i]+" ");
		StdOut.println();
	}
	public static void main(String[] args) {
		Comparable[] a = getBestArray(Integer.parseInt(StdIn.readLine()));
		Quick_NoRandom.sort(a);
	}
}
