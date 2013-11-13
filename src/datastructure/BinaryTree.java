/**
 * 
 */
package datastructure;

/**
 * Classe repr�sentant un arbre binaire de recherche. L'arbre repr�sent� ici est un arbre d'arbres binaires de recherche.
 * 
 * @author Cyril
 * 
 */
public class BinaryTree {

	/**
	 * Noeud racine de l'arbre
	 */
	private Node root;

	/**
	 * Constructeur par d�faut
	 */
	public BinaryTree() {

	}

	public BinaryTree(Node newRoot) {
		this.root = newRoot;
	}

	/**
	 * Noeud de l'arbre binaire, dans l'id�e le noeud doit contenir une valeur, un fils droit et/ou un fils gauche.
	 * 
	 * @author Cyril
	 * 
	 */
	private class Node {

		/**
		 * Valeur contenue dans le noeud, servant � le positionner dans l'arbre
		 */
		private int valeur;

		/**
		 * Fils gauche
		 */
		private Node lSon;

		/**
		 * Fils droit
		 */
		private Node rSon;

		/**
		 * P�re
		 */
		private Node father;

		/**
		 * Constructeur
		 * 
		 * @param valeur
		 *            , valeur contenue dans le noeud
		 * @param lSon
		 *            , fils droit
		 * @param rSon
		 *            , fils gauche
		 * @param father
		 *            , noeud p�re
		 */
		public Node(int valeur, Node lSon, Node rSon, Node father) {
			this.valeur = valeur;
			this.lSon = lSon;
			this.rSon = rSon;
			this.father = father;
		}
	}
}
