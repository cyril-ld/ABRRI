/**
 * 
 */
package datastructure;

import interfaces.Node;

/**
 * Implémentation d'un noeud simple. Ce noeud a vocation a être utilisé dans les ABRI. Il contient donc un fils gauche, un fils droit et une valeur.
 * 
 * @author Cyril
 * 
 */
public class SimpleNode extends Node {

	/**
	 * Valeur stockée par le noeud
	 */
	private int value;

	/**
	 * Constructeur.
	 * 
	 * @param value
	 */
	public SimpleNode(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return this.value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set this.value to value
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
