/**
 * 
 */
package datastructure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import exceptions.IntervalleChevauchantException;
import exceptions.IntervalleInexistantException;

/**
 * @author Cyril
 * 
 */
public class BinaryTree {

	/**
	 * Noeud racine de l'arbre.
	 */
	private Node rootNode;

	/**
	 * Ajoute un noeud type "ABRI" dans l'arbre binaire courant. Construit un TreeNode à partir des valeurs qui sont passées en paramètre et appelle
	 * la méthode insert(TreeNode node).
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @param arbre - arbre binaire stocké dans le noeud
	 * @return - true si le noeud a bien été ajouté, false sinon.
	 * @throws IntervalleChevauchantException lors de l'ajout d'un noeud dont l'intervalle chevauche un intervalle existant
	 * @see insert(TreeNode node)
	 */
	public void insert(int min, int max, BinaryTree arbre) throws IntervalleChevauchantException {
		TreeNode node = new TreeNode(min, max, arbre);
		this.insert(node);
	}

	/**
	 * Ajoute un noeud type "ABRI" dans l'arbre binaire courant
	 * 
	 * @param node - le noeud à ajouter.
	 * @throws IntervalleChevauchantException lors de l'ajout d'un noeud dont l'intervalle chevauche un intervalle existant
	 */
	@SuppressWarnings("cast")
	public void insert(TreeNode node) throws IntervalleChevauchantException {

		TreeNode father = null;

		// Fail fast
		if (node == null)
			throw new RuntimeException("Le noeud reçu est nul !");
		else if (node.getMax() <= 0 || node.getMin() < 0 || node.getMin() >= node.getMax())
			throw new RuntimeException("Une ou plusieurs borne(s) de l'intervalle n'est (ne sont) pas correcte(s) !");

		if (this.rootNode == null) {
			this.rootNode = node;
			return;
		}
		// Recherche du noeud père
		father = (TreeNode) this.findNode(this.rootNode, node.getMin(), node.getMax());

		if (father == null)
			throw new RuntimeException("Impossible de retrouver le père du noeud à ajouter !");

		node.setPere(father);

		if (node.getMin() > father.getMax())
			father.setFilsDroit(node);
		else if (node.getMax() < father.getMin())
			father.setFilsGauche(node);
		else
			throw new IntervalleChevauchantException("Chevauchement : \nmin : " + node.getMin() + " // max : " + node.getMax());
	}

	/**
	 * Recherche un noeud existant via les bornes de l'intervalle demandé.
	 * 
	 * @param node - le noeud dans lequel on recherche
	 * @param min - la borne minimale de l'intervalle
	 * @param max - la borne maximale de l'intervalle
	 * @return le noeud recherché s'il existe
	 */
	public TreeNode findNode(Node node, int min, int max) {
		throw new NotImplementedException();
	}

	/**
	 * Retourne un arbre binaire caractérisé par son intervalle de valeurs.
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @return le noeud demandé s'il existe
	 * @throws IntervalleInexistantException - lorsque l'intervalle demandé n'existe pas
	 */
	public Node select(int min, int max) throws IntervalleInexistantException {
		throw new NotImplementedException();
	}

	/**
	 * Supprime un ABRI selon l'intervalle spécifié
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @return le noeud supprimé
	 * @throws IntervalleInexistantException lorsque l'intervalle demandé n'existe pas
	 */
	public Node delete(int min, int max) throws IntervalleInexistantException {
		throw new NotImplementedException();
	}

	/**
	 * @return the rootNode
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

}
