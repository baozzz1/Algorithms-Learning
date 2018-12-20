package Exercise2_BinarySearchTree;

import Lesson2_BinarySearchTree.BST;
import Lesson__2_3_QuickSort.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 3.2.25
 * @author baozzz1
 * 2018年12月4日
 */
public class PerfectBalance {
    // precondition: a[] has no duplicates
    private static void perfect(BST bst, String[] a) {
        Quick.sort(a);
        perfect(bst, a, 0, a.length - 1);
        StdOut.println();
    }

    // precondition: a[lo..hi] is sorted
    private static void perfect(BST bst, String[] a, int lo, int hi) {
        if (hi < lo) return;
        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);
        StdOut.print(a[mid] + " ");
        perfect(bst, a, lo, mid-1);
        perfect(bst, a, mid+1, hi);
    }

    public static void main(String[] args) {
        String[] words = StdIn.readAllStrings();
        BST<String, Integer> bst = new BST<String, Integer>();
        perfect(bst, words);
    }
}
