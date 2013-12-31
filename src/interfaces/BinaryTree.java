/**
 * 
 */
package interfaces;

import datastructure.TypeABR;

/**
 * 
 * Arbre binaire généraliste. L'arbre possède une racine et un type (Cf. énumération TypeABR)
 * 
 * @author Cyril
 * 
 */
public abstract class BinaryTree {

	/**
	 * Noeud racine de l'arbre
	 */
	private Node rootNode;

	/**
	 * Type de l'AB
	 */
	private TypeABR type;

	/**
	 * @return the rootNode
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * @return the type
	 */
	public TypeABR getType() {
		return type;
	}
}
