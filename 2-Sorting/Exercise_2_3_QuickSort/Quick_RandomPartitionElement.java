package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.29 ReadMe</a>
 * @author baozzz1
 * 2018年11月07日
 */
public class Quick_RandomPartitionElement {
	public static int times = 0;
	
	public static void sort(Comparable[] a) {
		sort(a,0,a.length-1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		int chosen = StdRandom.uniform(lo, hi+1);
		exch(a,lo,chosen);
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
		times++;
		return a.compareTo(b)<0;
	}
	
	public static void main(String[] args) {
		StdOut.println("Please input try times:");
		int Try = Integer.parseInt(StdIn.readLine());
		StdOut.println("M\tN\trandom\tnormal\tratio");
		int[] arrM = { 10, 20, 50 };
		double[] arrN = { 1e3, 1e4, 1e5, 1e6 };
		for (int i = 0; i < arrM.length; i++)
			for (int j = 0; j < arrN.length; j++) {
				long randomSum = 0, normalSum = 0;
				for (int tryTime = 0; tryTime < Try; tryTime++) {
					Comparable[] a = new Comparable[(int) arrN[j]];
					for (int k = 0; k < (int) arrN[j]; k++)
						a[k] = k;
					StdRandom.shuffle(a);
					Comparable[] b = a;
					Quick_RandomPartitionElement qr = new Quick_RandomPartitionElement();
					qr.sort(a);
					randomSum += qr.times;
					Quick_CountCompareTimes qc = new Quick_CountCompareTimes();
					qc.sort(b);
					normalSum += qc.times;
				}
				StdOut.printf("%d\t%.0f\t%d\t%d\t%.4f\n", arrM[i], arrN[j], randomSum / Try, normalSum / Try,
						(double) randomSum / (double) normalSum);
			}
	}
}
