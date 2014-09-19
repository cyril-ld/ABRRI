/**
 * 
 */
package org.miage.aabri.exceptions;

/**
 * Permet de signaler lorsqu'un noeud est mal positionné dans l'ABRI.
 * 
 * Utile notamment dans les méthodes de validation des ABRI.
 * 
 * @author Cyril
 * 
 */
public class SimpleNodeMalPositionne extends Exception {

	/**
	 * auto-generated
	 */
	private static final long serialVersionUID = -2186033466004153501L;

	public SimpleNodeMalPositionne(String message) {
		super(message);
	}
}
