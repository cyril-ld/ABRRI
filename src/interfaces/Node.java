/**
 * 
 */
package interfaces;

/**
 * @author Cyril
 * 
 */
public abstract class Node {

	private Node leftSon;

	private Node rightSon;

	private Node father;

	/**
	 * @return the leftSon
	 */
	public Node getLeftSon() {
		return leftSon;
	}

	/**
	 * @param leftSon the leftSon to set
	 */
	public void setLeftSon(Node leftSon) {
		this.leftSon = leftSon;
	}

	/**
	 * @return the rightSon
	 */
	public Node getRightSon() {
		return rightSon;
	}

	/**
	 * @param rightSon the rightSon to set
	 */
	public void setRightSon(Node rightSon) {
		this.rightSon = rightSon;
	}

	/**
	 * @return the father
	 */
	public Node getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Node father) {
		this.father = father;
	}

	/**
	 * Permet de savoir si l'arbre est filiforme ou non
	 * 
	 * @param node - Le noeud représentant la racine de l'arbre dans lequel chercher
	 * @return true si filiforme, false sinon
	 */
	public boolean estFiliforme(Node node) {

		boolean ret = true;

		if (node != null) {

			if (node.getRightSon() != null && node.getRightSon() != null) {
				ret = false;
			} else {
				ret = ret && this.estFiliforme(node.getLeftSon());
				ret = ret && this.estFiliforme(node.getRightSon());
			}
		}

		return ret;
	}
}
