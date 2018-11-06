package Lesson__2_1_ElementarySorts;

import Exercise_2_3_QuickSort.BestCase;
import Exercise_2_3_QuickSort.Quick2Insert_IgnoreLittleArray;
import Exercise_2_3_QuickSort.Quick_3PickWithSentinel;
import Exercise_2_3_QuickSort.Quick_NoRandom;
import Exercise_2_3_QuickSort.Quick_NoRecursive;
import Exercise_2_3_QuickSort.Quick_Sentinel;
import Exercise_2_3_QuickSort.SampleSort;
import Lesson__2_3_QuickSort.Quick;
import Lesson__2_3_QuickSort.Quick2Insert;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author baozzz1
 * 2018年11月5日
 */
public class SortCompare {
	public static double time(String alg, int N) {
		Double[] a = new Double[N];
		BestCase bc = new BestCase(N);
		if(alg.equals("BestCase"))
			for(int i=0;i<N;i++)
				a[i] = (double) bc.getArray()[i];
		else
			for(int i=0;i<N;i++)
				a[i] = StdRandom.uniform();
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		if(alg.equals("Quick")) Quick.sort(a);
		if(alg.equals("Quick_NoRecursive")) Quick_NoRecursive.sort(a);
		if(alg.equals("BestCase")) Quick_NoRandom.sort(a);
		if(alg.equals("Quick_Sentinel")) Quick_Sentinel.sort(a);
		if(alg.equals("Quick_3PickWithSentinel")) Quick_3PickWithSentinel.sort(a,15);
		if(alg.equals("SampleSort")) SampleSort.sort(a, 7);
		if(alg.equals("Quick2Insert")) Quick2Insert.sort(a, 15);
		if(alg.equals("Quick2Insert_IgnoreLittleArray")) Quick2Insert_IgnoreLittleArray.sort(a, 6);
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0.0;
		for(int t = 0;t<T;t++) {
			total+=time(alg,N);
		}
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		StdOut.printf("For %d random Doubles\n%s is", N, alg1);
		StdOut.printf(" %.2f times faster than %s\n", t2 / t1, alg2);
	}
}
