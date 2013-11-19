/**
 * 
 */
package datastructure;

/**
 * @author Cyril
 * 
 */
public class TreeNode extends Node {

	/**
	 * Valeur minimale de l'intervalle couvert par l'arbre
	 */
	private int min;

	/**
	 * Valeur maximale de l'intervalle couvert par l'arbre
	 */
	private int max;

	/**
	 * Arbre binaire inversé stocké par le noeud courant
	 */
	private BinaryTree binaryTree;

	/**
	 * Constructeur d'un noeud d'arbre binaire d'arbres binaires inversés.
	 * 
	 * @param min - la valeur minimale de l'intervalle couvert
	 * @param max - la valeur maximale de l'intervalle couvert
	 * @param arbre - l'arbre stocké par le noeud
	 */
	public TreeNode(int min, int max, BinaryTree arbre) {
		this.binaryTree = arbre;
		this.min = min;
		this.max = max;
	}

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
