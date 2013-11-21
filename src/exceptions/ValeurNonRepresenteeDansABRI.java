/**
 * 
 */
package exceptions;

/**
 * Exception lancée dans le cas où une recherche est lancée sur un nombre compris dans un intervalle, mais qui n'est pas représenté dans un ABRI.
 * 
 * @author Cyril
 * 
 */
public class ValeurNonRepresenteeDansABRI extends Exception {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -961175343244591596L;

	public ValeurNonRepresenteeDansABRI(String message) {
		super(message);
	}
}
