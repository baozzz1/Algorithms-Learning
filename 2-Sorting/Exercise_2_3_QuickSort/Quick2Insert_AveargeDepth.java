package Exercise_2_3_QuickSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import Lesson__2_1_ElementarySorts.Insertion;

/**
 * <a href="https://github.com/baozzz1/Algorithms-Learning/blob/master/2-Sorting/Exercise_2_3_QuickSort/2-3-Exercise-README.md">
 * Exercise 2.3.28 ReadMe</a>
 * @author baozzz1 
 * 2018年11月06日
 */
public class Quick2Insert_AveargeDepth {	
	public static int sort(Comparable[] a, int M) {
		int depth = 1;
		if (a.length < M)
			Insertion.sort(a);
		else {
			StdRandom.shuffle(a);
			depth = sort(a, 0, a.length - 1, M, depth);
		}
		return depth;
	}

	private static int sort(Comparable[] a, int lo, int hi, int M, int depth) {
		if (hi <= lo)
			return depth;
		if (hi - lo + 1 < M) {
			Insertion.sort(a, lo, hi);
			return depth;
		}
		else {
			int j = partition(a, lo, hi);
			int left = sort(a, lo, j - 1, M, depth+1);
			int right = sort(a, j + 1, hi, M, depth+1);
			return less(left,right)?right:left;
		}
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo, j = hi + 1;
		while (true) {
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	public static void main(String[] args) {
		StdOut.println("Please input try times:");
		int Try = Integer.parseInt(StdIn.readLine());
		StdOut.println("M\tN\tDepth");
		int[] arrM = { 10, 20, 50 };
		double[] arrN = { 1e3, 1e4, 1e5, 1e6 };
		for (int i = 0; i < arrM.length; i++)
			for (int j = 0; j < arrN.length; j++) {
				Comparable[] a = new Comparable[(int) arrN[j]];
				for (int k = 0; k < (int) arrN[j]; k++)
					a[k] = k;
				int sum = 0;
				for (int tryTime = 0; tryTime < Try; tryTime++)
					sum+=Quick2Insert_AveargeDepth.sort(a, arrM[i]);
				StdOut.printf("%d\t%.0f\t%d\n", arrM[i], arrN[j], sum/Try);
			}
	}
}
