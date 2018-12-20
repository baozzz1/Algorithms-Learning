package Exercise2_BinarySearchTree;

import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.2.13
 * @author baozzz1
 * 2018年12月4日
 */
public class TestNonrecursiveBST {
	public static void main(String[] args) {
		String test = "S E A R C H E X A M P L E";
		String[] keys = test.split(" ");
		int n = keys.length;
		NonrecursiveBST<String, Integer> nrbst = new NonrecursiveBST<String, Integer>();
		for (int i = 0; i < n; i++)
			nrbst.put(keys[i], i);

		for(String s: nrbst.keys()) 
			StdOut.println(s+" "+nrbst.get(s));
	}
}
