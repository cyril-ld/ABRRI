/**
 * 
 */
package datastructure;

/**
 * @author Cyril
 * 
 */
public class TreeNode extends Node {

	private int min;

	private int max;

	private BinaryTree binaryTree;

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the binaryTree
	 */
	public BinaryTree getBinaryTree() {
		return binaryTree;
	}

	/**
	 * @param binaryTree the binaryTree to set
	 */
	public void setBinaryTree(BinaryTree binaryTree) {
		this.binaryTree = binaryTree;
	}
}
