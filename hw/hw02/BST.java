/** Special thanks to Prof.Yuzhen Ye, CSCI, Indiana University Bloomington, for the original source code. */
/** Binary Search Tree with insert and removal functions */
 
public class BST<Key extends Comparable<?super Key>, E> {
    private BinNode<Key, E> root;
    private int totalNode;
    private BinNode<Key, E> parent; //for removing nodes
    private BinNode<Key, E> curr;   //works with find()
    private String enumStr;         //for enumeration
    public BST() {
        root = curr = parent = null;
        totalNode = 0;
    }
    public BinNode<Key, E> find(Key k) {
        if(root == null) return null;
        else {
            return find(root, k);
        }
    }
    public BinNode<Key, E> find(BinNode<Key, E> entry, Key k) {
        if(entry == null) return null;
        parent = curr;
        curr = entry;
        if(entry.getKey() == k) {
            return curr;
        }
        else {
            if(entry.isLeaf()) return null;
            else if(((Key) k).compareTo((Key) entry.getKey()) >= 0) {
                return find(entry.getRight(), k);
            }
            else {
                return find(entry.getLeft(), k);
            }
        }
    }

    public void insert(Key k, E v) {
        BinNode<Key, E> node = new BinNode <Key, E>(k, v);
        insert(node);
        //insert(root, node);
    }

    public void insert(BinNode<Key, E> node) {
        find(node.getKey());
        if(curr == null) {
            root = node;
        }
        else if(((Key) node.getKey()).compareTo((Key) curr.getKey()) >= 0) {
            if(curr.getRight() != null) node.setRight(curr.getRight());
            curr.setRight(node);
        }
        else {
            if(curr.getLeft() != null) node.setLeft(curr.getLeft());
            curr.setLeft(node);
        }
        totalNode ++;
	node.setSize(0);
	if (!node.equals(root))
	    changeSizes(parent(node), true);
    }

    public BinNode<Key, E> remove(Key k) {
        if(root != null) return remove(root, k);
        else return null;
    }
    private BinNode<Key, E> remove(BinNode<Key, E> entry, Key k) {
        changeSizes(parent(entry), false);
	BinNode<Key, E> tmp = find(entry, k);
        BinNode<Key, E> parentTmp = parent;
        System.out.println("find node for the key to be removed: " + tmp.getKey());
        if(tmp != null) { //find the node with the same key
            if(totalNode == 1) root = null;
            else if(tmp.isLeaf()) { 
                System.out.println("parent: " + parent.getKey());
                if((parentTmp.getLeft() != null) && (parentTmp.getLeft().getKey() == k)) parentTmp.removeLeft();
                else parentTmp.setRight(null);
            }
            else if(tmp.getLeft() == null) { //to-be-removed node only has right child
                if(parentTmp.getLeft().getKey() == k) parentTmp.setLeft(tmp.getRight());
                else parentTmp.setRight(tmp.getRight());
            }
            else if(tmp.getRight() == null) { //to-be-removed node only has left child
                if(parentTmp.getLeft().getKey() == k) parentTmp.setLeft(tmp.getLeft());
                else parentTmp.setRight(tmp.getLeft());
            }
            else {
                //replace the to-be-deleted-node with the node with minimum key in the right subtree
                //the alternative is to replace it with the node with maximum key in the left subtree
                BinNode<Key, E> minGreaterThan = getMin(tmp.getRight());
                //replace to-be-deleted node with minGreaterThan
                tmp.setKey(minGreaterThan.getKey()); 
                tmp.setValue(minGreaterThan.getValue());
                //reset the old parent->minGreaterThan link
                if(parent.getLeft().getKey() == minGreaterThan.getKey())
                    //minGreatThan cannot have left child
                    parent.setLeft(minGreaterThan.getRight()); 
                else
                    parent.setRight(minGreaterThan.getRight());
            }
            totalNode --;
        }
        return tmp;
    }
    public BinNode<Key, E> getMax(BinNode<Key, E> entry) {
        /** only need to check right child */
        if(entry.getRight() == null) return entry;
        else {
            parent = entry;
            return getMax(entry.getRight());
        }
    }
    public BinNode<Key, E> getMin(BinNode<Key, E> entry) {
        /** only need to check left child */
        if(entry.getLeft() == null) return entry;
        else {
            parent = entry;
            return getMin(entry.getLeft());
        }
    }
    public void preorder() {
        enumStr = "";
        if(root != null) preorder(root);
        System.out.println("Preorder enumeration: " + enumStr);
    }
    private void preorder(BinNode<Key, E> node) { 
        if(node != null) System.out.println("root " + node.getKey());
        if(node.getLeft() != null) System.out.println("   left " + node.getLeft().getKey());
        if(node.getRight() != null) System.out.println("   right " + node.getRight().getKey());
         
        if(node != null) enumStr += node.getKey() + " ";
        if(node.getLeft() != null) preorder(node.getLeft());
        if(node.getRight() != null) preorder(node.getRight());
    }
    public void inorder() {
        enumStr = "";
        if(root != null) inorder(root);
        System.out.println("Inorder enumeration: " + enumStr);
    }
    private void inorder(BinNode<Key, E> node) {
        if(node.getLeft() != null) inorder(node.getLeft());
        if(node != null) {
            enumStr += node.getKey() + " ";
        }
        if(node.getRight() != null) inorder(node.getRight());
    }
    public void postorder() {
        enumStr = "";
        if(root != null) postorder(root);
        System.out.println("Postorder enumeration: " + enumStr);
    }
    private void postorder(BinNode<Key, E> node) {
        if(node.getLeft() != null) postorder(node.getLeft());
        if(node.getRight() != null) postorder(node.getRight());
        if(node != null) {
            enumStr += node.getKey() + " ";
        }
    }

    // private boolean isRoot(BinNode<Key, E> node) {
	
    // }

    private BinNode<Key, E> parent(BinNode<Key, E> node) {
	return parentHelper(root, node);
    }

    private BinNode<Key, E> parentHelper(BinNode<Key, E> currRoot, BinNode<Key, E> node) {
	if (currRoot ==  null || node.equals(root)) {
	    return null;
	}

	boolean left = (currRoot.getLeft() != null);
	boolean right = (currRoot.getRight() != null);
	
	if (left && right) {
	    if (currRoot.getLeft().equals(node) || currRoot.getRight().equals(node))
		return currRoot;
	    else if (((Key) currRoot.getKey()).compareTo((Key) node.getKey()) >= 0)
		parentHelper(currRoot.getLeft(), node);
	    else
		parentHelper(currRoot.getRight(), node);
	} else if (left) {
	    if (currRoot.getLeft().equals(node))
		return currRoot;
	    else
		parentHelper(currRoot.getLeft(), node);
	} else if (right) {
	    if (currRoot.getRight().equals(node))
		return currRoot;
	    else
		parentHelper(currRoot.getRight(), node);
	}

	return null;
    }

    private void changeSizes(BinNode<Key, E> node, boolean increment) {
	if (node == null)
	    return;
	if (increment)
	    node.setSize(node.getSize() + 1);
	else
	    node.setSize(node.getSize() - 1);
	if (node == root)
	    return;
	changeSizes(parent(node), increment);
    }

    private boolean balanced(BinNode<Key, E> node) {
	if (node.getSize() == 0)
	    return true;
	
	int leftS = node.getLeft().getSize();
	int rightS = node.getRight().getSize();
	if (leftS < rightS - 1 || rightS < leftS - 1)
	    return false;
	return (balanced(node.getLeft()) && balanced(node.getRight()));
    }

    // main
    public static void main(String[] args) {
	BST tree = new BST();
	BinNode<Integer, Integer> root = new BinNode(new Integer(30), new Integer(1));
	tree.insert(root);
	tree.insert(35, 0);
	tree.insert(34, 1);
	tree.insert(18, 0);
	tree.insert(15, 1);
	tree.insert(20, 0);
	tree.insert(40, 1);

	System.out.println("Checking if the tree is balanced, expecting true:");
	System.out.println(tree.balanced(tree.root));
    }
}

class BinNode<Key, E> {
    private Key key;
    private E value;
    private BinNode left;
    private BinNode right;
    private int size;
    public BinNode(Key k, E e) {
        key = k;
        value = e;
        left = right = null;
    }
    public Key getKey() { return key; }
    public E getValue() { return value; }
    public void setKey(Key k) { key = k; }
    public void setValue(E e) { value = e; }
    public void setLeft(BinNode node) { left = node; }
    public void setRight(BinNode node) { right = node; }
    public void removeLeft() { left = null; }
    public void removeRight() { right = null; }
    public BinNode getLeft() { return left; }
    public BinNode getRight() { return right; }
    public boolean isLeaf() {
        if((left == null) && (right == null)) return true;
        else return false;
    }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
