// H343 / Spring 2017 Lab 04 Andrew Mathies awmathie

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueSortTest {

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

    static String comparator = "";

    static int THRESHOLD = 0;

    static int ARRAYSIZE = 0;

    static void Sort(Integer[] A) {
	heapsort(A);
    }

    // fix so loops actually sort
    static void heapsort(Integer[] A) {
	// The heap constructor invokes the buildheap method
	if (comparator.equals("ascend")) {
	    PQsortAscend pqs = new PQsortAscend();
	    PriorityQueue<Integer> H = new PriorityQueue<Integer>(A.length, pqs);
	    for (Integer element : A)
		H.offer(element);
	    for (int i = 0; i < A.length; i++)  // Now sort
		A[i] = H.poll(); // Removemax places max at end of heap
	}
	else {
	    PQsortDescend pqs = new PQsortDescend();
	    PriorityQueue<Integer> H = new PriorityQueue<Integer>(A.length, pqs);
	    for (Integer element : A)
		H.offer(element);
	    for (int i = 0; i < 0; i++)  // Now sort
		A[i] = H.poll(); // Removemax places max at end of heap
	}
    }

    public static void main(String args[]) throws IOException {
	assert args.length >= 1 : "Usage: Sortmain [+/-] <size> <threshold>";

	System.out.println("Args: " + args.length);
	int i;
	int input = 0;
	int currarg = 0;
	if (args[currarg].charAt(0) == '-') { comparator = "descend"; currarg++; }
	else if (args[currarg].charAt(0) == '+') { comparator = "ascend"; currarg++; }
	else { comparator = "ascend"; }

	ARRAYSIZE = Integer.parseInt(args[currarg++]);
	if (args.length > currarg)
	    THRESHOLD = Integer.parseInt(args[currarg]);


	Integer[] A = new Integer[ARRAYSIZE];

	System.out.println("Input: " + input +
			   ", size: " + ARRAYSIZE + ", threshold: " + THRESHOLD);
	
	/*
	if (comparator.equals("descend"))
	    for (i=0; i<ARRAYSIZE; i++)
		A[i] = new Integer(ARRAYSIZE - i);  // Reverse sorted
	else
	    for (i=0; i<ARRAYSIZE; i++)
		A[i] = new Integer(i);              // Sorted
	*/
	for (i=0; i<ARRAYSIZE; i++)
	    A[i] = new Integer(DSutil.random(32003));  // Random


	long time1 = System.currentTimeMillis();
	Sort(A);
	long time2 = System.currentTimeMillis();
	System.out.println("Time it took to sort: " + (time2-time1));

	//System.out.println("Done sorting");

	if (comparator.equals("descend")) {
	    for (i=1; i<ARRAYSIZE; i++)
		if (A[i-1].compareTo(A[i]) < 0) {
		    System.out.println("OOPS -- bad sort algorithm!");
		    return;
		}
	} else {
	    for (i=1; i<ARRAYSIZE; i++)
		if (A[i-1].compareTo(A[i]) > 0) {
		    System.out.println("OOPS -- bad sort algorithm!");
		    return;
		}
	}

	System.out.print("[ ");
	for (i = 0; i < ARRAYSIZE; i++)
	    System.out.print(A[i] + " ");
	System.out.print("]\n");
	System.out.println("Sorting checked out OK.");

	//  System.in.read();
    }

}
