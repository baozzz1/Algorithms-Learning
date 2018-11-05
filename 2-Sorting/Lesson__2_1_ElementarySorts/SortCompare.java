package Lesson__2_1_ElementarySorts;

import Exercise_2_3_QuickSort.BestSituation;
import Exercise_2_3_QuickSort.Quick_NoRandom;
import Lesson__2_3_QuickSort.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 
 * @author baozzz1
 * 2018年11月5日
 */
public class SortCompare {
	public static double time(String alg, int N) {
		Double[] a = new Double[N];
		for(int i=0;i<N;i++)
			a[i] = StdRandom.uniform();
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		if(alg.equals("Quick")) Quick.sort(a);
		if(alg.equals("BestSituation")) Quick_NoRandom.sort(BestSituation.getBestArray(N));
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
		double t1 = timeRandomInput(alg1,N,T);
		double t2 = timeRandomInput(alg1,N,T);
		StdOut.printf("For %d random Doubles\n	%s is",N,alg1);
		StdOut.printf(" %.2f times faster than %s\n",t2/t1,alg2);
	}
}
