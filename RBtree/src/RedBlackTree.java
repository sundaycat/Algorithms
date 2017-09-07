/*
 * @CS 146 PROJECT 2 
 * 
 * @HONGZHE LIU 010030891
 */
public class RedBlackTree {

	private NodeRBT root;
	private long size;

	// sentinel node, use to denote the end of a tree path
	static final NodeRBT NIL = new NodeRBT(null, null, null, null, Color.BLACK);

	public RedBlackTree() {
		this.root = NIL;
		size = 0;
	}

	public RedBlackTree(NodeRBT root, int size) {

		this.root = root;
		this.size = size;
	}

	// if the node do not exist, return NIL, otherwise return the corresponding
	// node
	public NodeRBT search(NodeRBT current, String key) {

		String curKey = current.getValue();
		while (!current.isLeaf(NIL) && !curKey.equals(key)) {

			// if the current key is ahead of the searching key, keeping
			// searching the right subtree of current node, otherwise go to the
			// left subtree.
			if (curKey.compareTo(key) < 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
			curKey = current.getValue();
		}

		return current.isLeaf(NIL) ? NIL : current;
	}

	public NodeRBT findMinimum(NodeRBT current) {

		NodeRBT minNode = null;
		while (!current.isLeaf(NIL)) {

			minNode = current;
			current = current.getLeft();
		}

		return minNode;
	}

	public NodeRBT findMaximum(NodeRBT current) {

		NodeRBT maxNode = null;
		while (!current.isLeaf(NIL)) {

			maxNode = current;
			current = current.getRight();
		}
		return maxNode;
	}

	// Pre-order
	public String preorderTreeWalk(NodeRBT current) {

		StringBuilder keyList = new StringBuilder();
		preorder(current, keyList);
		return keyList.toString();
	}

	private void preorder(NodeRBT current, StringBuilder keyList) {
		// Color: BLACK, Key:D Parent: \n"
		if (!current.isLeaf(NIL)) {

			NodeRBT p = current.getParent();
			String parVal = p.getValue() == null ? "" : p.getValue();
			keyList.append("Color: " + current.getColor() + ", Key:"
					+ current.getValue() + " Parent: " + parVal + "\n");
			preorder(current.getLeft(), keyList);
			preorder(current.getRight(), keyList);
		}
	}

	// In-order
	public String inorderTreeWalk(NodeRBT current) {

		StringBuilder keyList = new StringBuilder();
		inorder(current, keyList);
		return keyList.toString();
	}

	private void inorder(NodeRBT current, StringBuilder keyList) {

		if (!current.isLeaf(NIL)) {
			inorder(current.getLeft(), keyList);
			NodeRBT p = current.getParent();
			String parVal = p.getValue() == null ? "" : p.getValue();
			keyList.append("Color: " + current.getColor() + ", Key:"
					+ current.getValue() + " Parent: " + parVal + "\n");
			inorder(current.getRight(), keyList);
		}
	}

	// Post-order
	public String postorderTreeWalk(NodeRBT current) {

		StringBuilder keyList = new StringBuilder();
		postorder(current, keyList);
		return keyList.toString();
	}

	private void postorder(NodeRBT current, StringBuilder keyList) {

		if (!current.isLeaf(NIL)) {
			postorder(current.getLeft(), keyList);
			postorder(current.getRight(), keyList);
			NodeRBT p = current.getParent();
			String parVal = p.getValue() == null ? "" : p.getValue();
			keyList.append("Color: " + current.getColor() + ", Key:"
					+ current.getValue() + " Parent: " + parVal + "\n");
		}
	}

	// left rotation
	public void leftRotation(NodeRBT x) {

		// set y
		NodeRBT y = x.getRight();

		// turn y's left subtree into x's right subtree
		x.setRight(y.getLeft());
		if (!y.getLeft().isLeaf(NIL)) {
			// Set x to be �'s parent
			y.getLeft().setParent(x);
		}

		// link x's parent to y
		y.setParent(x.getParent());
		if (x.getParent().isLeaf(NIL)) {
			root = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		// put x on y's left
		y.setLeft(x);
		x.setParent(y);
	}

	// right rotation
	public void rightRotation(NodeRBT y) {

		NodeRBT x = y.getLeft();
		y.setLeft(x.getRight());
		//
		if (!x.getRight().isLeaf(NIL)) {
			x.getRight().setParent(y);
		}

		// link y's parent to x
		x.setParent(y.getParent());
		if (y.getParent().isLeaf(NIL)) {
			root = x;
		} else if (y == y.getParent().getLeft()) {
			y.getParent().setLeft(x);
		} else {
			y.getParent().setRight(x);
		}

		// put y on x's right
		x.setRight(y);
		y.setParent(x);

	}

	// fix up the red black tree after inserting a new node
	public void fixupRBT(NodeRBT x) {

		x.setColor(Color.RED);
		
		// Keep iterating until the color of x's parent is BLACK
		while (x != root && x.getParent().getColor() == Color.RED) {

			NodeRBT grandpa = x.getParent().getParent();
			NodeRBT parent = x.getParent();
			if (parent == grandpa.getLeft()) {

				// If x's parent is a left child of its grand parent
				NodeRBT uncle = grandpa.getRight();
				if (parent.getColor() == Color.RED
						&& uncle.getColor() == Color.RED) { // case 1

					grandpa.setColor(Color.RED);
					parent.setColor(Color.BLACK);
					uncle.setColor(Color.BLACK);
					x = grandpa;

				} else if (parent.getColor() == Color.RED
						&& uncle.getColor() == Color.BLACK
						&& x == parent.getRight()) { // case 2

					x = parent;
					this.leftRotation(x);

					// case 3, follow case 2
					x.getParent().setColor(Color.BLACK);
					x.getParent().getParent().setColor(Color.RED);
					x = x.getParent().getParent();
					this.rightRotation(x);

				} else {

					// case 3, show up without case 2
					x.getParent().setColor(Color.BLACK);
					x.getParent().getParent().setColor(Color.RED);
					x = x.getParent().getParent();
					this.rightRotation(x);
				}

			} else {

				// If x's parent is a right child of its grand parent
				NodeRBT uncle = grandpa.getLeft();
				if (parent.getColor() == Color.RED
						&& uncle.getColor() == Color.RED) { // case 1

					grandpa.setColor(Color.RED);
					parent.setColor(Color.BLACK);
					uncle.setColor(Color.BLACK);
					x = grandpa;

				} else if (parent.getColor() == Color.RED
						&& uncle.getColor() == Color.BLACK
						&& x == parent.getLeft()) { // case 2

					x = parent;
					this.rightRotation(x);

					// case 3, follow case 2
					x.getParent().setColor(Color.BLACK);
					x.getParent().getParent().setColor(Color.RED);
					x = x.getParent().getParent();
					this.leftRotation(x);

				} else {

					// case 3, show up without case 2
					x.getParent().setColor(Color.BLACK);
					x.getParent().getParent().setColor(Color.RED);
					x = x.getParent().getParent();
					this.leftRotation(x);

				}
			}
		}

		this.root.setColor(Color.BLACK);
	}

	// Inserting a new node to a binary search tree
	// rbt: the root of red black tree
	// rbn: inserting red black node
	private void insertBST(NodeRBT rbn) {

		NodeRBT parOfCur = null;
		NodeRBT current = this.root;

		if (current.isLeaf(NIL)) {
			this.root = rbn;
			this.root.setParent(NIL);
			return;
		} else {
			while (!current.isLeaf(NIL)) {
				parOfCur = current;
				if (current.isLess(rbn)) {
					current = current.getRight();
				} else {
					current = current.getLeft();
				}
			}
		}

		if (parOfCur.isLess(rbn)) {
			parOfCur.setRight(rbn);
			rbn.setParent(parOfCur);
		} else {
			parOfCur.setLeft(rbn);
			rbn.setParent(parOfCur);
		}

	}

	// Insert a node to a red black tree
	public void insertRBT(NodeRBT rbn) {

		insertBST(rbn);
		fixupRBT(rbn);
		size++;
	}

	public NodeRBT getRoot() {
		return root;
	}

	public void setRoot(NodeRBT root) {
		this.root = root;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public NodeRBT getSibling(NodeRBT n) {

		NodeRBT parentOfN = n.getParent();

		// if n is the root of tree, return sentinel node NIL
		if (!parentOfN.isLeaf(NIL)) {

			// if n is the left child of its parent, return its parent's right
			// child, vice verse. if the sibling doesn't exist, return NIL
			return (parentOfN.getLeft() == n) ? parentOfN.getRight()
					: parentOfN.getLeft();
		} else {
			return NIL;
		}
	}

	public NodeRBT getAunt(NodeRBT n) {

		NodeRBT aunt = null;
		NodeRBT parent = n.getParent();
		NodeRBT grandpa = n.getParent().getParent();

		// if the aunt node don't exist, return NIL node
		if (parent.isLeaf(NIL) || grandpa.isLeaf(NIL)) {
			aunt = NIL;
		} else if (parent == grandpa.getLeft()) {
			aunt = grandpa.getRight();
		} else {
			aunt = grandpa.getLeft();
		}

		return aunt;
	}

	public NodeRBT getGrandparent(NodeRBT n) {

		NodeRBT parent = n.getParent();
		NodeRBT grandpa = null;
		// if the grandpa don't exist, return NIL node
		if (parent.isLeaf(NIL) || parent.getParent().isLeaf(NIL)) {
			grandpa = NIL;
		} else {
			grandpa = parent.getParent();
		}

		return grandpa;
	}

	// professor's test code
	public static interface Visitor {
		/**
		 * This method is called at each node.
		 * 
		 * @param n
		 *            the visited node
		 */
		void visit(NodeRBT n);
	}

	public void preOrderVisit(Visitor v) {
		preOrderVisit(root, v);
	}

	private static void preOrderVisit(NodeRBT n, Visitor v) {
		if (n.isLeaf(NIL))
			return;

		v.visit(n);
		preOrderVisit(n.getLeft(), v);
		preOrderVisit(n.getRight(), v);
	}

}
