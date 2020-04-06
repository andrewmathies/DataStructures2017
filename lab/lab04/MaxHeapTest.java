// H343 / Spring 2017 Lab 04 Andrew Mathies awmathie

//import java.util.Comparator;
//import java.util.PriorityQueue;
import java.lang.Comparable;
import java.util.*;
import java.math.*;

public class MaxHeapTest<E extends Comparable<? super E>> {
    /*
    // define two Comparator classes, a descending one and an ascending one:
    static class PQsortDescend implements Comparator<Integer> {
  
        public int compare(Integer first, Integer second) {
            return second - first;
        }
    }
     
    static class PQsortAscend implements Comparator<Integer> {
          
        public int compare(Integer first, Integer second) {
            return first - second;
        }
    }
    */

    // fields
    private E[] Heap;
    private int size;
    private int n;

    /** Constructor supporting preloading of heap contents */
    public MaxHeapTest(E[] h, int num, int max) {
	Heap = h;
	n = num;
	size = max;
	buildheap();
    }

    /** @return Current size of the heap */
    public int heapsize() { return n; }

    /** @return True if pos a leaf position, false otherwise */
    public boolean isLeaf(int pos)
    { return (pos >= n/2) && (pos < n); }

    /** @return Position for left child of pos */
    public int leftchild(int pos) {
	assert pos < n/2 : "Position has no left child";
	return 2*pos + 1;
    }

    /** @return Position for right child of pos */
    public int rightchild(int pos) {
	assert pos < (n-1)/2 : "Position has no right child";
	return 2*pos + 2;
    }

    /** @return Position for parent */
    public int parent(int pos) {
	assert pos > 0 : "Position has no parent";
	return (pos-1)/2;
    }

    /** Insert val into heap */
    public void insert(E val) {
	assert n < size : "Heap is full";
	int curr = n++;
	Heap[curr] = val;             // Start at end of heap
	// Now sift up until curr's parent's key > curr's key
	while ((curr != 0)  &&
	       (Heap[curr].compareTo(Heap[parent(curr)]) > 0)) {
	    DSutil.swap(Heap, curr, parent(curr));
	    curr = parent(curr);
	}
    }
    /** Heapify contents of Heap */
    public void buildheap()
    { for (int i=n/2-1; i>=0; i--) siftdown(i); }

    /** Put element in its correct place */
    private void siftdown(int pos) {
	assert (pos >= 0) && (pos < n) : "Illegal heap position";
	while (!isLeaf(pos)) {
	    int j = leftchild(pos);
	    if ((j<(n-1)) && (Heap[j].compareTo(Heap[j+1]) < 0)) 
		j++; // j is now index of child with greater value
	    if (Heap[pos].compareTo(Heap[j]) >= 0) return;
	    DSutil.swap(Heap, pos, j);
	    pos = j;  // Move down
	}
    }

    /** Remove and return maximum value */
    public E removemax() {
	assert n > 0 : "Removing from empty heap";
	DSutil.swap(Heap, 0, --n); // Swap maximum with last value
	if (n != 0)      // Not on last element
	    siftdown(0);   // Put new heap root val in correct place
	return Heap[n];
    }

    /** Remove and return element at specified position */
    public E remove(int pos) {
	assert (pos >= 0) && (pos < n) : "Illegal heap position";
	if (pos == (n-1)) n--; // Last element, no work to be done
	else
	    {
		DSutil.swap(Heap, pos, --n); // Swap with last value
		// If we just swapped in a big value, push it up
		while ((pos > 0) &&
		       (Heap[pos].compareTo(Heap[parent(pos)]) > 0)) {
		    DSutil.swap(Heap, pos, parent(pos));
		    pos = parent(pos);
		}
		if (n != 0) siftdown(pos); // If it is little, push down
	    }
	return Heap[n];
    }    


    // 
    // main program to test the PriorityQueue class:
    //
    public static void main(String[] args) {
	// delcaring and filling our array
        Integer[] myStartArray = { new Integer(1), new Integer(10), new Integer(5), new Integer(3), new Integer(4), new Integer(7), new Integer(6), new Integer(9), new Integer(8)};
        System.out.print("myStartArray: [ ");

	// printing out the starting array
        for (Integer element : myStartArray) {
            System.out.print(element + " ");
        }
        System.out.println("]");

	// randomize the array
	DSutil.permute(myStartArray);

	// making a maxHeap out of the array, then printing out the maxHeap
        MaxHeapTest<Integer> myQueue1 = new MaxHeapTest<Integer>(myStartArray, 9, 9);
        System.out.print("myQueue1: [ ");
	for (int i = 0; i < myQueue1.size; i++) {
	    System.out.print(myQueue1.remove(0) + " ");
	}
	System.out.print(" ]\n");

	// doing the same thing again, randomizing array then building and printing a heap
	DSutil.permute(myStartArray);	
	MaxHeapTest<Integer> myQueue2 = new MaxHeapTest<Integer>(myStartArray, 9, 9);
	System.out.print("myQueue2: [ ");
	for (int i = 0; i < myQueue2.size; i++) {
	    System.out.print(myQueue2.removemax() + " ");
	}
	System.out.print(" ]\n");
    } // end of main()
}


/** A bunch of utility functions. */
class DSutil<E> {

    /** Swap two Objects in an array
      @param A The array
      @param p1 Index of one Object in A
      @param p2 Index of another Object A
    */
    public static <E> void swap(E[] A, int p1, int p2) {
	E temp = A[p1];
	A[p1] = A[p2];
	A[p2] = temp;
    }

    /** Randomly permute the Objects in an array.
      @param A The array
    */

    // int version
    // Randomly permute the values of array "A"
    static void permute(int[] A) {
	for (int i = A.length; i > 0; i--) // for each i
	    swap(A, i-1, DSutil.random(i));  //   swap A[i-1] with
    }                                    //   a random element

    public static void swap(int[] A, int p1, int p2) {
	int temp = A[p1];
	A[p1] = A[p2];
	A[p2] = temp;
    }

    /** Randomly permute the values in array A */
    static <E> void permute(E[] A) {
	for (int i = A.length; i > 0; i--) // for each i
	    swap(A, i-1, DSutil.random(i));  //   swap A[i-1] with
    }                                    //   a random element
    /** @return The minimum and maximum values in A
	between positions l and r */
    static void MinMax(int A[], int l, int r, int Out[]) {
	if (l == r) {        // n=1
	    Out[0] = A[r];
	    Out[1] = A[r];
	}
	else if (l+1 == r) { // n=2
	    Out[0] = Math.min(A[l], A[r]);
	    Out[1] = Math.max(A[l], A[r]);
	}
	else {               // n>2
	    int[] Out1 = new int[2];
	    int[] Out2 = new int[2];
	    int mid = (l + r)/2;
	    MinMax(A, l, mid, Out1);
	    MinMax(A, mid+1, r, Out2);
	    Out[0] = Math.min(Out1[0], Out2[0]);
	    Out[1] = Math.max(Out1[1], Out2[1]);
	}
    }

    /** Initialize the random variable */
    static private Random value = new Random(); // Hold the Random class object

    /** Create a random number function from the standard Java Random
      class. Turn it into a uniformly distributed value within the
      range 0 to n-1 by taking the value mod n.
      @param n The upper bound for the range.
      @return A value in the range 0 to n-1.
    */
    static int random(int n) {
	return Math.abs(value.nextInt()) % n;
    }

}
