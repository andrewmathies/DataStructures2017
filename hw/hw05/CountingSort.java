// H343 / Spring 2017 HW05 Andrew Mathies awmathie


// need this to randomly generate numbers, as well as print out arrays quickly
import java.util.*;

public class CountingSort { 
    // I am associating the starting list of randomly generated numbers with the class itself,
    // here is that variable as well as the maximum value found in that starting list
    private int max;
    private int[] A;
    
    // constructor for class, takes in a desired size of randomly generated list
    public CountingSort(int size) {
	// we need to make this to randomly generate numbers
	Random gen = new Random();
	// we use these to calculate the maximum value in the list easily
	int temp = 0, max = 0;
	// initializing the list
	A = new int[size];
	
	// this loop randomly generates numbers, checks to see if the new number is larger than
	// any of the previously made numbers, and then puts the number into the starting array
	for (int i = 0; i < A.length; i++) {
	    // randomly generating a number between 0 and the length of the list
	    temp = gen.nextInt(A.length) + 1;
	    // checking to see if newly generated number is bigger than our current max
	    if (temp > max)
		max = temp;

	    // putting the randomly generated number into the array
	    A[i] = temp;
	}

	// setting the instance variable max to be what we calculated as the largest number
	this.max = max;
    }

    // getter method for max instance variable
    public int getMax() {
	return this.max;
    }

    // getter method for the list of randomly generated numbers
    public int[] getList() {
	return this.A;
    }

    // our sorting algorithm
    public int[] sort() {
	// here we make two lists, one for calculating how many times a number occurs, and
	// other as our final sorted output list
	int[] B = new int[A.length + 1], C = new int[A.length];
	
	// we use i to go through the various arrays, we will use index in stage 3
	int i = 0, index = 0;
	
	// stage 1, go through all elements of A, for each element, increment the element in
	// B whose index is equal to the value of the element in A
	for (i = 0; i < A.length; i++)
	    B[A[i]]++;

	// stage 2, go through B, add together the current elements value with the previous
	// elements value, that is the new value for the current element
	for (i = 1; i < B.length; i++)
	    B[i] += B[i - 1];

	// stage 3, here we go through A, find the element in B whose index is equal to the
	// value of the current element in A. Then set index to be the value of that element
	// in B. Then set the element in C whose index is equal to 1 less than our variable
	// index to be equal to the value of our current element in A. Finally, decrement the
	// value of the element in B whose index is equal to the value of our current element
	// in A.
	for (i = 0; i < C.length; i++) {
	    index = B[A[i]];
	    C[index - 1] = A[i];
	    B[A[i]]--;
	}
	
	// return the sorted array
	return C;
    }

    public static void main(String[] args) {
	// this is how many elements I want to be in the array we are sorting
	int size = 20;
	// just making an instance of our sorting class
	CountingSort test = new CountingSort(size);
	// initializing our output sorted array
	int[] C = new int[size];
	
	// set our output sorted array with our sort method
	C = test.sort();
	
	// this is just printing out the unsorted array, the sorted array, and the max value
	// in either array, as well as relevant information so the user knows what we are 
	// printing out
	System.out.println("The unsorted list:");
	System.out.println(Arrays.toString(test.getList()));
	System.out.println("The sorted list:");
	System.out.println(Arrays.toString(C));
	System.out.println("The maximum key found in A:");
	System.out.println(test.getMax());
    }
}
