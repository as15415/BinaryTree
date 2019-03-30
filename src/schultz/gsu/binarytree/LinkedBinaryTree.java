package schultz.gsu.binarytree;

public class LinkedBinaryTree<T> {
	
	private BinaryTreeNode<T> root;
	
	public LinkedBinaryTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		root.setParent(null);
		this.root = root;
	}

	public int size(BinaryTreeNode<T> node) {
		if(node == null)
			return 0;
		
		return 1 + size(node.getLeftChild()) + size(node.getRightChild());
	}
	
	public boolean isEmpty() {
		return size(root) == 0;
		// or: return root == null;
	}
	
	public boolean isInternal(BinaryTreeNode<T> node) {
		return node.getLeftChild() != null || node.getRightChild() != null;
	}
	
	public boolean isExternal(BinaryTreeNode<T> node) {
		return !isInternal(node);
	}
	
	public boolean isRoot(BinaryTreeNode<T> node) {
		return node.getParent() == null;
	}
	
	public int edgeHeight(BinaryTreeNode<T> node) {
		// added in case either left or right child is null, avoiding NullPointerException
		if(node == null)
			return 0;
		
		if(this.isExternal(node))
			return 0;
		
		return 1 + Math.max(edgeHeight(node.getLeftChild()), edgeHeight(node.getRightChild()));
	}
	
	public int nodeHeight(BinaryTreeNode<T> node) {
		if(node == null)
			return 0;
		
		return 1 + Math.max(nodeHeight(node.getLeftChild()), nodeHeight(node.getRightChild()));
	}
	
	public void preOrder(BinaryTreeNode<T> node) {
		System.out.println(node.getElement());
		
		if(node.getLeftChild() != null)
			preOrder(node.getLeftChild());
		
		if(node.getRightChild() != null)
			preOrder(node.getRightChild());
	}
	
	public void postOrder(BinaryTreeNode<T> node) {
		if(node.getLeftChild() != null)
			postOrder(node.getLeftChild());
		
		if(node.getRightChild() != null)
			postOrder(node.getRightChild());
		
		System.out.println(node.getElement());
	}
	
	public void inOrder(BinaryTreeNode<T> node) {
		if(node.getLeftChild() != null)
			inOrder(node.getLeftChild());
		
		System.out.println(node.getElement());
		
		if(node.getRightChild() != null)
			inOrder(node.getRightChild());
	}
	
	public void printExpression(BinaryTreeNode<T> node) {
		if(node.getLeftChild() != null) {
			System.out.print("(");
			printExpression(node.getLeftChild());
		}
		
		System.out.print(node.getElement());
		
		if(node.getRightChild() != null) {
			printExpression(node.getRightChild());
			System.out.print(")");
		}
	}
	
}
