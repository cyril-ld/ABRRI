/**
 * 
 */
package datastructure;

import interfaces.Node;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
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
		} else if (value < node.getValeur()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getFilsDroit() == null) {
				ret = node;
			} else {
				ret = findNode((SimpleNode) node.getFilsDroit(), value);
			}
		} else if (value > node.getValeur()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getFilsGauche() == null) {
				ret = node;
			} else {
				ret = findNode((SimpleNode) node.getFilsGauche(), value);
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

		if (node.getValeur() == 0) {
			throw new RuntimeException("La valeur du noeud est nulle !");
		}

		if (this.root == null) {
			this.root = node;
		} else {

			// Recherche du noeud père
			father = this.findNode(this.root, node.getValeur());

			if (father.getValeur() > node.getValeur()) {
				father.setFilsDroit(node);
			} else if (father.getValeur() < node.getValeur()) {
				father.setFilsGauche(node);
			}
		}
	}

	public void delete(int value) {
		throw new NotImplementedException();
	}
}
