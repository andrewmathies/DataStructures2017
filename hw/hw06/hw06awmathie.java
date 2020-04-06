// H343 / Spring 2017 HW06 Andrew Mathies awmathie

import java.util.*;
import java.io.*;

public class hw06awmathie {

    private Hashtable<String, ArrayList<Integer>> lines;
    private Scanner s;

    public hw06awmathie(String file) {
	lines = new Hashtable<String, ArrayList<Integer>>();
	try {
	    s = new Scanner(new File(file));
	} catch(FileNotFoundException e) {
	    System.out.println("couldn't find the file dude");
	}

	int line = 1, i;
	String[] words;
	ArrayList<Integer> temp;

	while (s.hasNextLine()) {
	    words = s.nextLine().split(" ");
	    for (i = 0; i < words.length; i++) {
		if (words[i].contains("."))
		    words[i].replace(".", "");		
		if (lines.contains(words[i])) {
		    temp = lines.get(words[i]);
		    temp.add(line);
		    lines.replace(words[i], temp);
		} else {
		    temp = new ArrayList<Integer>();
		    temp.add(line);
		    lines.put(words[i], temp);
		}
	    }
	    line++;
	}
    }

    public ArrayList<Integer> query(String word) {
	if (lines.contains(word))
	    return lines.get(word);
	else
	    return new ArrayList<Integer>();
    }

    public static void main(String[] args) {
	hw06awmathie test = new hw06awmathie("hw-06-testing.txt");

	System.out.println("This does currently not work, but I think I am close to a solution.");

	System.out.println("Testing word \"algorithm\", expecting []");
	System.out.println(test.query("algorithm"));
	System.out.println("Testing word \"data\", expecting [3, 5, 6, 9, 11, 15, 17, 19]");
	System.out.println(test.query("data"));
    }
}
