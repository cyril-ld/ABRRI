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
}
