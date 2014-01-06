/**
 * 
 */
package datastructure;

import interfaces.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import exceptions.IntervalleChevauchantException;
import exceptions.SimpleNodeMalPositionne;
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
	 */
	public void insert(final int nodeValue) {
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

	/**
	 * Retourne le noeud recherché et représenté par value dans l'arbre dont node est la racine.
	 * 
	 * @param node - La racine de l'arbre dans lequel rechercher
	 * @param value - La valeur que l'on recherche
	 * @return le noeud recherché ou null s'il n'existe pas
	 */
	public SimpleNode findNode(SimpleNode node, final int value) {

		SimpleNode ret = null;

		if (node == null) {
			throw new RuntimeException("Le noeud à rechercher ne peut pas être nul !");
		} else if (value < node.getValue()) { // Si la valeur est inférieure à la valeur du noeud courant, on recherche dans le fils droit

			if (node.getRightSon() != null) {
				ret = findFather((SimpleNode) node.getRightSon(), value);
			}

		} else if (value > node.getValue()) { // Si la valeur est supérieure à la valeur du noeud courant, on recherche dans le fils gauche

			if (node.getLeftSon() != null) {
				ret = findFather((SimpleNode) node.getLeftSon(), value);
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
	 */
	public void insert(SimpleNode node) {
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

		SimpleNode ret, node, father, replacement;

		node = this.findFather(this.root, value);

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
			} else {
				father.setLeftSon(node);
			}
			node.setFather(father);
		}
	}

	/**
	 * Insert un noeud dans un abre binaire de recherche
	 * 
	 * @param node - Le noeud à insérer
	 */
	private void insertInABR(SimpleNode node) {

		SimpleNode father;

		if (node == null) {
			throw new RuntimeException("Le noeud passé en paramètre doit être différent de null !");
		}

		if (this.root == null) {
			this.root = node;
		} else {
			father = this.findFather(this.root, node.getValue());

			if (father.getValue() > node.getValue()) {
				father.setLeftSon(node);
			} else {
				father.setRightSon(node);
			}
			node.setFather(father);
		}
	}

	/**
	 * Retourne la valeur maximale de l'arbre courant.
	 * 
	 * @param node - Le noeud racine de l'arbre dans lequel rechercher
	 * @return le noeud portant la valeur maximale
	 */
	private SimpleNode max(SimpleNode node) {
		if (this.type == TypeABR.ARBRE_BINAIRE_RECHERCHE) {
			return this.maxABR(node);
		}
		return this.maxABRI(node);
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
	 * Retourne le fils portant la valeur maximale dans un ABR
	 * 
	 * @param node - Le noeud racine de l'arbre dans lequel rechercher
	 * @return le noeud portant la valeur maximale
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
	 */
	public AABRI toAABRI(int nbreIntervalles) {

		// Abre binaire retourné par la méthode
		AABRI ret = new AABRI();

		// Variable d'insertion dans l'AABRI
		AABRINode tempNode;

		// Tableau contenant les noeuds sous forme de cdc
		String[] noeudsAsString;

		// Set permettant de trier les valeurs afin qu'il n'y ait pas de chevauchement
		Set<Integer> noeudsAsInteger;

		// Liste des noeuds (noeuds sous représentation de listes)
		List<List<Integer>> noeuds = new ArrayList<List<Integer>>();

		// Liste de stockage temporaire d'un noeud [Min; Max]
		List<Integer> valeursNoeud = new ArrayList<Integer>();

		// Rang de parcours dans l'affectation des valeurs de noeuds
		int i = 0;

		// Récupération des valeurs de l'arbre par un parcours préfixe
		noeudsAsString = this.getInfos(this.root).split(":");

		if (noeudsAsString.length < nbreIntervalles * 2) {
			throw new RuntimeException("Le nombre de valeurs dans l'arbre binaire courant (" + noeudsAsString.length
			        + ") ne permet pas de construire un AABRI avec "
			        + nbreIntervalles + " noeuds !");
		}

		// Ajout des valeurs dans un tableau de type ordered set
		noeudsAsInteger = new TreeSet<>();

		for (String noeud : noeudsAsString) {
			noeudsAsInteger.add(Integer.parseInt(noeud));
		}

		// On découpe ce set en "nbreIntervalles" sous tableaux correspondant aux noeuds
		for (Integer valeur : noeudsAsInteger) {

			// Si le noeud courant possède le nombre de valeurs désiré
			if (valeursNoeud.size() == noeudsAsString.length / nbreIntervalles) {
				noeuds.add(valeursNoeud);
				valeursNoeud = new ArrayList<Integer>();
				valeursNoeud.add(valeur);
			} else if (i + 1 == noeudsAsInteger.size()) { // Si noeud est incomplet (dernier noeud)
				valeursNoeud.add(valeur);
				noeuds.add(valeursNoeud);
			} else {
				valeursNoeud.add(valeur);
			}

			i++;
		}

		// Dans chaque tableau, on enlève (on ne prend pas en compte) la première
		// et la dernière valeur (Min et Max) afin de les affecter aux bornes min et max
		for (List<Integer> noeud : noeuds) {

			i = 0;

			tempNode = new AABRINode();

			// Pour éviter l'arbre filiforme, il faut re mélanger la collection après avoir récupérer le premier et le dernier élément
			tempNode.setMin(noeud.get(0));
			tempNode.setMax(noeud.get(noeud.size() - 1));

			Collections.shuffle(noeud);

			for (Integer valeur : noeud) {

				// Partant du principe qu'il ne peut y avoir deux fois la même valeur dans l'arbre
				if (valeur != tempNode.getMin() && valeur != tempNode.getMax()) {
					tempNode.insert(valeur);
				}
			}

			// Insertion du noeud dans l'arbre d'arbres
			try {
				ret.insert(tempNode);
			} catch (IntervalleChevauchantException e) {
				System.out.println(e.getMessage());
			}
		}

		// Bingo c'est fini (naïf^^)
		return ret;
	}
}
