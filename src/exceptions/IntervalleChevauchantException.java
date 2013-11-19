/**
 * 
 */
package exceptions;

/**
 * Exception levée lors de l'ajout d'un TreeNode qui possède le même intervalle qu'un TreeNode existant.
 * 
 * @author Cyril
 * 
 */
public class IntervalleChevauchantException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7923560957922130303L;

	public IntervalleChevauchantException(String message) {
		super(message);
	}
}
