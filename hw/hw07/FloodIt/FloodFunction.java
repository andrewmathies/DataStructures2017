// H343 / Spring 2017 Homework 07 Andrew Mathies awmathie

package FloodIt;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class FloodFunction {
    private Driver driver;
    // here floodList is declared as a LinkedList
    // it is declared as public (a bad practice), but it is needed by the Driver class
    public List<Coord> floodList = new LinkedList<Coord>();

    // constructor
    public FloodFunction(Driver newDriver) {
	driver = newDriver;
	// when the game starts, only the cell at the left/top corner is filled
	floodList.add(new Coord(0, 0));

	// if there are other cells connected to the top left corner that are the
	// same color, they will start in the floodlist
	int color = this.driver.getColor(floodList.get(0));
	int size = floodList.size();
	Coord  cur;
	for (int i = 0; i < size; i++) {
	    cur = floodList.get(i);
	    if (inbound(up(cur)) && this.driver.getColor(up(cur)) == color && !floodList.contains(up(cur))) {
		floodList.add(up(cur));
		size++;
	    }
	    if (inbound(left(cur)) && this.driver.getColor(left(cur)) == color && !floodList.contains(left(cur))) {
		floodList.add(left(cur));
		size++;
	    }
	    if (inbound(down(cur)) && this.driver.getColor(down(cur)) == color && !floodList.contains(down(cur))) {
		floodList.add(down(cur));
		size++;
	    }
	    if (inbound(right(cur)) && this.driver.getColor(right(cur)) == color && !floodList.contains(right(cur))) {
		floodList.add(right(cur));
		size++;
	    }
	}
    }

    // flood function is to be implemented by students
    public void flood(int colorToFlood) {
	Coord  cur;
	ArrayList<Coord> toFlood = new ArrayList<Coord>();

	// I tried getting this to work with a while loop and a boolean value that changed to false when
	// there were no more blocks of the new color to add to the list, but for some reason I couldn't
	// get it to work. While I recognize that this solution is pretty bad, it works as long as there
	// isn't a chain of more than 15 blocks of a color being added to the floodList
	for (int count = 0; count < 15; count++) {
	    for (int i = 0; i < floodList.size(); i++) {
		cur = floodList.get(i);
		this.driver.colorOfCoord[cur.y][cur.x] = colorToFlood;
		if (inbound(up(cur)) && this.driver.getColor(up(cur)) == colorToFlood) {
		    toFlood.add(up(cur));
		}
		if (inbound(left(cur)) && this.driver.getColor(left(cur)) == colorToFlood) {
		    toFlood.add(left(cur));
		}
		if (inbound(down(cur)) && this.driver.getColor(down(cur)) == colorToFlood) {
		    toFlood.add(down(cur));
		}
		if (inbound(right(cur)) && this.driver.getColor(right(cur)) == colorToFlood) {
		    toFlood.add(right(cur));
		}
	    }

	    for (int i = 0; i < toFlood.size(); i++) {
		if (!floodList.contains(toFlood.get(i)))
		    floodList.add(toFlood.get(i));
	    }
	    toFlood.clear();
	}
    }

    // is input cell (specified by coord) on the board?
    public boolean inbound(final Coord coord) {
	final int x = coord.x;
	final int y = coord.y;
	final int size = this.driver.size;
	return x > -1 && x < size && y > -1 && y < size;
    }
    // return the coordinate of the cell on the top of the given cell (coord)
    public Coord up(final Coord coord) {
	return new Coord(coord.x, coord.y-1);
    }
    // return the coordinate of the cell below the given cell (coord)
    public Coord down(final Coord coord) {
	return new Coord(coord.x, coord.y+1);
    }
    // return the coordinate of the cell to the left of the given cell (coord)
    public Coord left(final Coord coord) {
	return new Coord(coord.x-1, coord.y);
    }
    // return the coordinate of the cell to the right of the given cell (coord)
    public Coord right(final Coord coord) {
	return new Coord(coord.x + 1, coord.y);
    }
    // get the color of a cell (coord)
    public int colorOfCoord(final Coord coord) {
	return this.driver.getColor(coord);
    }
}
