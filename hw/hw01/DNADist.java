// H343 / Spring 2017 Homework 01 Andrew Mathies awmathie

import java.util.*;

public class DNADist {    
    public static void main(String[] args) {
	// making two random DNA strings
	String DNA1 = generateDNA();
	String DNA2 = generateDNA();
	// calculating and printing hamming ditance
	System.out.println("DNA1: " + DNA1 + "  DNA2: " + DNA2);
	System.out.println("Hamming distance is: " + hamming(DNA1, DNA2));
	// making an intentional test for hamming method
	String DNA3 = "ATGA";
	String DNA4 = "ATCC";
	System.out.println("DNA3: ATG  DNA4: ATC, expected hamming distance is 3.");
	System.out.println("Hamming distance is: " + hamming(DNA3, DNA4));
    }
    
    public static String generateDNA() {
	String s = "";
	Random gen = new Random();
	int n = 0;
	for (int i = 0; i < 20; i++) {
	    n = gen.nextInt(4);
	    if (n == 3)
		s += "A";
	    else if (n == 2)
		s += "T";
	    else if (n == 1)
		s += "C";
	    else
		s += "G";
	}
	// Outputs the final string
	return s;
    }
    
    public static int hamming(String dna1, String dna2) {
	int distance = 0;
	for (int i = 0; i < dna1.length(); i++) {
	    distance += Math.abs(translate(dna1.charAt(i)) - translate(dna2.charAt(i)));
	}
	return distance;
    }
   
    public static int translate(char in) {
	if (in == 'A')
	    return 3;
	else if (in == 'T')
	    return 2;
	else if (in == 'C')
	    return 1;
	else
	    return 0;
    }
}
