// H343 / Spring 2017 Lab 05 Andrew Mathies awmathie

public class BinNodeJr <E extends Comparable<?super E>>{
    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;
    public BinNodeJr(E e) {
        value = e;
        left = right = null;
    }
    public void setLeft(BinNodeJr<E> node) {
        left = node;
    }
    public void setRight(BinNodeJr<E> node) {
        right = node;
    }
    public boolean find(E searchValue) {
	if (this == null)
	    return false;
	else if (this.value.equals(searchValue))
	    return true;
	else if (this.left != null && this.right != null)
	    return (this.left.find(searchValue) || this.right.find(searchValue));
	else if (this.left != null)
	    return this.left.find(searchValue);
	else if (this.right != null)
	    return this.right.find(searchValue);
	else return false;
    }    


    public static void main(String[] args) {
	System.out.println("BinNodeJr test running...");
        // building a not so simple tree...
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(100);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(120);
        BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(35);
	BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(70);
	BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(50);
	BinNodeJr<Integer> node5 = new BinNodeJr<Integer>(5);
	BinNodeJr<Integer> node6 = new BinNodeJr<Integer>(80);
	BinNodeJr<Integer> node7 = new BinNodeJr<Integer>(60);
	BinNodeJr<Integer> node8 = new BinNodeJr<Integer>(75);
	BinNodeJr<Integer> node9 = new BinNodeJr<Integer>(45);
	BinNodeJr<Integer> node10 = new BinNodeJr<Integer>(3);
	BinNodeJr<Integer> node11 = new BinNodeJr<Integer>(18);
	BinNodeJr<Integer> node12 = new BinNodeJr<Integer>(58);
	BinNodeJr<Integer> node13 = new BinNodeJr<Integer>(24);

	// setting elements of tree in place
        root.setLeft(node1);
        root.setRight(node2);
	node1.setLeft(node3);
	node1.setRight(node4);
	node2.setLeft(node10);
	node4.setLeft(node5);
	node4.setRight(node6);
	node5.setLeft(node7);
	node5.setRight(node8);
	node8.setLeft(node9);
	node10.setRight(node11);
	node11.setLeft(node12);
	node11.setRight(node13);

	// checking that find works
	System.out.println("Testing for 120, expecting true:");
	System.out.println(root.find(120));
	System.out.println("Testing for 18, expecting true:");
	System.out.println(root.find(18));
	System.out.println("Testing left subtree for 45, expecting true:");
	System.out.println(node1.find(45));
	System.out.println("Testing bottom of left subtree for 60, expecting true:");
	System.out.println(node5.find(60));
	System.out.println("Testing right subtree for 24, expecting true:");
	System.out.println(node2.find(24));
	System.out.println("Testing right subtree for 45, expecting false:");
	System.out.println(node2.find(45));
	System.out.println("Testing for 81, expecting false:");
	System.out.println(root.find(81));
	System.out.println("Testing for 3000, expecting false:");
	System.out.println(root.find(3000));

	// building a second tree with strings
	BinNodeJr<String> root2 = new BinNodeJr<String>("hello");
	BinNodeJr<String> t2n1 = new BinNodeJr<String>("music");
	BinNodeJr<String> t2n2 = new BinNodeJr<String>("movies");
	BinNodeJr<String> t2n3 = new BinNodeJr<String>("video games");
	BinNodeJr<String> t2n4 = new BinNodeJr<String>("dnd");
	
	// putting all of the elements in place
	root2.setLeft(t2n1);
	root2.setRight(t2n2);
	t2n1.setRight(t2n3);
	t2n3.setLeft(t2n4);

	// checking that find works for data types other than Integer
	System.out.println("Testing second tree for video games, expecting true:");
	System.out.println(root2.find("video games"));
	System.out.println("Testing second tree for dancing, expecting false:");
	System.out.println(root2.find("dancing"));
    }
}
