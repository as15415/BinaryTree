package schultz.gsu.binarytree;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayBinaryTree<T> {
	
	private ArrayList<T> data;
	private BinaryTreeNode<T> root;
	
	public ArrayBinaryTree() {
		data = new ArrayList<T>();
		
		root = null;
	}
	
	public ArrayBinaryTree(T[] data) {
		this.data = new ArrayList<T>(Arrays.asList(data));
		root = null;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public int size(int nodeRank) {
		if(data.get(nodeRank) == null)
			return 0;
		
		return 1 + size(getLeftChildRank(nodeRank)) + size(getRightChildRank(nodeRank));
	}
	
	public boolean isEmpty() {
		return size(0) == 0;
	}

	public int height(int nodeRank) {
		// added in case either left or right child is null, avoiding NullPointerException
		// not needed if tree is a proper binary tree
		if(data.get(nodeRank) == null)
			return 0;
				
		if(this.isExternal(nodeRank))
			return 0;
				
		return 1 + Math.max(height(getLeftChildRank(nodeRank)), height(getRightChildRank(nodeRank)));
	}
	
	public boolean isInternal(int nodeRank) {
		int leftChildRank = getLeftChildRank(nodeRank);
		int rightChildRank = getRightChildRank(nodeRank);
		
		return leftChildRank != -1 || rightChildRank != -1;
	}
	
	public boolean isExternal(int nodeRank) {
		return !isInternal(nodeRank);
	}
	
	public boolean isRoot(BinaryTreeNode<T> node) {
		return node.getParent() == null;
	}
	
	public void preOrder(int nodeRank) {
		int leftChildRank = getLeftChildRank(nodeRank);
		int rightChildRank = getRightChildRank(nodeRank);
		
		System.out.println(data.get(nodeRank));
		
		if(leftChildRank != -1)
			inOrder(leftChildRank);
		
		if(rightChildRank != -1)
			inOrder(rightChildRank);
	}
	
	public void postOrder(int nodeRank) {
		int leftChildRank = getLeftChildRank(nodeRank);
		int rightChildRank = getRightChildRank(nodeRank);
		
		if(leftChildRank != -1)
			inOrder(leftChildRank);
		
		if(rightChildRank != -1)
			inOrder(rightChildRank);
		
		System.out.println(data.get(nodeRank));
	}
	
	public void inOrder(int nodeRank) {
		int leftChildRank = getLeftChildRank(nodeRank);
		int rightChildRank = getRightChildRank(nodeRank);
		
		if(leftChildRank != -1)
			inOrder(leftChildRank);
		
		System.out.println(data.get(nodeRank));
		
		if(rightChildRank != -1)
			inOrder(rightChildRank);
	}
	
	public int getRank(BinaryTreeNode<T> node) {
		if(isRoot(node))
			return 0;
		
		if(node.getParent().getLeftChild().equals(node))
			return 2 * getRank(node.getParent()) + 1;
		
		if(node.getParent().getRightChild().equals(node))
			return 2 * getRank(node.getParent()) + 2;
		
		return -1;
	}
	
	public void storeInOrder(BinaryTreeNode<T> node) {
		int rank = getRank(node);
		
		if(node.getLeftChild() != null)
			storeInOrder(node.getLeftChild());
		
		data.add(rank, node.getElement());
		
		if(node.getRightChild() != null)
			storeInOrder(node.getRightChild());
	}
	
	public int getParentRank(int nodeRank) {
		int result = (nodeRank - 1) / 2;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public int getLeftChildRank(int nodeRank) {
		int result = 2 * nodeRank + 1;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public int getRightChildRank(int nodeRank) {
		int result = 2 * nodeRank + 2;
		
		if(result > data.size() - 1 || result < 0 || data.get(result) == null)
			return -1;
		
		return result;
	}
	
	public void printExpression(int nodeRank) {
		int leftChildRank = getLeftChildRank(nodeRank);
		int rightChildRank = getRightChildRank(nodeRank);
		
		if(leftChildRank != -1) {
			System.out.print("(");
			printExpression(leftChildRank);
		}
		
		System.out.print(data.get(nodeRank));
		
		if(rightChildRank != -1) {
			printExpression(rightChildRank);
			System.out.print(")");
		}
	}
	
	public String toString() {
		String result = "";
		
		for(int i = 0; i < data.size(); i++)
			result += (i + " ");
		
		result += "\n";
		
		for(int i = 0; i < data.size(); i++)
			result += (data.get(i) + " ");
		
		return result;
	}
	
}
