package Exercise_2_3_QuickSort;

import Lesson__2_3_QuickSort.Quick;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.24 ReadMe</a>
 * @author baozzz1
 * 2018年11月6日
 */
public class SampleSort {
	public static void sort(Comparable[] a, int K) {
		int sampleNum = (int) (Math.pow(2, K)-1);
		if(a.length<sampleNum) {
			Quick.sort(a);
			return;
		}
		
		StdRandom.shuffle(a);
		Quick.sort(a,0,sampleNum-1);
		
		int sampleMedian = sampleNum/2;
		int i = sampleNum-1,j = a.length-1;
		while(i!=sampleMedian)
			exch(a,i--,j--);
		sort(a,0,sampleMedian,j,a.length-1);
	}
	
	private static void sort(Comparable[] a, int sampleLo, int lo, int hi, int sampleHi) {
		if(hi<=lo)
			return;
		int j = partition(a,lo,hi);
		
		if(lo-sampleLo>1) {
			int p =lo-1,v=j-1;
			for(int i = 0;i<(lo-sampleLo)/2;i++)
				exch(a,p--,v--);
			sort(a,sampleLo,p,v,j-1);
		}
		else
			Quick.sort(a, sampleLo, j-1);
		
		if(sampleHi - hi>1) {
			int p = hi,v = j;
			for(int i =0;i<(sampleHi-hi)/2;i++)
				exch(a,++p,++v);
			sort(a,j+1,v,p,sampleHi);
		}
		else
			Quick.sort(a, j+1, sampleHi);
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
