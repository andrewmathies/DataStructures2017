// H343 / Spring 2017 Homework 01 Andrew Mathies awmathie

import java.util.Random;

public class Int2DArray2 implements Int2DArray {
    private int[][] arr;
    private int width, height;

    public Int2DArray2(int width, int height) {
	this.arr = new int[width][height];
	this.width = width;
	this.height = height;
    }

    public void insert(int value, int row, int col) {
	this.arr[row][col] = value;
    }

    public void wipe() {
	for (int i = 0; i < this.width; i++)
	    for (int j = 0; j < this.height; j++)
		this.arr[i][j] = 0;
    }

    public void remove(int row, int col) {
	this.arr[row][col] = 0;
    }

    public int get(int row, int col) {
	return this.arr[row][col];
    }

    public boolean empty() {
        for (int i = 0; i < this.width; i++)
	    for (int j = 0; j < this.height; j++)
		if (this.arr[i][j] != 0)
		    return false;
	return true;
    }

    public boolean subArrayEmpty(int row) {
	for (int i = 0; i < this.height; i++)
	    if (this.arr[row][i] != 0)
		return false;
	return true;
    }

    public int width() {
	return this.width;
    }

    public int height() {
	return this.height;
    }

    public static void main(String[] args) {
	Int2DArray2 test1 = new Int2DArray2(3, 3);
	test1.insert(5, 0, 1);
	System.out.println("expecting false: " + test1.empty());
	System.out.println("expecting true: " + test1.subArrayEmpty(2));
	System.out.println("expecting 3: " + test1.width());
	System.out.println("expecting 3: " + test1.height());
	test1.wipe();
	System.out.println("expecting true: " + test1.empty());
	Random gen = new Random();
	for (int i = 0; i < test1.width(); i++)
	    for (int j = 0; j < test1.height(); j++)
		test1.insert(gen.nextInt(20), i, j);
	System.out.println("random number at position (1, 2): " + test1.get(1, 2));
    }
}
