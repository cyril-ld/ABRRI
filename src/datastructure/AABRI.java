/**
 * 
 */
package datastructure;

import interfaces.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import exceptions.AABRINodeMalPositionne;
import exceptions.IntervalleChevauchantException;
import exceptions.IntervalleInexistantException;
import exceptions.SimpleNodeMalPositionne;
import exceptions.ValeurDejaPresenteException;
import exceptions.ValeurNonRepresenteeDansABRI;

/**
 * @author Cyril
 * 
 */
public class AABRI {

	/**
	 * Noeud racine de l'arbre.
	 */
	private AABRINode rootNode;

	/**
	 * Ajoute un noeud type "ABRI" dans l'arbre binaire courant. Construit un TreeNode à partir des valeurs qui sont passées en paramètre et appelle
	 * la méthode insert(TreeNode node).
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @param rootNode - arbre binaire stocké dans le noeud
	 * @param typeNode - Le type d'arbre stocké dans le noeud
	 * @return - true si le noeud a bien été ajouté, false sinon.
	 * @throws IntervalleChevauchantException lors de l'ajout d'un noeud dont l'intervalle chevauche un intervalle existant
	 * @see insert(TreeNode node)
	 */
	public void insert(int min, int max, SimpleNode rootNode, TypeABR typeNode) throws IntervalleChevauchantException {
		AABRINode node = new AABRINode(min, max, rootNode, typeNode);
		this.insert(node);
	}

	/**
	 * Supprime une valeur dans l'un des noeuds de l'AABRI
	 * 
	 * @param value - La valeur à supprimer
	 * @return true si la valeur a bien été supprimée, false sinon
	 * @throws IntervalleInexistantException dans le cas où l'intervalle n'existe pas.
	 * @throws ValeurNonRepresenteeDansABRI Dans le cas où la valeur n'existe pas dans l'ABRI trouvé.
	 */
	public boolean delete(int value) throws IntervalleInexistantException, ValeurNonRepresenteeDansABRI {
		AABRINode owner = this.findTreeNodeFromValue(this.rootNode, value);
		return owner.delete(value) != null ? true : false;
	}

	/**
	 * Ajoute un noeud type "ABRI" dans l'arbre binaire courant
	 * 
	 * @param node - le noeud à ajouter.
	 * @throws IntervalleChevauchantException lors de l'ajout d'un noeud dont l'intervalle chevauche un intervalle existant
	 */
	@SuppressWarnings("cast")
	public void insert(AABRINode node) throws IntervalleChevauchantException {

		AABRINode father = null;

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
		father = (AABRINode) this.findFather(this.rootNode, node.getMin(), node.getMax());

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
	 * @param min - la borne minimale de l'intervalle du nouveau noeud, ne doit pas être modifiée
	 * @param max - la borne maximale de l'intervalle du nouveau noeud, ne doit pas être modifiée
	 * @return le noeud recherché s'il existe
	 */
	public AABRINode findTreeNode(AABRINode node, final int min, final int max) {

		AABRINode ret = null;

		if (node == null || min > max || (min == max && max == 0)) {
			throw new RuntimeException("Problèmes dans les paramètres de la méthode");

		} else if (node.getMin() > max) { // Cas où le noeud courant est "plus grand" que le max du nouveau noeud

			if (node.getLeftSon() != null) {
				ret = findTreeNode((AABRINode) node.getLeftSon(), min, max);
			}

		} else if (node.getMax() < min) { // Cas où le noeud courant est "plus petit" que l'intervalle donné

			if (node.getRightSon() != null) {
				ret = findTreeNode((AABRINode) node.getRightSon(), min, max);
			}
		} else if (node.getMin() == min && node.getMax() == max) {
			ret = node;
		} else {
			return null;
		}
		return ret;
	}

	/**
	 * Permet de retrouver le père du noeud que l'on cherche, notamment lors de l'insertion.
	 * 
	 * @param node - Le noeud racine de l'arbre dans lequel chercher
	 * @param min - Le minimum de l'intervalle recherché
	 * @param max - Le maximum de l'intervalle recherché
	 * @return le noeud recherché ou null si le noeud n'existe pas.
	 * @throws IntervalleChevauchantException dans le cas où l'intervalle existe déjà !
	 */
	private AABRINode findFather(AABRINode node, final int min, final int max) throws IntervalleChevauchantException {
		AABRINode ret = null;

		if (node == null || min > max || (min == max && max == 0) || min == max) {
			throw new RuntimeException("Problèmes dans les paramètres de la méthode");

		} else if (node.getMin() > max) { // Cas où le noeud courant est "plus grand" que le max du nouveau noeud

			if (node.getLeftSon() == null) {
				ret = node;
			} else {
				ret = findFather((AABRINode) node.getLeftSon(), min, max);
			}

		} else if (node.getMax() < min) { // Cas où le noeud courant est "plus petit" que l'intervalle donné

			if (node.getRightSon() == null) {
				ret = node;
			} else {
				ret = findFather((AABRINode) node.getRightSon(), min, max);
			}
		} else if (node.getMin() == min && node.getMax() == max) {
			throw new IntervalleChevauchantException("L'intervalle existe déjà !");
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
	public AABRINode findTreeNodeFromValue(AABRINode node, final int value) throws IntervalleInexistantException {

		AABRINode ret = null;

		if (node == null || value == 0) {
			throw new RuntimeException("Le noeud et la valeur doivent être renseignés !");

		} else if (value < node.getMin()) {

			if (node.getLeftSon() == null) {
				throw new IntervalleInexistantException("Impossible de trouver le noeud correspondant !\n"
				        + "Valeur demandée : {" + value + "}\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNodeFromValue((AABRINode) node.getLeftSon(), value);

		} else if (value > node.getMax()) {

			if (node.getRightSon() == null) {
				throw new IntervalleInexistantException("Impossible de trouver le noeud correspondant !\n"
				        + "Valeur demandée : {" + value + "}\n"
				        + "Intervalle au mieux : [" + node.getMin() + "; " + node.getMax() + "]");
			}
			ret = findTreeNodeFromValue((AABRINode) node.getRightSon(), value);
		} else { // Si on est pas inférieur ni supérieur, c'est qu'on est dans le bon intervalle
			ret = node;
		}
		return ret;
	}

	/**
	 * @return the rootNode
	 */
	public AABRINode getRootNode() {
		return rootNode;
	}

	/**
	 * Supprime un ABRI selon l'intervalle spécifié
	 * 
	 * @param min - la valeur minimale de l'intervalle
	 * @param max - la valeur maximale de l'intervalle
	 * @return le noeud supprimé
	 * @throws IntervalleInexistantException lorsque l'intervalle demandé n'existe pas
	 */
	public Node delete(int min, int max) {

		// Retour
		AABRINode ret = null;

		AABRINode nodeToDelete = this.findTreeNode(this.rootNode, min, max);

		if (nodeToDelete == null) {
			throw new RuntimeException("Impossible de retrouver le noeud à supprimer !");
		}

		// Dans le cas où le noeud est une feuille
		if (nodeToDelete.getLeftSon() == null && nodeToDelete.getRightSon() == null) {

			// Si le noeud père existe
			if (nodeToDelete.getFather() != null) {

				// On regarde quel fils est à supprimer, sans se préoccuper de rebrancher le père puisque le noeud courant n'a pas de fils
				if (nodeToDelete.getFather().getRightSon() == nodeToDelete) {
					nodeToDelete.getFather().setRightSon(null);
					ret = nodeToDelete;
				} else {
					nodeToDelete.getFather().setLeftSon(null);
					ret = nodeToDelete;
				}

			} else { // Dans le cas où le noeud courant est la racine de l'arbre
				this.rootNode = null;
			}
		} else if (nodeToDelete.getLeftSon() == null || nodeToDelete.getRightSon() == null) { // Dans le cas où le noeud n'a qu'un seul fils

			// Si le noeud à supprimer a un noeud père, il va falloir re brancher père et fils
			if (nodeToDelete.getFather() != null) {

				if (nodeToDelete.getFather().getRightSon() == nodeToDelete) {

					// On rebranche le père sur l'unique fils du noeud à supprimer, et inversement
					if (nodeToDelete.getLeftSon() != null) {
						nodeToDelete.getFather().setRightSon(nodeToDelete.getLeftSon());
						nodeToDelete.setFather(nodeToDelete.getFather());
						ret = nodeToDelete;
					} else {
						nodeToDelete.getFather().setRightSon(nodeToDelete.getRightSon());
						nodeToDelete.getRightSon().setFather(nodeToDelete.getFather());
						ret = nodeToDelete;
					}
				} else if (nodeToDelete.getFather().getLeftSon() == nodeToDelete) {

					if (nodeToDelete.getLeftSon() != null) {
						nodeToDelete.getFather().setLeftSon(nodeToDelete.getLeftSon());
						nodeToDelete.getLeftSon().setFather(nodeToDelete.getFather());
						ret = nodeToDelete;
					} else {
						nodeToDelete.getFather().setLeftSon(nodeToDelete.getRightSon());
						nodeToDelete.getRightSon().setFather(nodeToDelete.getFather());
						ret = nodeToDelete;
					}
				}
			}
		} else { // Dans le cas où le noeud a deux fils

			// Noeud qui va stocker le noeud le plus grand dans le sag
			AABRINode replacement = this.max((AABRINode) nodeToDelete.getRightSon());

			// On supprime le noeud de l'arbre
			this.delete(replacement.getMin(), replacement.getMax());

			// On copie les informations du noeud à supprimer dans le noeud de retour
			ret = new AABRINode();

			ret.setMax(nodeToDelete.getMax());
			ret.setMin(nodeToDelete.getMin());
			ret.setRoot(nodeToDelete.getRoot());
			ret.setFather(nodeToDelete.getFather());
			ret.setLeftSon(nodeToDelete.getLeftSon());
			ret.setRightSon(nodeToDelete.getRightSon());

			// On intervertit les valeurs des deux noeuds
			nodeToDelete.setMin(replacement.getMin());
			nodeToDelete.setMax(replacement.getMax());
			nodeToDelete.setRoot(replacement.getRoot());
		}

		return ret;
	}

	/**
	 * Dans l'AABRI le noeud le plus grand est normalement celui qui se trouve tout en bas à droite de l'arbre. On cherche donc à atteindre le noeud
	 * qui se trouve au plus profond des fils de droite.
	 * 
	 * @param node - L'arbre dans lequel faire la recherche
	 * @return - Le noeud ayant le minimum (ou maximum) le plus élevé de l'AABRI
	 */
	private AABRINode max(AABRINode node) {

		AABRINode ret = null;

		if (node.getRightSon() != null) {
			ret = this.max((AABRINode) node.getRightSon());
		} else {
			ret = node;
		}

		return ret;
	}

	/**
	 * Méthode en charge d'ajouter un noeud simple dans l'AABRI. La méthode parcours l'AABRI à la recherche d'un intervalle qui contient la valeur à
	 * ajouter. Si l'intervalle n'existe pas et si la valeur existe déjà dans l'ABRI, la méthode ne fait rien. Une fois l'intervalle trouvée, la
	 * méthode fait appel à la fonction TreeNode.insert(SimpleNode).
	 * 
	 * @param node - Le noeud d'ABRI à ajouter
	 * @return TreeNode - le noeud d'AABRI dans lequel la valeur a été ajoutée
	 * @throws IntervalleInexistantException dans le cas où aucun intervalle ne contient la valeur à ajouter
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 */
	public SimpleNode addSimpleNode(SimpleNode node) throws IntervalleInexistantException, ValeurDejaPresenteException {

		AABRINode treeNode = this.findTreeNodeFromValue(this.rootNode, node.getValue());

		if (treeNode == null) {
			return null;
		}

		treeNode.insert(node);

		return node;
	}

	/**
	 * Ajoute un entier dans l'un des ABRI de l'AABRI en fonction des intervalles disponibles.
	 * 
	 * @param value - La valeur à ajouter
	 * @throws IntervalleInexistantException dans le cas où aucun intervalle ne contient la valeur à ajouter
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 */
	public SimpleNode addSimpleNode(int value) throws IntervalleInexistantException, ValeurDejaPresenteException {
		return this.addSimpleNode(new SimpleNode(value));
	}

	/**
	 * Supprime une noeud simple d'un ABRI. La méthode parcours l'AABRI à la recherche de l'intervalle contenant la valeur simpleNodeValue. Si
	 * l'intervalle n'existe pas, la méthode lève une exception IntervalleInexistantException. Si la valeur est introuvable, la méthode renvoi null.
	 * Une fois l'intervalle trouvé (et donc l'objet TreeNode), la méthode appelle la méthode TreeNode.delete(int simpleNodeValue).
	 * 
	 * Attention, lorsque le noeud ne contient plus aucune valeur, on supprime le noeud afin de ne pas garder de noeud vide.
	 * 
	 * @param simpleNodeValue - La valeur du noeud à supprimer
	 * @return le noeud qui a été retiré, ou null si aucun noeud n'a été trouvé.
	 * @throws IntervalleInexistantException Si la valeur n'appartient à aucun intervalle
	 * @throws ValeurNonRepresenteeDansABRI Si la valeur est bien dans l'intervalle de l'ABRI, mais n'est pas représentée
	 */
	public SimpleNode removeSimpleNode(final int simpleNodeValue) throws IntervalleInexistantException, ValeurNonRepresenteeDansABRI {

		// Penser à supprimer le TreeNode courant lorsqu'il ne contient plus aucun noeud simple (ie lorsque le root == null)
		AABRINode treeNode = this.findTreeNodeFromValue(this.rootNode, simpleNodeValue);

		SimpleNode removedNode = treeNode.delete(simpleNodeValue);

		// Si le noeud ABRI ne contient plus aucune valeur, on supprime le noeud de l'AABRI le contenant
		if (treeNode.getRoot() == null) {
			// TODO optimisation possible en créant une méthode de suppression à partir d'un
			// TreeNode
			this.delete(treeNode.getMin(), treeNode.getMax());
		}
		return removedNode;
	}

	/**
	 * Affiche dans un terminal une représentation telle que définie dans le README de l'abre courant. Le parcours de l'AABRI est préfixe (On va au
	 * bout des fils gauches, on imprime en remontant, puis on va chercher dans les fils droits).
	 * 
	 * @param node - le sous arbre dans lequel faire l'affichage.
	 * @return String - les infos sur le noeud courant
	 */
	public String getInfos(AABRINode node) {

		String infosNode, ret;
		ret = "";
		if (node != null) {
			infosNode = node.getMin() + ":" + node.getMax() + ";" + node.getInfos(node.getRoot()) + "\n";
			ret += infosNode;
			ret += this.getInfos((AABRINode) node.getLeftSon());
			ret += this.getInfos((AABRINode) node.getRightSon());
		}
		return ret;
	}

	/**
	 * Renvoie un String contenant toutes les valeurs de l'AABRI, et aucune autre :)
	 * 
	 * @param node - La racine de l'arbre dans lequel on souhaite relever les valeurs
	 * @return le tableau de valeurs
	 */
	public String getValues(AABRINode node) {

		String infosNode, ret;
		ret = "";

		if (node != null) {
			infosNode = node.getInfos(node.getRoot()) + "\n";
			ret += infosNode;
			ret += this.getValues((AABRINode) node.getLeftSon());
			ret += this.getValues((AABRINode) node.getRightSon());
		}
		return ret;
	}

	/**
	 * <pre>
	 * Vérifie si l'arbre courant est bien formé :
	 * - A est un ABR (sur les valeurs de m)
	 * - Tous les intervalles [m;M] des noeuds de A sont disjoincts
	 * - Tous les arbres A' des noeuds de A sont des ABRI contenant des éléments compris entre m et M
	 * </pre>
	 * 
	 * @return
	 * @throws IntervalleChevauchantException si des intervalles se chevauchent
	 * @throws AABRINodeMalPositionne si l'un des noeuds de l'AABRI est mal positionné
	 * @throws SimpleNodeMalPositionne si l'ABRI contenu par le noeud est mal formé
	 */
	public boolean isWellFormed() throws IntervalleChevauchantException, AABRINodeMalPositionne, SimpleNodeMalPositionne {
		return this.isABR(this.rootNode) && this.containsOnlyDisjointIntervals(this.rootNode) && ABRIWellFormed(this.rootNode);
	}

	/**
	 * Vérifie que l'ABRI contenu par le noeud courant est bien formé. Via le mécanisme des exceptions, on affiche le noeud en cause si l'ABRI contenu
	 * dans le noeud courant n'est pas bien formé.
	 * 
	 * <pre>
	 * fonction ABRIBienForme (NoeudAABRI noeud) : booleen
	 * booleen ret = true
	 * début
	 * 	si noeud non vide alors
	 * 		ret = noeud.estBienForme
	 * 		ret = ret ET ABRIBienForme (noeud.sag)
	 * 		ret = ret ET ABRIBienForme (noeud.sad)
	 * 	sinon
	 * 		ret = true
	 * 	finsi
	 * 	ABRIBienForme = ret
	 * fin
	 * </pre>
	 * 
	 * @param node - le noeud à vérifier
	 * @return true si l'ABRI contenu est correctement formé, false sinon.
	 * @throws SimpleNodeMalPositionne si le SimpleNode est mal positionné
	 */
	private boolean ABRIWellFormed(AABRINode node) throws SimpleNodeMalPositionne {

		boolean ret;
		ret = true;
		if (node != null) {
			ret = node.isWellFormed(node.getRoot());
			ret = ret && this.ABRIWellFormed((AABRINode) node.getLeftSon());
			ret = ret && this.ABRIWellFormed((AABRINode) node.getRightSon());
		}
		return ret;
	}

	/**
	 * Vérifie que l'arbre dont node est la racine est bien constitué d'intervalles disjoints
	 * 
	 * @param node - Racine de l'abre à vérifier
	 * @return true si tous les intervalles sont disjoints, false sinon
	 * @throws IntervalleChevauchantException dans le cas où des intervalles se chevauchent
	 */
	private boolean containsOnlyDisjointIntervals(AABRINode node) throws IntervalleChevauchantException {

		List<int[]> intervalles = new ArrayList<>();
		intervalles = this.getIntervalles(this.rootNode, intervalles);

		for (int i = 0; i < intervalles.size(); i++) {
			for (int j = i + 1; j < intervalles.size() - 1; j++) {
				if ((intervalles.get(i)[0] >= intervalles.get(j)[0] && intervalles.get(i)[0] <= intervalles.get(j)[1])
				        || (intervalles.get(i)[1] >= intervalles.get(j)[0] && intervalles.get(i)[1] <= intervalles.get(j)[1])) {
					throw new IntervalleChevauchantException("Intervalles chevauchants : [" + intervalles.get(i)[0] + ";" + intervalles.get(i)[1]
					        + " ] et [" + intervalles.get(j)[0] + "; " + intervalles.get(j)[1] + "]");
				}
			}
		}
		return true;
	}

	/**
	 * Récupère l'ensemble des intervalles des noeuds de l'AABRI
	 * 
	 * @param node - le noeud racine de l'ABRI
	 * @param intervalles - la liste dans laquelle ajouter les intervalles
	 * @return la liste des intervalles mise à jour
	 */
	public List<int[]> getIntervalles(AABRINode node, List<int[]> intervalles) {
		if (node != null) {
			int[] bornes = { node.getMin(), node.getMax() };
			intervalles.add(bornes);
			intervalles = this.getIntervalles((AABRINode) node.getLeftSon(), intervalles);
			intervalles = this.getIntervalles((AABRINode) node.getRightSon(), intervalles);
		}
		return intervalles;
	}

	/**
	 * Vérifie que la structure de l'arbre dont le noeud passé est la racine est correcte.
	 * 
	 * <pre>
	 * Tous les éléments contenus dans les noeuds du sag de node sont < à node sur node.getMin()
	 * --------------------------------------------- sad ------------ > ------------------------
	 * </pre>
	 * 
	 * @param node - Racine de l'abre à vérifier
	 * @return true si l'arbre est bien un ABR
	 * @throws AABRINodeMalPositionne si le noeud est mal positionné
	 */
	protected boolean isABR(AABRINode node) throws AABRINodeMalPositionne {

		// On retourne true dans le cas où le noeud est une feuille
		boolean ret = true;

		// Si node a un fils gauche correctement positionné on descend dedans
		if (node.getLeftSon() != null) {

			// Si le fils gauche est bien > au noeud courant, on descend dedans pour vérifier
			if (((AABRINode) node.getLeftSon()).getMin() < node.getMin()) {
				ret = isABR((AABRINode) node.getLeftSon());
			} else {
				throw new AABRINodeMalPositionne("Noeud mal positionné : [" + node.getMin() + "; " + node.getMax() + "] // fils gauche : ["
				        + ((AABRINode) node.getLeftSon()).getMin() + "; " + ((AABRINode) node.getLeftSon()).getMax() + "]");
			}
		}

		// Si node a un fils droit on descend dedans
		if (node.getRightSon() != null) {

			if (((AABRINode) node.getRightSon()).getMin() > node.getMin()) {
				ret = isABR((AABRINode) node.getRightSon());
			} else {
				throw new AABRINodeMalPositionne("Noeud mal positionné : [" + node.getMin() + "; " + node.getMax() + "] // fils gauche : ["
				        + ((AABRINode) node.getRightSon()).getMin() + "; " + ((AABRINode) node.getRightSon()).getMax() + "]");
			}
		}
		return ret;
	}

	/**
	 * Construit un ABR à partir de l'AABRI courant.
	 * 
	 * @return l'arbre binaire créé
	 * @throws ValeurDejaPresenteException Si la valeur à insérer est déjà présente dans l'arbre.
	 * @throws NumberFormatException si le format de l'une des valeurs n'est pas correct
	 */
	public AABRINode toABR() throws NumberFormatException, ValeurDejaPresenteException {

		// Retour de la méthode
		AABRINode ret = new AABRINode(TypeABR.ARBRE_BINAIRE_RECHERCHE);

		// Représentation de l'arbre
		String AABRI = this.getValues(this.rootNode);

		// Les sauts de ligne sont remplacés par les délimiteurs de valeurs
		AABRI = AABRI.replace("\n", ":");

		// Objet de parcours de la représentation de l'abre binaire
		StringTokenizer st = new StringTokenizer(AABRI, ":");

		// Variable de parcours
		String tmp;

		while (st.hasMoreTokens()) {
			tmp = st.nextToken();
			tmp = tmp.trim();
			ret.insert(Integer.parseInt(tmp));
		}

		return ret;
	}
}
