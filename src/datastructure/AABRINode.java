/**
 * 
 */
package datastructure;

import interfaces.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.IntervalleChevauchantException;
import exceptions.IntervalleInexistantException;
import exceptions.SimpleNodeMalPositionne;
import exceptions.ValeurDejaPresenteException;
import exceptions.ValeurNonRepresenteeDansABRI;

/**
 * Implémentation d'un noeud de l'AABRI. Ce noeud contient donc un fils droit, un fils gauche, une borne minimale, une borne maximale et un ABRI.
 * 
 * Par manque de temps pour le moment, la rustine du typeABR est utilisée au lieu d'un héritage propre. TODO
 * 
 * @author Cyril
 * 
 */
public class AABRINode extends Node {

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
	 * Type de l'arbre stocké par le noeud
	 */
	private TypeABR type;

	/**
	 * Constructeur d'un noeud d'arbre binaire d'arbres binaires inversés.
	 * 
	 * @param min - la valeur minimale de l'intervalle couvert
	 * @param max - la valeur maximale de l'intervalle couvert
	 * @param rootNode - l'arbre stocké par le noeud
	 * @param type - le type de l'abre binaire, Cf. enum datastructure.TypeABR pour les types disponibles
	 */
	public AABRINode(int min, int max, SimpleNode rootNode, TypeABR type) {
		this.root = rootNode;
		this.min = min;
		this.max = max;
		this.type = type;
	}

	/**
	 * Constructeur par défaut. Par défaut, le type d'arbre binaire stocké est ABRI (cadre du projet).
	 */
	public AABRINode() {
		this.type = TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE;
	}

	/**
	 * Constructeur permettant d'affecter le type de l'arbre, aucun autre traitement
	 * 
	 * @param type - Le type de l'arbre
	 */
	public AABRINode(TypeABR type) {
		this.type = type;
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
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 */
	public void insert(final int nodeValue) throws ValeurDejaPresenteException {
		this.insert(new SimpleNode(nodeValue));
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
	 * @return the type
	 */
	public TypeABR getType() {
		return type;
	}

	/**
	 * Recherche le noeud père du noeud contenant value.
	 * 
	 * @param node - Le noeud dans lequel on va chercher parmis ses fils
	 * @param value - La valeur caractérisant le noeud
	 * @return le noeud que l'on recherche, ou null si aucun noeud ne stocke la valeur recherchée
	 */
	public SimpleNode findFather(SimpleNode node, final int value) {

		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE) {
			return this.findFatherInABR(node, value);
		}
		return this.findFatherInABRI(node, value);
	}

	/**
	 * Recherche le noeud père du noeud contenant value dans un ABR dont la racine est node.
	 * 
	 * @param node - Le noeud racine de l'arbre dans lequel rechercher
	 * @param value - La valeur dont on souhaite trouver le noeud père
	 * @return le noeud père
	 */
	private SimpleNode findFatherInABR(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value > node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() == null) {
				ret = node;
			} else {
				ret = findFather((SimpleNode) node.getRightSon(), value);
			}
		} else if (value < node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() == null) {
				ret = node;
			} else {
				ret = findFather((SimpleNode) node.getLeftSon(), value);
			}
		} else {
			ret = node;
		}
		return ret;
	}

	/**
	 * Recherche le noeud père du noeud contenant value dans un ABRI dont la racine est node.
	 * 
	 * @param node - Le noeud racine de l'arbre dans lequel rechercher
	 * @param value - La valeur dont on souhaite trouver le père.
	 * @return le noeud père du noeud contenant la valeur.
	 */
	private SimpleNode findFatherInABRI(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value < node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() == null) {
				ret = node;
			} else {
				ret = findFather((SimpleNode) node.getRightSon(), value);
			}
		} else if (value > node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() == null) {
				ret = node;
			} else {
				ret = findFather((SimpleNode) node.getLeftSon(), value);
			}
		} else {
			ret = node;
		}
		return ret;
	}

	public SimpleNode findNode(SimpleNode node, final int value) {
		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE) {
			return this.findNodeABR(node, value);
		}
		return this.findNodeABRI(node, value);
	}

	/**
	 * Retourne le noeud recherché et représenté par value dans l'arbre dont node est la racine.
	 * 
	 * @param node - La racine de l'arbre dans lequel rechercher
	 * @param value - La valeur que l'on recherche
	 * @return le noeud recherché ou null s'il n'existe pas
	 */
	private SimpleNode findNodeABRI(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (this.root == null) {
			throw new RuntimeException("Le noeud [" + this.min + "; " + this.max + "] est vide et ne contient donc pas " + value + " !");
		}
		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value < node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() != null) {
				ret = findNodeABRI((SimpleNode) node.getRightSon(), value);
			}

		} else if (value > node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() != null) {
				ret = findNodeABRI((SimpleNode) node.getLeftSon(), value);
			}

		} else if (value == node.getValue()) {
			ret = node;
		}
		return ret;
	}

	/**
	 * Retourne le noeud recherché et représenté par value dans l'arbre dont node est la racine.
	 * 
	 * @param node - La racine de l'arbre dans lequel rechercher
	 * @param value - La valeur que l'on recherche
	 * @return le noeud recherché ou null s'il n'existe pas
	 */
	private SimpleNode findNodeABR(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (this.root == null) {
			throw new RuntimeException("Le noeud [" + this.min + "; " + this.max + "] est vide et ne contient donc pas " + value + " !");
		}
		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value > node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() != null) {
				ret = findNodeABR((SimpleNode) node.getRightSon(), value);
			}

		} else if (value < node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() != null) {
				ret = findNodeABR((SimpleNode) node.getLeftSon(), value);
			}

		} else if (value == node.getValue()) {
			ret = node;
		}
		return ret;
	}

	/**
	 * Insert un nouveau noeud dans l'ABRI courant.
	 * 
	 * @param node - le noeud à ajouter
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 */
	public void insert(SimpleNode node) throws ValeurDejaPresenteException {
		if (node == null) {
			throw new RuntimeException("Le noeud à insérer ne doit pas être nul!");
		} else if (this.type == null) {
			throw new RuntimeException("Un type d'arbre doit être donné pour que l'insertion soit possible !");
		}

		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE) {
			this.insertInABR(node);
		} else if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE) {
			this.insertInABRI(node);
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
		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE) {
			return this.deleteABR(value);
		}
		return this.deleteABRI(value);
	}

	/**
	 * Supprime un noeud simple dans l'un des ABRI contenu dans les noeuds de l'arbre.
	 * 
	 * @param value - La valeur du noeud à supprimer.
	 * @throws ValeurNonRepresenteeDansABRI dans le cas où la valeur recherchée n'est pas représentée dans l'ABRI courant, malgré le fait que
	 * l'intervalle encadre celle-ci.
	 */
	private SimpleNode deleteABRI(int value) throws ValeurNonRepresenteeDansABRI {

		SimpleNode ret, node, father, replacement;

		node = this.findNode(this.root, value);

		if (node == null) {
			throw new ValeurNonRepresenteeDansABRI("Impossible de retrouver le noeud contenant la valeur : " + value + "\n");
		}

		ret = new SimpleNode();
		father = (SimpleNode) node.getFather();

		// Dans le cas où le noeud est une feuille, il suffit de débrancher son noeud père s'il existe
		if (node.getLeftSon() == null && node.getRightSon() == null) {

			if (father != null) {

				if (father.getRightSon() == node) {
					father.setRightSon(null);
				} else if (father.getLeftSon() == node) {
					father.setLeftSon(null);
				}
				ret = node;
			} else { // Lorsqu'on souhaite supprimer le noeud root
				ret = new SimpleNode(this.root.getValue());
				this.root = null;
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
			} else { // On essaie de supprimer la racine

				ret.setValue(node.getValue());
				ret.setFather(node.getFather());
				ret.setLeftSon(node.getLeftSon());
				ret.setRightSon(node.getRightSon());

				if (node.getLeftSon() == null) {
					replacement = this.deleteABRI(this.maxABRI((SimpleNode) node.getRightSon()).getValue());
					node.setValue(replacement.getValue());
				} else {
					this.root = (SimpleNode) node.getLeftSon();
					this.root.setFather(null);
				}
			}
		} else { // Lorsque le noeud à supprimer a deux fils

			// 1è étape : on recherche le noeud possédant la valeur immédiatement inférieure dans le sad (noeud remplaçant)
			// 2è étape : on supprime ce noeud tout en conservant une copie
			// 3è étape : on affecte la valeur du noeud remplaçant dans le noeud que l'on souhaite supprimer
			replacement = this.deleteABRI(this.maxABRI((SimpleNode) node.getRightSon()).getValue());

			// Mémorisation des information pour retour de fonction
			ret.setValue(node.getValue());
			ret.setFather(node.getFather());
			ret.setLeftSon(node.getLeftSon());
			ret.setRightSon(node.getRightSon());

			// On affecte au noeud à supprimer la valeur du noeud remplaçant (tour de passe passe ;))
			node.setValue(replacement.getValue());
		}
		return ret;
	}

	/**
	 * Supprime un noeud simple dans l'un des ABRI contenu dans les noeuds de l'arbre.
	 * 
	 * @param value - La valeur du noeud à supprimer.
	 * @throws ValeurNonRepresenteeDansABRI dans le cas où la valeur recherchée n'est pas représentée dans l'ABRI courant, malgré le fait que
	 * l'intervalle encadre celle-ci.
	 */
	private SimpleNode deleteABR(int value) throws ValeurNonRepresenteeDansABRI {

		SimpleNode ret, node, father, replacement;

		node = this.findNode(this.root, value);

		if (node == null) {
			throw new ValeurNonRepresenteeDansABRI("Impossible de retrouver le noeud contenant la valeur : " + value + "\n");
		}

		ret = new SimpleNode();
		father = (SimpleNode) node.getFather();

		// Dans le cas où le noeud est une feuille, il suffit de débrancher son noeud père s'il existe
		if (node.getLeftSon() == null && node.getRightSon() == null) {

			if (father != null) {

				if (father.getRightSon() == node) {
					father.setRightSon(null);
				} else if (father.getLeftSon() == node) {
					father.setLeftSon(null);
				}
				ret = node;
			} else { // Lorsqu'on souhaite supprimer le noeud root
				ret = new SimpleNode(this.root.getValue());
				this.root = null;
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
			} else { // On essaie de supprimer la racine

				ret.setValue(node.getValue());
				ret.setFather(node.getFather());
				ret.setLeftSon(node.getLeftSon());
				ret.setRightSon(node.getRightSon());

				if (node.getRightSon() == null) {
					replacement = this.deleteABR(this.maxABR((SimpleNode) node.getLeftSon()).getValue());
					node.setValue(replacement.getValue());
				} else {
					this.root = (SimpleNode) node.getRightSon();
					this.root.setFather(null);
				}
			}
		} else { // Lorsque le noeud à supprimer a deux fils

			// 1è étape : on recherche le noeud possédant la valeur immédiatement inférieure dans le sad (noeud remplaçant)
			// 2è étape : on supprime ce noeud tout en conservant une copie
			// 3è étape : on affecte la valeur du noeud remplaçant dans le noeud que l'on souhaite supprimer
			replacement = this.deleteABR(this.maxABR((SimpleNode) node.getLeftSon()).getValue());

			// Mémorisation des information pour retour de fonction
			ret.setValue(node.getValue());
			ret.setFather(node.getFather());
			ret.setLeftSon(node.getLeftSon());
			ret.setRightSon(node.getRightSon());

			// On affecte au noeud à supprimer la valeur du noeud remplaçant (tour de passe passe ;))
			node.setValue(replacement.getValue());
		}
		return ret;
	}

	/**
	 * Insert un noeud dans un abre binaire de recherche inversé.
	 * 
	 * @param node - Le noeud à insérer
	 */
	private void insertInABRI(SimpleNode node) {

		SimpleNode father;

		if (node.getValue() == 0) {
			throw new RuntimeException("La valeur du noeud est nulle !");
		}

		if (this.root == null) {
			this.root = node;
		} else {

			// Recherche du noeud père
			father = this.findFather(this.root, node.getValue());

			if (father.getValue() > node.getValue()) {
				father.setRightSon(node);
				node.setFather(father);
			} else if (father.getValue() < node.getValue()) {
				father.setLeftSon(node);
				node.setFather(father);
			}
		}
	}

	/**
	 * Insert un noeud dans un abre binaire de recherche
	 * 
	 * @param node - Le noeud à insérer
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 */
	private void insertInABR(SimpleNode node) throws ValeurDejaPresenteException {

		SimpleNode father;

		if (node == null) {
			throw new RuntimeException("Le noeud passé en paramètre doit être différent de null !");
		}

		if (this.root == null) {
			this.root = node;
		} else {
			father = this.findFather(this.root, node.getValue());

			if (father.getValue() == node.getValue()) {
				throw new ValeurDejaPresenteException("La valeur " + node.getValue() + " est déjà présente dans l'arbre, insertion impossible !");
			}

			if (father.getValue() > node.getValue()) {
				father.setLeftSon(node);
			} else if (father.getValue() < node.getValue()) {
				father.setRightSon(node);
			}
			node.setFather(father);
		}
	}

	/**
	 * Recherche le noeud contenant la valeur maximale dans le sous arbre dont node est la racine.
	 * 
	 * @param node - Racine du sous arbre dans lequel rechercher la valeur maximale
	 * @return le noeud contenant la valeur maximale
	 */
	private SimpleNode maxABRI(SimpleNode node) {

		SimpleNode ret = null;

		if (node.getLeftSon() == null) {
			ret = node;
		} else {
			ret = maxABRI((SimpleNode) node.getLeftSon());
		}

		return ret;
	}

	/**
	 * Recherche le noeud contenant la valeur maximale dans le sous arbre dont node est la racine.
	 * 
	 * @param node - Racine du sous arbre dans lequel rechercher la valeur maximale
	 * @return le noeud contenant la valeur maximale
	 */
	private SimpleNode maxABR(SimpleNode node) {

		SimpleNode ret = null;

		if (node.getRightSon() == null) {
			ret = node;
		} else {
			ret = maxABR((SimpleNode) node.getRightSon());
		}

		return ret;
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
	 * @throws SimpleNodeMalPositionne lorsqu'un noeud est mal positionné dans l'arbre
	 */
	public boolean isWellFormed(SimpleNode node) throws SimpleNodeMalPositionne {
		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE) {
			return this.isWellFormedABRI(node);
		}
		return this.isWellFormedABR(node);
	}

	/**
	 * Vérifie si l'arbre dont le noeud passé en paramètre est la racine est bien formé.
	 * 
	 * @param node - La racine de l'ABRI à vérifier
	 * @return true si le sous arbre est bien formé, false sinon
	 * @throws SimpleNodeMalPositionne lorsqu'un noeud est mal positionné dans l'arbre
	 */
	private boolean isWellFormedABR(SimpleNode node) throws SimpleNodeMalPositionne {
		// On retourne true dans le cas où le noeud est une feuille
		boolean ret = true;

		if (node != null) {
			// Si node a un fils gauche correctement positionné on descend dedans
			if (node.getLeftSon() != null) {

				// Si le fils gauche est bien > au noeud courant, on descend dedans pour vérifier
				if (((SimpleNode) node.getLeftSon()).getValue() < node.getValue()) {
					ret = isWellFormed((SimpleNode) node.getLeftSon());
				} else {
					throw new SimpleNodeMalPositionne("Valeur noeud courant : " + node.getValue() + ", valeur fils gauche : "
					        + ((SimpleNode) node.getLeftSon()).getValue() + ".");
				}
			} else if (node.getRightSon() != null) {

				if (((SimpleNode) node.getRightSon()).getValue() > node.getValue()) {
					ret = isWellFormed((SimpleNode) node.getRightSon());
				} else {
					throw new SimpleNodeMalPositionne("Valeur noeud courant : " + node.getValue() + ", valeur fils gauche : "
					        + ((SimpleNode) node.getRightSon()).getValue() + ".");
				}
			}
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * Vérifie si l'arbre dont le noeud passé en paramètre est la racine est bien formé.
	 * 
	 * @param node - La racine de l'ABRI à vérifier
	 * @return true si le sous arbre est bien formé, false sinon
	 * @throws SimpleNodeMalPositionne lorsqu'un noeud est mal positionné dans l'arbre
	 */
	private boolean isWellFormedABRI(SimpleNode node) throws SimpleNodeMalPositionne {

		// On retourne true dans le cas où le noeud est une feuille
		boolean ret = true;

		if (node != null) {

			// Si node a un fils gauche correctement positionné on descend dedans
			if (node.getLeftSon() != null) {

				// Si le fils gauche est bien > au noeud courant, on descend dedans pour vérifier
				if (((SimpleNode) node.getLeftSon()).getValue() > node.getValue()) {
					ret = isWellFormed((SimpleNode) node.getLeftSon());
				} else {
					throw new SimpleNodeMalPositionne("Valeur noeud courant : " + node.getValue() + ", valeur fils gauche : "
					        + ((SimpleNode) node.getLeftSon()).getValue() + ".");
				}
			} else if (node.getRightSon() != null) {

				if (((SimpleNode) node.getRightSon()).getValue() < node.getValue()) {
					ret = isWellFormed((SimpleNode) node.getRightSon());
				} else {
					throw new SimpleNodeMalPositionne("Valeur noeud courant : " + node.getValue() + ", valeur fils gauche : "
					        + ((SimpleNode) node.getRightSon()).getValue() + ".");
				}
			} else if (node.getValue() > this.max || node.getValue() < this.min) {
				throw new SimpleNodeMalPositionne("Valeur noeud courant : " + node.getValue() + ". Intervalle correspondant : [" + this.min + "; "
				        + this.max + "].");

			}
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * <pre>
	 * 	Soit A un ABR dont les éléments sont compris entre Min et Max et soit un entier k. La méthode découpe l'intervalle
	 * 	[Min; Max] en k intervalles de sensiblement la même taille [a1; b1], [a2; b2] ... [an; bn].
	 * 	Ces intervalles sont tq : a1 = Min et b1 = Max pour 1 <= i <= k-1.
	 * 	Chaque noeud de l'AABRI sera tq a(i+1) = b(i) + 1.
	 * </pre>
	 * 
	 * Renvoie un AABRI à partir des données contenues dans le noeud courant.
	 * 
	 * La fonction ne se trouve pas dans la classe utils car on ne souhaite pas qu'une classe fille de AABRINode puisse l'utiliser (on imagine que si
	 * la méthode était dans la classe utils, elle prendrait en paramètre un AABRINode).
	 * 
	 * @param nbreIntervalles - le nombre d'intervalles (ie le nombre de noeuds dans l'AABRI créé)
	 * @return l'AABRI créé
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 * @throws IntervalleChevauchantException si un intervalle possède une partie commune avec un autre
	 * @throws IntervalleInexistantException si l'intervalle n'existe pas
	 */
	public AABRI toAABRI(int nbreIntervalles) throws RuntimeException, ValeurDejaPresenteException, IntervalleChevauchantException,
	        IntervalleInexistantException {

		// Abre binaire retourné par la méthode
		AABRI ret = new AABRI();

		// Variable d'insertion dans l'AABRI
		AABRINode tempNode;

		// Tableau contenant les noeuds sous forme de cdc
		String[] noeudsAsString;

		// Liste de stockage de toutes les valeurs de l'ABR
		List<Integer> valeursABR = new ArrayList<Integer>();

		// Collection des bornes
		List<Integer> bornes = new ArrayList<Integer>();

		// Liste utilisée pour mélanger les intervalles afin de ne pas créer un arbre filiforme
		List<int[]> intervallesRandom = new ArrayList<int[]>();

		// Rang de parcours dans l'affectation des valeurs de noeuds
		int i = 0;

		int[] tmp;

		int tailleIntervallesEntiere;

		// Récupération des valeurs de l'arbre par un parcours préfixe
		noeudsAsString = this.getInfos(this.root).split(":");

		if ((this.max - this.min + 1) < nbreIntervalles * 2) {
			throw new RuntimeException("L'amplitude de valeurs dans l'arbre binaire courant (" + (this.max - this.min + 1)
			        + ") ne permet pas de construire un AABRI avec "
			        + nbreIntervalles + " noeuds !");
		}

		// Ajout des valeurs dans un tableau de type ordered set
		valeursABR = new ArrayList<Integer>();

		for (String noeud : noeudsAsString) {
			valeursABR.add(Integer.parseInt(noeud));
		}

		tailleIntervallesEntiere = (this.getMax() - this.getMin() + 1) / nbreIntervalles;

		// Compteur permettant de connaitre le nombre de valeur parcourues depuis le dernier ajout dans les bornes
		i = 0;

		// Remplissage de l'AABRI avec des noeuds contenants seulement les
		for (int j = this.min; j <= this.max - 1; j++) {

			if (i != tailleIntervallesEntiere) {
				if (j == this.min) {
					bornes.add(j);
				} else if (j == this.max && bornes.get(j - 1) != j) {
					bornes.add(j);
				}
				i++;
			} else {
				bornes.add(j);
				bornes.add(j + 1);
				i = 1;
				if (j + 1 == this.max) {
					break;
				}
			}
		}

		if (bornes.size() == 1) {
			bornes.add(this.max);
		}

		if (bornes.size() % 2 == 1) {
			bornes.set(bornes.size() - 2, this.max);
			bornes.remove(bornes.size() - 1);
		}

		for (int j = 0; j < bornes.size() - 1; j = j + 2) {

			tmp = new int[2];
			tmp[0] = bornes.get(j);
			tmp[1] = bornes.get(j + 1);

			intervallesRandom.add(tmp);
		}

		Collections.shuffle(intervallesRandom);

		// Parcours des intervalles et ajout de noeuds dans l'AABRI
		for (int j = 0; j < intervallesRandom.size(); j++) {
			tmp = intervallesRandom.get(j);

			tempNode = new AABRINode();
			tempNode.setMin(tmp[0]);
			tempNode.setMax(tmp[1]);
			ret.insert(tempNode);
		}

		// Envisager d'ajouter les noeuds dans une liste et mélanger la liste pour éviter l'arbre filiforme
		for (int valeur : valeursABR) {
			ret.addSimpleNode(valeur);
		}
		return ret;
	}
}
