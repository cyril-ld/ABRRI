/**
 * 
 */
package datastructure;

import interfaces.Node;
import exceptions.ValeurNonRepresenteeDansABRI;

/**
 * Implémentation d'un noeud de l'AABRI. Ce noeud contient donc un fils droit, un fils gauche, une borne minimale, une borne maximale et un ABRI.
 * 
 * @author Cyril
 * 
 */
public class TreeNode extends Node {

	/**
	 * Valeur minimale de l'intervalle couvert par l'arbre
	 */
	private int min;

	/**
	 * Valeur maximale de l'intervalle couvert par l'arbre
	 */
	private int max;

	/**
	 * Noeud racine stocké par le noeud
	 */
	private SimpleNode root;

	/**
	 * Constructeur d'un noeud d'arbre binaire d'arbres binaires inversés.
	 * 
	 * @param min - la valeur minimale de l'intervalle couvert
	 * @param max - la valeur maximale de l'intervalle couvert
	 * @param rootNode - l'arbre stocké par le noeud
	 */
	public TreeNode(int min, int max, SimpleNode rootNode) {
		this.root = rootNode;
		this.min = min;
		this.max = max;
	}

	/**
	 * Constructeur par défaut
	 */
	public TreeNode() {
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Ajoute un nouveau SimpleNode dans l'ABRI contenu par le noeud courant.
	 * 
	 * @param nodeValue - La valeur stockée par le noeud simple, finale pour éviter toute modification.
	 */
	public void insert(final int nodeValue) {
		this.insert(new SimpleNode(nodeValue));
	}

	/**
	 * Recherche un noeud dans l'ABRI courant.
	 * 
	 * @param node - Le noeud dans lequel on va chercher parmis ses fils
	 * @param value - La valeur caractérisant le noeud
	 * @return le noeud que l'on recherche, ou null si aucun noeud ne stocke la valeur recherchée
	 */
	public SimpleNode findNode(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value < node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() == null) {
				ret = node;
			} else {
				ret = findNode((SimpleNode) node.getRightSon(), value);
			}
		} else if (value > node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() == null) {
				ret = node;
			} else {
				ret = findNode((SimpleNode) node.getLeftSon(), value);
			}
		} else {
			ret = node;
		}

		return ret;
	}

	/**
	 * Insert un nouveau noeud dans l'ABRI courant.
	 * 
	 * @param node - le noeud à ajouter
	 */
	public void insert(SimpleNode node) {

		SimpleNode father;

		if (node.getValue() == 0) {
			throw new RuntimeException("La valeur du noeud est nulle !");
		}

		if (this.root == null) {
			this.root = node;
		} else {

			// Recherche du noeud père
			father = this.findNode(this.root, node.getValue());

			if (father.getValue() > node.getValue()) {
				father.setRightSon(node);
			} else if (father.getValue() < node.getValue()) {
				father.setLeftSon(node);
			}

			node.setFather(father);
		}
	}

	/**
	 * Supprime un noeud simple dans l'un des ABRI contenu dans les noeuds de l'arbre.
	 * 
	 * @param value - La valeur du noeud à supprimer.
	 * @throws ValeurNonRepresenteeDansABRI dans le cas où la valeur recherchée n'est pas représentée dans l'ABRI courant, malgré le fait que
	 * l'intervalle encadre celle-ci.
	 */
	public SimpleNode delete(int value) throws ValeurNonRepresenteeDansABRI {

		SimpleNode ret, node, father, replacement;

		node = this.findNode(this.root, value);

		if (node == null) {
			throw new ValeurNonRepresenteeDansABRI("Impossible de retrouver le noeud contenant la valeur : " + value + "\n");
		}

		ret = null;
		father = (SimpleNode) node.getFather();

		// Dans le cas où le noeud est une feuille, il suffit de débrancher son noeud père s'il existe
		if (node.getLeftSon() == null && node.getRightSon() == null) {

			if (father != null) {

				if (father.getRightSon() == node) {
					father.setRightSon(null);
					ret = node;
				} else if (father.getLeftSon() == node) {
					father.setLeftSon(null);
					ret = node;
				}
			}

		} else if ((node.getLeftSon() == null || node.getRightSon() == null)) { // Dans le cas où le noeud a un seul fils

			if (father != null) {

				if (father.getRightSon() == node) { // Noeud courant == fils droit du père

					if (node.getRightSon() != null) { // Si le noeud a seulement un fils droit, on fait le branchement avec le père
						father.setRightSon(node.getRightSon());
						node.getRightSon().setFather(father);
						ret = node;
					} else {
						father.setRightSon(node.getLeftSon());
						node.getLeftSon().setFather(father);
						ret = node;
					}
				} else if (father.getLeftSon() == node) { // Noeud courant == fils gauche du père

					if (node.getLeftSon() != null) {
						father.setLeftSon(node.getLeftSon());
						node.getLeftSon().setFather(father);
						ret = node;
					} else {
						father.setLeftSon(node.getRightSon());
						node.getRightSon().setFather(father);
						ret = node;
					}
				}
			} else { // Lorsqu'on souhaite supprimer le noeud root

				if (node.getRightSon() != null) {
					this.root = (SimpleNode) node.getRightSon();
					node.getRightSon().setFather(null);
					ret = node;
				} else {
					this.root = (SimpleNode) node.getLeftSon();
					node.getLeftSon().setFather(null);
					ret = node;
				}
			}
		} else { // Lorsque le noeud à supprimer a deux fils

			// 1è étape : on recherche le noeud possédant la valeur immédiatement inférieure dans le sad (noeud remplaçant)
			// 2è étape : on supprime ce noeud tout en conservant une copie
			// 3è étape : on affecte la valeur du noeud remplaçant dans le noeud que l'on souhaite supprimer
			replacement = this.delete(this.max((SimpleNode) node.getLeftSon()).getValue());

			// Mémorisation des information pour retour de fonction
			ret = new SimpleNode(node.getValue());
			ret.setFather(node.getFather());
			ret.setLeftSon(node.getLeftSon());
			ret.setRightSon(node.getRightSon());

			// On affecte au noeud à supprimer la valeur du noeud remplaçant (tour de passe passe ;))
			node.setValue(replacement.getValue());
			node.setFather(replacement.getFather());
			node.setRightSon(replacement.getRightSon());
			node.setLeftSon(replacement.getLeftSon());
		}
		return ret;
	}

	/**
	 * Recherche le noeud contenant la valeur maximale dans le sous arbre dont node est la racine.
	 * 
	 * @param node - Racine du sous arbre dans lequel rechercher la valeur maximale
	 * @return le noeud contenant la valeur maximale
	 */
	private SimpleNode max(SimpleNode node) {

		SimpleNode ret = null;

		if (node.getLeftSon() == null) {
			ret = node;
		} else {
			ret = max((SimpleNode) node.getLeftSon());
		}

		return ret;
	}

	/**
	 * @return the root
	 */
	public SimpleNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(SimpleNode root) {
		this.root = root;
	}

	/**
	 * Créé une représentation des informations contenues dans l'ABRI contenu par le noeud. Le parcours est préfixe.
	 * 
	 * @param node - La racine de l'abre dont on souhaite afficher les valeurs.
	 * @return - Une chaîne contenant l'ensemble des informations de l'arbre.
	 */
	public String getInfos(SimpleNode node) {

		String ret, lSon, rSon;

		if (node != null) {
			lSon = this.getInfos((SimpleNode) node.getLeftSon());
			rSon = this.getInfos((SimpleNode) node.getRightSon());
			if ((lSon == null || lSon.equals("")) && rSon != null && !rSon.equals("")) {
				ret = node.getValue() + ":" + rSon;
			} else if ((lSon != null && !lSon.equals("") && (rSon == null || rSon.equals("")))) {
				ret = node.getValue() + ":" + lSon;
			} else if (lSon != null && !lSon.equals("") && rSon != null && !rSon.equals("")) {
				ret = node.getValue() + ":" + lSon + ":" + rSon;
			} else {
				ret = Integer.toString(node.getValue());
			}
		} else {
			ret = "";
		}
		return ret;
	}

	/**
	 * Vérifie si l'arbre dont le noeud passé en paramètre est la racine est bien formé.
	 * 
	 * @param node - La racine de l'ABRI à vérifier
	 * @return true si le sous arbre est bien formé, false sinon
	 */
	public boolean isWellFormed(SimpleNode node) {

		// On retourne true dans le cas où le noeud est une feuille
		boolean ret = true;

		// Si node a un fils gauche correctement positionné on descend dedans
		if (node.getLeftSon() != null) {

			// Si le fils gauche est bien > au noeud courant, c'est bon
			if (((SimpleNode) node.getLeftSon()).getValue() > node.getValue()) {
				ret = isWellFormed((SimpleNode) node.getLeftSon());
			} else {
				ret = false;
			}
		} else if (node.getRightSon() != null) {

			if (((SimpleNode) node.getRightSon()).getValue() < node.getValue()) {
				ret = isWellFormed((SimpleNode) node.getRightSon());
			} else {
				ret = false;
			}
		}
		return ret;
	}
}
