/**
 * 
 */
package datastructure;

import interfaces.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import exceptions.IntervalleChevauchantException;
import exceptions.IntervalleInexistantException;
import exceptions.ValeurNonRepresenteeDansABRI;

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

		try {
			// Recherche du noeud père
			father = (TreeNode) this.findTreeNode(this.rootNode, node.getMin(), node.getMax());
		} catch (IntervalleInexistantException e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}

		if (father == null)
			throw new RuntimeException("Impossible de retrouver le père du noeud à ajouter !");

		node.setFather(father);

		if (node.getMin() > father.getMax())
			father.setRightSon(node);
		else if (node.getMax() < father.getMin())
			father.setLeftSon(node);
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
	 * @throws IntervalleInexistantException dans le cas où aucun noeud n'a pu être trouvé
	 */
	public TreeNode findTreeNode(TreeNode node, final int min, final int max) throws IntervalleInexistantException {

		TreeNode ret = null;

		if (node == null || min > max || (min == max && max == 0)) {
			throw new RuntimeException("Problèmes dans les paramètres de la méthode");

		} else if (node.getMin() > max) { // Cas où le noeud courant est "plus grand" que l'intervalle donné

			if (node.getLeftSon() == null) {
				throw new IntervalleInexistantException(""
				        + "Intervalle demandé : [" + min + "; " + max + "]\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNode((TreeNode) node.getLeftSon(), min, max);

		} else if (node.getMax() < min) { // Cas où le noeud courant est "plus petit" que l'intervalle donné

			if (node.getRightSon() == null) {
				throw new IntervalleInexistantException(""
				        + "Intervalle demandé : [" + min + "; " + max + "]\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNode((TreeNode) node.getRightSon(), min, max);

		} else if (node.getMin() == min && node.getMax() == max) {
			ret = node;
		} else {
			return null;
		}
		return ret;
	}

	/**
	 * Recherche un TreeNode depuis une valeur. La recherche consiste à parcourir l'AABRI pour trouver le TreeNode dont l'intervalle contient la
	 * valeur.
	 * 
	 * @param node - Noeud racine de l'arbre dans lequel chercher
	 * @param value - La valeur à partir de laquelle on cherche le TreeNode
	 * @return
	 * @throws IntervalleInexistantException dans le cas où aucun intervalle ne contient cette valeur
	 */
	public TreeNode findTreeNodeFromValue(TreeNode node, final int value) throws IntervalleInexistantException {

		TreeNode ret = null;

		if (node == null || value == 0) {
			throw new RuntimeException("Le noeud et la valeur doivent être renseignés !");

		} else if (value < node.getMin()) {

			if (node.getLeftSon() == null) {
				throw new IntervalleInexistantException(""
				        + "Valeur demandée : {" + value + "}\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNodeFromValue((TreeNode) node.getLeftSon(), value);

		} else if (value > node.getMax()) {

			if (node.getRightSon() == null) {
				throw new IntervalleInexistantException(""
				        + "Valeur demandée : {" + value + "}\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNodeFromValue((TreeNode) node.getRightSon(), value);
		} else { // Si on est pas inférieur ni supérieur, c'est qu'on est dans le bon intervalle
			ret = node;
		}
		return ret;
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
	 * @param node - Le noeud d'ABRI à ajouter
	 * @return TreeNode - le noeud d'AABRI dans lequel la valeur a été ajoutée
	 */
	public TreeNode addSimpleNode(SimpleNode node) {
		throw new NotImplementedException();
	}

	/**
	 * Ajoute un entier dans l'un des ABRI de l'AABRI en fonction des intervalles disponibles.
	 * 
	 * @param value - La valeur à ajouter
	 */
	public TreeNode addSimpleNode(int value) {
		return this.addSimpleNode(new SimpleNode(value));
	}

	/**
	 * Supprime une noeud simple d'un ABRI. La méthode parcours l'AABRI à la recherche de l'intervalle contenant la valeur simpleNodeValue. Si
	 * l'intervalle n'existe pas, la méthode lève une exception IntervalleInexistantException. Si la valeur est introuvable, la méthode renvoi null.
	 * Une fois l'intervalle trouvé (et donc l'objet TreeNode), la méthode appelle la méthode TreeNode.delete(int simpleNodeValue)
	 * 
	 * @param simpleNodeValue - La valeur du noeud à supprimer
	 * @return le noeud qui a été retiré, ou null si aucun noeud n'a été trouvé.
	 * @throws IntervalleInexistantException Si la valeur n'appartient à aucun intervalle
	 * @throws ValeurNonRepresenteeDansABRI Si la valeur est bien dans l'intervalle de l'ABRI, mais n'est pas représentée
	 */
	public SimpleNode removeSimpleNode(final int simpleNodeValue) throws IntervalleInexistantException, ValeurNonRepresenteeDansABRI {
		// Penser à supprimer le TreeNode courant lorsqu'il ne contient plus aucun noeud simple (ie lorsque le root == null)

		TreeNode treeNode = this.findTreeNodeFromValue(this.rootNode, simpleNodeValue);

		SimpleNode removedNode = treeNode.delete(simpleNodeValue);

		if (treeNode.getRoot() == null) {
			// TODO optimisation possible en créant une méthode de suppression à partir d'un
			// TreeNode
			this.delete(treeNode.getMin(), treeNode.getMax());
		}
		return removedNode;
	}

	public void ABRtoAABRI() {
		throw new NotImplementedException();
	}

	public void AABRItoABR() {
		throw new NotImplementedException();
	}
}
