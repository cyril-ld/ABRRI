/**
 * 
 */
package datastructure;

import interfaces.Node;

/**
 * @author Cyril
 * 
 */
public class SimpleNode extends Node {

	private int valeur;

	public SimpleNode(int valeur) {
		this.valeur = valeur;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
