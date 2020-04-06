// H343 / Spring 2017 Homework 3 Andrew Mathies awmathie

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Edit {
    public static void main(String[] args) {
	// reading two DNA strings to compare
	String one = "";
	String two = "";
	Scanner s;

	try {
	    s = new Scanner(new File("dna.txt"));
	    one = s.nextLine();
	    two = s.nextLine();
	} catch (FileNotFoundException e) {
	    System.out.println("can't find the file");
	    e.printStackTrace();
	}
	

	// making arrays and DP table
	int m = one.length() + 1, n = two.length() + 1, i = 0, j = 0, c = 0;
	int[][] d = new int[m][n];
	char[] a = new char[m-1];
	char[] b = new char[n-1];

	// setting up two arrays of chars that are the Strings I read
	for (i = 0; i < m-1; i++) {
	    a[i] = one.charAt(i);
	}
	for (j = 0; j < n-1; j++) {
	    b[j] = two.charAt(j);
	}

	//initialization
	for (i = 0; i < m; i++) d[i][0] = i;
	for (j = 0; j < n; j++) d[0][j] = j; 
	//fill the DP table
	for (i = 1; i < m; i++)  {
	    for (j = 1; j < n; j++) {
                if(a[i-1] == b[j-1]) c = 0;
                else c = 1; 
                d[i][j] = min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1]+c);
	    }
	}
	System.out.println("Expected Edit Distance for my example is 6.");
	System.out.println("Edit distance is : " + d[m-1][n-1]);
    }
    
    public static int min(int n1, int n2, int n3) {
	if (n1 < n2 && n1 < n3)
	    return n1;
	else if (n2 < n1 && n2 < n3)
	    return n2;
	else if (n3 < n1 && n3 < n2)
	    return n3;

	// I am assuming the three test cases only fail if the numbers are 
	// all equivalent
	return n1;
    }
}
