/**
 * 
 */
package org.miage.aabri.exceptions;

/**
 * Exception levée lorsqu'on essaie de trouver un arbre binaire caractérisé par un intervalle qui n'existe pas.
 * 
 * @author Cyril
 * 
 */
public class IntervalleInexistantException extends Exception {

	/**
	 * auto généré
	 */
	private static final long serialVersionUID = -5359373138818590743L;

	public IntervalleInexistantException(String message) {
		super(message);
	}

}
