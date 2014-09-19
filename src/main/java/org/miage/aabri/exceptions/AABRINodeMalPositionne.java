/**
 * 
 */
package org.miage.aabri.exceptions;

/**
 * Exception permettant de marquer un noeud de l'AABRI mal positonné.
 * 
 * @author Cyril
 * 
 */
public class AABRINodeMalPositionne extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7982444918744656691L;

	public AABRINodeMalPositionne(String message) {
		super(message);
	}
}
