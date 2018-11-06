package Exercise_2_3_QuickSort;

import Lesson__2_1_ElementarySorts.SortCompare;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 2.3.16 Best case of Quick.java
 * @author baozzz1 
 * 2018年11月5日
 */
public class BestCase {
	private int[] bestArray;

	public BestCase(int N) {
		bestArray = new int[N];
		for (int i = 0; i < N; i++)
			bestArray[i] = i;
		reverseSort(bestArray, 0, N - 1);
	}

	private void reverseSort(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = (lo + hi) / 2;
		reverseSort(a, lo, mid - 1);
		int temp = a[mid];
		a[mid] = a[lo];
		a[lo] = temp;
		reverseSort(a, mid + 1, hi);
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < bestArray.length; i++)
			res += bestArray[i] + " ";
		return res;
	}

	public int[] getArray() {
		return bestArray;
	}

	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		BestCase bc = new BestCase(alphabet.length());
		StdOut.println("Compare with the book:");
		for(int i=0;i<alphabet.length();i++)
			StdOut.print(alphabet.charAt(bc.getArray()[i]));
		StdOut.println();

		StdOut.println("Compare this array sorted by Quick_NoRandom with random array sorted by Quick:");
		double t1 = SortCompare.timeRandomInput(alg1, N, T);
		double t2 = SortCompare.timeRandomInput(alg2, N, T);
		StdOut.printf("For %d random Doubles\n%s is", N, alg1);
		StdOut.printf(" %.2f times faster than %s\n", t2 / t1, alg2);
	}
}
