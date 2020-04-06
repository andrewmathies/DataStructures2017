// H343 / Spring 2017 Lab03 Andrew Mathies awmathie

import java.util.*;

public class MyCardSet implements CardSet {
    private ArrayList<String> cards;

    public void initialize() {
	cards = new ArrayList<String>();
	String letter = "";

	for (int i = 0; i < 4; i++) {
	    if (i == 0)
		letter = "S";
	    else if (i == 1)
		letter = "H";
	    else if (i == 2)
		letter = "D";
	    else
		letter = "C";

	    for (int j = 2; j < 11; j++) {
		cards.add(j + letter);
	    }
	    cards.add("J" + letter);
	    cards.add("Q" + letter);
	    cards.add("K" + letter);
	    cards.add("A" + letter);
	}
    }

    public MyCardSet() {
	initialize();
    }

    public String drawRandomCard() {
	int size = cards.size();
	Random gen = new Random();
	int index = gen.nextInt(size) + 1;
	return cards.get(index);
    }

    public String drawHand(int size) {
	ArrayList<String> temp = new ArrayList<String>();
	Random gen = new Random();
	int index = 0;
	String output = "";

	for (int i = 0; i < size; i++) {
	    index = gen.nextInt(cards.size()) + 1;
	    output += cards.get(index) + " ";
	    temp.add(cards.get(index));
	    cards.remove(index);
	}

	// this makes sure the deck always ends up having 52 cards in it
	for (int i = 0; i < temp.size(); i++)
	    cards.add(temp.get(i));
	return output;
    }

    public static void main(String[] args) {
	MyCardSet a = new MyCardSet();
	System.out.println("Here is a random card from my deck: " + a.drawRandomCard());
	System.out.println("Here's a hand of five cards from my deck: " + a.drawHand(5));
	System.out.println("Here is my whole deck: ");
	System.out.println();
	for (int i = 0; i < a.cards.size(); i++)
	    System.out.print(a.cards.get(i) + " ");
    }
}
