package Exercise2_BinarySearchTree;

import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.2.10
 * @author baozzz1 
 * 2018年12月4日
 */
public class TestBST {
	public static void main(String[] args) {
		String test = "S E A R C H E X A M P L E";
		String[] keys = test.split(" ");
		int n = keys.length;
		BST<String, Integer> bst = new BST<String, Integer>();
		for (int i = 0; i < n; i++)
			bst.put(keys[i], i);

		StdOut.println("Test keys():");
		for (String s : bst.keys())
			StdOut.print(s + " " + bst.get(s) + "\t");
		StdOut.println();
		String min = bst.min();
		String max = bst.max();
		StdOut.println("Test min() and max(): min() is " + min + ", max() is " + max);
		String floor = bst.floor("B");
		String ceiling = bst.ceiling("W");
		String select = bst.select(0);
		int rank = bst.rank("S");
		StdOut.println("Test floor(): floor(\"B\") is " + floor);
		StdOut.println("Test ceiling(): ceiling(\"W\") is " + ceiling);
		StdOut.println("Test select(): select(0) is " + select);
		StdOut.println("Test rank(): rank(\"S\") is " + rank);

		bst.delete("S");
		StdOut.println("Test delete(\"S\"):");
		for (String s : bst.keys())
			StdOut.print(s + " " + bst.get(s) + "\t");
		StdOut.println();

		bst.deleteMin();
		StdOut.println("Test deleteMin():");
		for (String s : bst.keys())
			StdOut.print(s + " " + bst.get(s) + "\t");
		StdOut.println();

		bst.deleteMax();
		StdOut.println("Test deleteMax():");
		for (String s : bst.keys())
			StdOut.print(s + " " + bst.get(s) + "\t");
		StdOut.println();

	}
}
