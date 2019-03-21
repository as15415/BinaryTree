package schultz.gsu.binarytree;

public class BinaryTreeNode<T> {
	
	private T element;
	private BinaryTreeNode<T> parent;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	public BinaryTreeNode(T element) {
		this.element = element;
		parent = null;
		leftChild = null;
		rightChild = null;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public BinaryTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
		this.leftChild.setParent(this);
	}
	
	public void setNullLeftChild() {
		this.leftChild = null;
	}

	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
		this.rightChild.setParent(this);
	}
	
	public void setNullRightChild() {
		this.rightChild = null;
	}
	
	public int numChildren() {
		if(leftChild == null && rightChild == null)
			return 0;
		
		else if(leftChild != null && rightChild != null)
			return 2;
		
		return 1;
	}
	
	public BinaryTreeNode<T> getSibling() {
		if(parent == null)
			return null;
		
		else {
			if(this == parent.getLeftChild())
				return parent.getRightChild();
			else
				return parent.getLeftChild();
		}
	}
	
}
