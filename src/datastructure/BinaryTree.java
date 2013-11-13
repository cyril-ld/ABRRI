/**
 * 
 */
package datastructure;

/**
 * Classe représentant un arbre binaire de recherche. L'arbre représenté ici est un arbre binaire de recherche inversé.
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
	 * Constructeur par défaut
	 */
	public BinaryTree() {

	}

	public BinaryTree(Node newRoot) {
		this.root = newRoot;
	}

	/**
	 * Noeud de l'arbre binaire, dans l'idée le noeud doit contenir une valeur, un fils droit et/ou un fils gauche.
	 * 
	 * @author Cyril
	 * 
	 */
	private class Node {

		/**
		 * Valeur contenue dans le noeud, servant à le positionner dans l'arbre
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
		 * Père
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
		 *            , noeud père
		 */
		public Node(int valeur, Node lSon, Node rSon, Node father) {
			this.valeur = valeur;
			this.lSon = lSon;
			this.rSon = rSon;
			this.father = father;
		}
	}
}
