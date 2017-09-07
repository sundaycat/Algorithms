public class NodeRBT {

	private NodeRBT left;
	private NodeRBT right;
	private NodeRBT parent;

	private Color color;
	private String value;

	public NodeRBT(String value, NodeRBT left, NodeRBT right, NodeRBT parent,
			Color color) {

		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.color = color;
	}

	public boolean isLeaf(NodeRBT nil) {

		return (value == nil.getValue() && parent == nil.getParent()
				&& left == nil.getLeft() && right == nil.getRight()) ? true : false;

	}

	public boolean isLess(NodeRBT rbn) {

		String key = rbn.getValue();
		return (value.compareTo(key) < 0) ? true : false;
	}

	public NodeRBT getLeft() {
		return left;
	}

	public void setLeft(NodeRBT left) {
		this.left = left;
	}

	public NodeRBT getRight() {
		return right;
	}

	public void setRight(NodeRBT right) {
		this.right = right;
	}

	public NodeRBT getParent() {
		return parent;
	}

	public void setParent(NodeRBT parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
