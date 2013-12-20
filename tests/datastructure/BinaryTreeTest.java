/**
 * 
 */
package datastructure;

import static org.junit.Assert.fail;
import interfaces.Node;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TreeUtils;
import exceptions.IntervalleInexistantException;
import exceptions.ValeurNonRepresenteeDansABRI;

/**
 * Classe de test de l'AABRI. Utilise le fichier resources/AABRI.txt pour effectuer les tests.
 * 
 * @author Cyril
 * 
 */
public class BinaryTreeTest {

	/**
	 * Objet à tester
	 */
	private AABRI binaryTree;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.binaryTree = TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");
		if (this.binaryTree == null) {
			Assert.fail("L'initialisation de l'arbre binaire a échoué !");
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.binaryTree = null;
	}

	/**
	 * Test method for {@link datastructure.AABRI#insert(int, int, datastructure.SimpleNode)}.
	 * 
	 * On part du principe que l'ajout d'une valeur n'est réalisable que si un noeud contient un intervalle incluant la valeur.
	 * 
	 * @throws IntervalleInexistantException attendu
	 */
	@Test(expected = IntervalleInexistantException.class)
	public void testInsertSimpleNodeFromValue() throws IntervalleInexistantException {
		this.binaryTree.addSimpleNode(999999);
	}

	/**
	 * Test method for {@link datastructure.AABRI#insert(int, int, datastructure.SimpleNode)}.
	 * 
	 * Ajout d'un entier (noeud simple) dans l'AABRI.
	 */
	@Test
	public void testInsertSimpleNodeFromValueOK() throws IntervalleInexistantException {
		this.binaryTree.addSimpleNode(60);

		AABRINode node = this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 50, 75);
		SimpleNode simpleNode = node.findNode(node.getRoot(), 60);

		Assert.assertNotNull(simpleNode);
		Assert.assertTrue("On doit retrouver la valeur insérée dans l'arbre.", 60 == simpleNode.getValue());
	}

	/**
	 * Test method for {@link datastructure.AABRI#insert(datastructure.AABRINode)}. On cherche à récupérer le noeud que l'on vient d'ajouter. Si
	 * on récupère correctement le noeud c'est qu'il a été correctement inséré dans l'arbre.
	 */
	@Test
	public void testInsertTreeNodeWithNoError() {
		AABRINode treeNode = new AABRINode(500, 550, new SimpleNode(530));
		try {
			this.binaryTree.insert(treeNode);
			Assert.assertEquals(treeNode, this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 500, 550));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test method for {@link datastructure.AABRI#findTreeNode(datastructure.AABRINode, int, int)}.
	 * 
	 * On essaie de récupérer un noeud de l'AABRI et on vérifie ses différentes valeurs.
	 */
	@Test
	public void testFindTreeNode() {
		AABRINode treeNode = this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 92, 110);

		Assert.assertTrue("Le noeud doit être retrouvé.", treeNode != null);
		Assert.assertTrue("Le maximum doit être égal au maximum donné dans le fichier source.", treeNode.getMax() == 110);
		Assert.assertTrue("Le minimum doit être égal au minimum donné dans le fichier source.", treeNode.getMin() == 92);
		Assert.assertTrue("La valeur du noeud racine doit être égale à la première valeur donnée pour ce noeud dans le fichier sources.",
		        (treeNode.getRoot().getValue() == 100));
	}

	/**
	 * Test method for {@link datastructure.AABRI#findTreeNodeFromValue(datastructure.AABRINode, int)}.
	 * 
	 * La méthode testée retourne a noeud d'AABRI. On teste donc ce noeud afin de savoir s'il correspond vraiment au noeud que l'on recherche.
	 */
	@Test
	public void testFindTreeNodeFromValue() {
		try {
			AABRINode treeNode = this.binaryTree.findTreeNodeFromValue(this.binaryTree.getRootNode(), 32);
			Assert.assertTrue("Le minimum doit être égal au minimum donné dans le fichier source.", treeNode.getMin() == 24);
			Assert.assertTrue("Le maximum doit être égal au maximum donné dans le fichier source.", treeNode.getMax() == 48);
			Assert.assertTrue("La valeur du noeud racine doit être égale à la première valeur donnée pour ce noeud dans le fichier sources.",
			        treeNode.getRoot().getValue() == 30);
		} catch (IntervalleInexistantException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test method for {@link datastructure.AABRI#delete(int, int)}.
	 * 
	 * Supprime un noeud d'AABRI. Le test supprime donc un noeud de l'AABRI et essaie de le retrouver. Si on le trouve dans l'AABRI c'est que la
	 * méthode de suppression a échoué.
	 */
	@Test
	public void testDelete() {
		try {
			Node treeNode = this.binaryTree.delete(24, 48);
			Assert.assertTrue("On doit récupérer un noeud lors de la suppression.", treeNode != null);
			Assert.assertTrue("Le noeud recherché ne doit pas exister.",
			        this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 24, 48) == null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test method for {@link datastructure.AABRI#addSimpleNode(datastructure.SimpleNode)}.
	 */
	@Test
	public void testAddSimpleNodeSimpleNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.AABRI#addSimpleNode(int)}.
	 */
	@Test
	public void testAddSimpleNodeInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.AABRI#removeSimpleNode(int)}.
	 */
	@Test
	public void testRemoveSimpleNode() {
		try {
			this.binaryTree.removeSimpleNode(60);

			// Recherche de la valeur que l'on vient de supprimer
			AABRINode node = this.binaryTree.findTreeNodeFromValue(this.binaryTree.getRootNode(), 60);

			Assert.assertTrue("On ne doit pas retrouver la valeur d'un noeud que l'on vient de supprimer.", node.findNode(node.getRoot(), 60) == null);
		} catch (IntervalleInexistantException | ValeurNonRepresenteeDansABRI e) {
			e.printStackTrace();
			fail("La suppression d'un valeur existante ne doit pas lever d'exception.");
		} catch (NullPointerException e) {
			e.printStackTrace();
			fail("Erreur non prévue.");
		}
	}

	/**
	 * Test method for {@link datastructure.AABRI#ABRtoAABRI()}.
	 */
	@Test
	public void testABRtoAABRI() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.AABRI#AABRItoABR()}.
	 */
	@Test
	public void testAABRItoABR() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.AABRI#getRootNode()}.
	 */
	@Test
	public void testGetRootNode() {
		Assert.assertTrue("Le minimum du noeud racine doit être 50.", this.binaryTree.getRootNode().getMin() == 50);
		Assert.assertTrue("Le maximum du noeud racine doit être 75.", this.binaryTree.getRootNode().getMax() == 75);
	}

	/**
	 * Vérifie que l'arbre binaire est bien formé
	 */
	@Test
	public void testIsABR() {
		Assert.assertTrue("L'arbre binaire est bien formé !", this.binaryTree.isABR(this.binaryTree.getRootNode()));
	}
}
