package Lesson__2_3_QuickSort;

import java.lang.System;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import Lesson__2_1_ElementarySorts.Insertion;
/**
 * Exercise 2.3.25
 * @author baozzz1 
 * 2018年10月29日
 */
public class Quick2Insert {
	public static void sort(Comparable[] a, int M) {
		if (a.length < M)
			Insertion.sort(a);
		else {
			StdRandom.shuffle(a);
			sort(a, 0, a.length - 1, M);
		}
	}

	private static void sort(Comparable[] a, int lo, int hi, int M) {
		if (hi <= lo)
			return;
		if (hi - lo + 1 < M)
			Insertion.sort(a, lo, hi);
		else {
			int j = partition(a, lo, hi);
			sort(a, lo, j - 1, M);
			sort(a, j + 1, hi, M);
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

	private static Comparable[] randomArray(int N) {
		Comparable[] a = new Comparable[N];
		for (int i = 0; i < a.length; i++)
				a[i] = StdRandom.uniform(1000000000);
		return a;
	}
	
	public static void main(String[] args) {
		int arrayLength = (int) 1e3;
		int M = 0,minM = 0;
		long minTime = Long.MAX_VALUE;
		Comparable[] a = randomArray(arrayLength);
		for(;M<=30;M++) {
			Comparable[] test = a;
			long start = System.currentTimeMillis();
			Quick2Insert.sort(test,M);
			long end =  System.currentTimeMillis();
			long time = end-start;
			if(time<minTime) {
				minTime = time;
				minM = M;
			}
			StdOut.println("When the M is "+M+", sort length "+arrayLength+" array, time is "+time);
		}
		
	}
}
