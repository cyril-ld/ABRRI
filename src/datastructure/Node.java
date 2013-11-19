/**
 * 
 */
package datastructure;

/**
 * @author Cyril
 * 
 */
public abstract class Node {

	private Node filsGauche;

	/**
	 * @return the filsGauche
	 */
	public Node getFilsGauche() {
		return filsGauche;
	}

	/**
	 * @param filsGauche the filsGauche to set
	 */
	public void setFilsGauche(Node filsGauche) {
		this.filsGauche = filsGauche;
	}

	/**
	 * @return the filsDroit
	 */
	public Node getFilsDroit() {
		return filsDroit;
	}

	/**
	 * @param filsDroit the filsDroit to set
	 */
	public void setFilsDroit(Node filsDroit) {
		this.filsDroit = filsDroit;
	}

	private Node filsDroit;
}
