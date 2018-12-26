package Exercise4_HashTables;

import Lesson4_HashTables.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Exercise 3.4.36
 * @author baozzz1
 * 2018年12月26日
 */
public class Exercise36_RangeOfListLength {
	public static void main(String[] args) {
		int N = (int) 1e3;
		for(;N<=1e6;N=N*10) {
			SeparateChainingHashST<Integer,Integer> sch;
			sch = new SeparateChainingHashST<Integer,Integer>(N/100);
			int[] a = new int[N];
			for(int i=0;i<N;i++)
				sch.put(StdRandom.uniform(Integer.MAX_VALUE), i);
			int maxLength = Integer.MIN_VALUE;
			int minLength = Integer.MAX_VALUE;
			for(int i=0;i<N/100;i++) {
				int length = sch.st[i].size();
				if(length>maxLength)
					maxLength = length;
				if(length<minLength)
					minLength = length;
			}
			StdOut.println("N is "+N+", and the max size of list is "+maxLength+"; the min size of list is "+minLength);
		}
	}
}
/*****
 * Do not resize the SeparateChainingHashST.
 * N is 1000, and the max size of list is 115; the min size of list is 81
 * N is 10000, and the max size of list is 120; the min size of list is 74
 * N is 100000, and the max size of list is 132; the min size of list is 65
 * N is 1000000, and the max size of list is 141; the min size of list is 64
*****/


