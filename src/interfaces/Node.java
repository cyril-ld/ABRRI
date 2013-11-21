/**
 * 
 */
package interfaces;

/**
 * @author Cyril
 * 
 */
public abstract class Node {

	private Node filsGauche;

	private Node filsDroit;

	private Node pere;

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

	/**
	 * @return the pere
	 */
	public Node getPere() {
		return pere;
	}

	/**
	 * @param pere the pere to set
	 */
	public void setPere(Node pere) {
		this.pere = pere;
	}
}
