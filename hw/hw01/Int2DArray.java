// define an ADT for 2D int array, specifying operations
/*
1.
A two-dimensional array of integers is a collection of arrays of integers.
Each array inside the 2D array has the same number of elements. Operations you
can perform on 2D arrays include inserting an integer in a specific location
in one of the specified arrays inside the 2D array, resetting the 2D array by
wiping all data in all of the arrays, deleting or retrieving whatever integer
is at a specified loation in a specified array, checking whether the entire
2D array is empty, checking whether one of the arrays inside the 2D array is
empty, returning how manty arrays are inside the 2D array, and returning the
length of the arrays inside the 2D array.
*/

// 2.
public interface Int2DArray {    
    public void insert(int value, int row, int col);

    public void wipe();

    public void remove(int row, int col);

    public int get(int row, int col);

    public boolean empty();

    public boolean subArrayEmpty(int row);

    public int width();

    public int height();
}
