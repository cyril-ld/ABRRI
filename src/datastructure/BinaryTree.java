/**
 * 
 */
package datastructure;

import interfaces.Node;
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
	private TreeNode rootNode;

	/**
	 * Ajoute un noeud type "ABRI" dans l'arbre binaire courant. Construit un TreeNode à partir des valeurs qui sont passées en paramètre et appelle
	 * la méthode insert(TreeNode node).
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @param rootNode - arbre binaire stocké dans le noeud
	 * @return - true si le noeud a bien été ajouté, false sinon.
	 * @throws IntervalleChevauchantException lors de l'ajout d'un noeud dont l'intervalle chevauche un intervalle existant
	 * @see insert(TreeNode node)
	 */
	public void insert(int min, int max, SimpleNode rootNode) throws IntervalleChevauchantException {
		TreeNode node = new TreeNode(min, max, rootNode);
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
	 * @param min - la borne minimale de l'intervalle, ne doit pas être modifiée
	 * @param max - la borne maximale de l'intervalle, ne doit pas être modifiée
	 * @return le noeud recherché s'il existe
	 */
	public TreeNode findNode(TreeNode node, final int min, final int max) {

		TreeNode ret = null;

		if (node == null || min > max || (min == max && max == 0)) {
			throw new RuntimeException("Problèmes dans les paramètres de la méthode");
		} else if (node.getMin() > max) { // Cas où le noeud courant est "plus grand" que l'intervalle donnée

			if (node.getFilsGauche() == null) {
				ret = node;
			} else {
				ret = findNode((TreeNode) node.getFilsGauche(), min, max);
			}

		} else if (node.getMax() < min) { // Cas où le noeud courant est "plus petit" que l'intervalle donnée

			if (node.getFilsDroit() == null) {
				ret = node;
			} else {
				ret = findNode((TreeNode) node.getFilsDroit(), min, max);
			}

		} else if (node.getMin() == min && node.getMax() == max) {
			ret = node;
		} else {
			return null;
		}
		return ret;
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
	 * Méthode en charge d'ajouter un noeud simple dans l'AABRI. La méthode parcours l'AABRI à la recherche d'un intervalle qui contient la valeur à
	 * ajouter. Si l'intervalle n'existe pas et si la valeur existe déjà dans l'ABRI, la méthode ne fait rien. Une fois l'intervalle trouvée, la
	 * méthode fait appel à la fonction TreeNode.insert(SimpleNode).
	 * 
	 * @param node - Le noeud à ajouter
	 */
	public void addSimpleNode(SimpleNode node) {
		throw new NotImplementedException();
	}

	/**
	 * Supprime une noeud simple d'un ABRI. La méthode parcours l'AABRI à la recherche de l'intervalle contenant la valeur simpleNodeValue. Si
	 * l'intervalle n'existe pas, la méthode lève une exception IntervalleInexistantException. Si la valeur est introuvable, la méthode renvoi null.
	 * Une fois l'intervalle trouvé (et donc l'objet TreeNode), la méthode appelle la méthode TreeNode.delete(int simpleNodeValue)
	 * 
	 * @param simpleNodeValue - La valeur du noeud à supprimer
	 * @return le noeud qui a été retiré, ou null si aucun noeud n'a été trouvé.
	 */
	public SimpleNode removeSimpleNode(int simpleNodeValue) {
		throw new NotImplementedException();
	}

	public void ABRtoAABRI() {
		throw new NotImplementedException();
	}

	public void AABRItoABR() {
		throw new NotImplementedException();
	}
}
