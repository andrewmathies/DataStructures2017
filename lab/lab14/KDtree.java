// H343 Spring 2017
//
// KD tree with insert and exact match methods
//
// TODO for Lab 14:
//   implement getNeighbors(Key[] key, int r)

import java.util.*;

public class KDtree<Key extends Comparable<?super Key>, E> {
    private BinNode<Key, E> root;
    private int totalNode;
    private BinNode<Key, E> curr;   // works with find()
    private String enumStr;         // for enumeration
    private int dim;   // dimension of the key
    private int level; // which level; important for insertion & search
    private ArrayList<BinNode<Key, E>> neighbors = new ArrayList<BinNode<Key, E>>();
    // k-dimensional tree:
    // at instantiation time,
    // the parameter d specifies how many dimensions are stored in each key
    // (stored in the instance variable dim)
    public KDtree(int d) {
        root = curr = null;
        totalNode = 0;
        dim = d;
        level = 0;
    }

    public BinNode<Key, E> find (Key[] k) {
        if (root == null) 
	    return null;
        else
            return find (root, 0, k);
    }

    public BinNode<Key, E> find (BinNode<Key, E> entry, int thislevel, Key[] k) {
        if (entry == null) 
	    return null;
        curr = entry;
        level = thislevel; // update level
        Key[] thiskey = entry.getKey();
        if (thiskey[level % dim].compareTo(k[level % dim]) == 0) {
            return curr;
        } else {
            if (entry.isLeaf()) return null;
            Key[] key2 = entry.getKey();
             // make sure that the "correct" component within the k-dimensional key is used:
            if (k[level % dim].compareTo(key2[level % dim]) >= 0) {
                return find (entry.getRight(), thislevel + 1, k); // note thislevel + 1
            } else {
                return find (entry.getLeft(), thislevel + 1, k);
            }
        }
    }
    public void insert(Key[] k, E v) {
        BinNode<Key, E> node = new BinNode <Key, E>(k, v);
        insert(node);
        // insert(root, node);
    }
    public void insert(BinNode<Key, E> node) {
        find (node.getKey());
        if (curr == null) {
            root = node;
        } else {
            Key[] key1 = node.getKey();
            Key[] key2 = curr.getKey();
            if (key1[level % dim].compareTo(key2[level % dim]) >= 0) {
                if (curr.getRight() != null) node.setRight(curr.getRight());
                curr.setRight(node);
            } else {
                if (curr.getLeft() != null) node.setLeft(curr.getLeft());
                curr.setLeft(node);
            }
        }
        totalNode ++;
    }
    public void preorder() {
        enumStr = "";
        System.out.println("Total node = " + totalNode);
        if (root != null) preorder(root);
        System.out.println("Preorder enumeration: " + enumStr);
    }
    private void preorder(BinNode<Key, E> node) {    
        if (node != null) System.out.println("root node: " + node.toString());
        if (node.getLeft() != null) System.out.println("   left node: " + node.getLeft().toString());
        if (node.getRight() != null) System.out.println("   right node:" + node.getRight().toString());
        
        if (node != null) {
            enumStr += node.toString();
        }
        if (node.getLeft() != null) preorder(node.getLeft());
        if (node.getRight() != null) preorder(node.getRight());
    }
    
    // ---------------------------------------------------------------------    
    // TODO for lab14 - implement the following method:
    // ---------------------------------------------------------------------
    /*
    public void left(BinNode<Key, E> node) {
	curr = root;
	level = 0;
	while (curr.getLeft().getKey()[level % dim].compareTo(node.getKey()[level % dim]) != 0) {
	    curr = curr.getLeft();
	    level++;
	}
    }
   
    public void right(BinNode<Key, E> node) {
	curr = root;
	level = 0;
	while (curr.getRight().getKey()[level % dim].compareTo(node.getKey()[level % dim]) != 0) {
	    curr = curr.getRight();
	    level++;
	}
    }
    */
    public double distance(BinNode<Key, E> node1, BinNode<Key, E> node2) {
	double out = 0;
	for(int i = 0; i < dim; i++)
	    out += Math.pow(Math.abs(Integer.parseInt(node1.getKey()[i].toString()) - Integer.parseInt(node2.getKey()[i].toString())), 2);
	out = Math.sqrt(out);
	//System.out.println("The distance between " + node1 + " and " + node2 + " is: " + out);
	return out;
    }

    public void getNeighborsHelp(BinNode<Key, E> node, BinNode<Key, E> start, int r) {
	if (node == null)
	    return;
	//System.out.println("One helper iteration.");
	if (distance(start, node) < r) {
	    neighbors.add(node);
	    //System.out.println("One neighbor!");
	}
	
	if (node.getLeft() != null)
	    getNeighborsHelp(node.getLeft(), start, r);
	if (node.getRight() != null)
	    getNeighborsHelp(node.getRight(), start, r);
    }

    public void getNeighbors(Key[] key, int r) {
     	BinNode<Key, E> start = find(key);
	//System.out.println("This is the node we are looking around: " + start);
	getNeighborsHelp(root, start, r);

	neighbors.remove(start);
	for (BinNode<Key, E> x : neighbors)
	    System.out.println(x);
    }
	/*
	while (distance(start, curr) < r) {
	    if (start.getKey()[level % dim].compareTo(curr.getKey()[level & dim]) >= 0) {
		right(curr);
	    } else {
		left(curr);
		left = true;
	    }
	    neighbors.add(curr);
	    temp = curr;
	    while (distance(curr, start) < (double) r) {
		if (left)
		    curr = curr.getRight();
		else
		    curr = curr.getLeft();
		neighbors.add(curr);
	    }
	    curr = temp;
	    left = false;
	}
	
	if (down) {
	    curr = start;
	    while (distance(curr, start) < (double) r) {
		curr = curr.getLeft();
		neighbors.add(curr);
	    }
	    curr = start;
	    while (distance(curr, start) < (double) r) {
		curr = curr.getRight();
		neighbors.add(curr);
	    }
	    }
}
*/
    // ---------------------------------------------------------------------

    public static void main(String[] args) {
        
        KDtree <Integer, String> kdt = new KDtree<Integer, String>(2);
        Integer[] dataA = {40, 45};
        kdt.insert(dataA, "A");
        Integer[] dataB = {15, 70};
        kdt.insert(dataB, "B");
        Integer[] dataC = {70, 10};
        kdt.insert(dataC, "C");
        Integer[] dataD = {69, 50};
        kdt.insert(dataD, "D");
        Integer[] dataE = {66, 85};
        kdt.insert(dataE, "E");
        Integer[] dataF = {85, 95};
        kdt.insert(dataF, "F");
        Integer[] dataG = {85, 93}; // G, close to F
	kdt.insert(dataG, "G");

        // TODO for lab14:
        // write at least 3 additional (different) examples
        //   to test getNeighbors() on identical/close/not close points
        
	KDtree <Integer, String> kdt2 = new KDtree<Integer, String>(2);
        Integer[] A2 = {100, 100};
        kdt2.insert(A2, "A");
        Integer[] B2 = {110, 70};
        kdt2.insert(B2, "B");
        Integer[] C2 = {90, 120};
        kdt2.insert(C2, "C");
        Integer[] D2 = {105, 80};
        kdt2.insert(D2, "D");
        Integer[] E2 = {95, 105};
        kdt2.insert(E2, "E");

	KDtree <Integer, String> kdt3 = new KDtree<Integer, String>(2);
	Integer[] A3 = {5, 7};
	kdt3.insert(A3, "A");
	Integer[] B3 = {5, 10};
	kdt3.insert(B3, "B");
	Integer[] C3 = {7, 7};
	kdt3.insert(C3, "C");
	Integer[] D3 = {50, 11};
	kdt3.insert(D3, "D");

	KDtree <Integer, String> kdt4 = new KDtree<Integer, String>(2);
	Integer[] A4 = {1400, 1250};
	kdt4.insert(A4, "A");
	Integer[] B4 = {1630, 1400};
	kdt4.insert(B4, "B");
	Integer[] C4 = {2000, 1500};
	kdt4.insert(C4, "C");

	//kdt.preorder();
        kdt.getNeighbors(dataG, 3);
	System.out.println();

	//kdt2.preorder();
	System.out.println("Expectation is C, D, and E");
	kdt2.getNeighbors(A2, 30);
	System.out.println();

	//kdt3.preorder();
	System.out.println("Expectation is just A");
	kdt3.getNeighbors(C3, 3);
	System.out.println();

	//kdt4.preorder();
	System.out.println("Expectation is A and B");
	kdt4.getNeighbors(C4, 1000);
	System.out.println();

        // TODO for lab14:
        // instantiate and test a K-D tree with 3 dimensions        
	KDtree <Integer, String> kdt5 = new KDtree<Integer, String>(3);
	Integer[] A5 = {3, 4, 5};
	kdt5.insert(A5, "A");
	Integer[] B5 = {4, 7, 2};
	kdt5.insert(B5, "B");
	Integer[] C5 = {3, 6, 6};
	kdt5.insert(C5, "C");
	Integer[] D5 = {5, 5, 5};
	kdt5.insert(D5, "D");
	
	System.out.println("Expectation is C and D");
	kdt5.getNeighbors(A5, 3);
    }
}
