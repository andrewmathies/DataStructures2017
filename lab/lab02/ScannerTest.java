// H343 / Spring 2017 Lab Mini-Assignments 02 Andrew Mathies awmathie

import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.net.URL;

public class ScannerTest {
    public static void main(String[] args) throws IOException {
	Scanner s = new Scanner(System.in);
	System.out.println("If you would like to scan via URL, enter \"URL\" , if you would like to scan via file enter \"File\" .");
	String in = s.nextLine();
	if (in.contains("URL")) {
	    URL url = new URL("http://homes.soic.indiana.edu/classes/spring2017/csci/h343-mitja/test2017/tweet-data-Jan19.txt");
	    s = new Scanner(url.openStream());
	} else if (in.contains("File")) {
	    s = new Scanner(new FileReader("tweet-data-Jan19.txt"));
	} else {
	    System.out.println("OK well cya!");
	    return;
	}

	while(s.hasNext()) {
	    String line = s.nextLine();
	    System.out.println(line);
	}

	s.close();
    }
}
