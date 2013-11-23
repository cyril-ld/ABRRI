/**
 * 
 */
package datastructure;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.TreeUtils;
import exceptions.IntervalleInexistantException;

/**
 * @author Cyril
 * 
 */
public class BinaryTreeTest {

	/**
	 * Objet à tester
	 */
	private BinaryTree binaryTree;

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
	 * Test method for {@link datastructure.BinaryTree#insert(int, int, datastructure.SimpleNode)}.
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
	 * Test method for {@link datastructure.BinaryTree#insert(int, int, datastructure.SimpleNode)}.
	 * 
	 * Ajout d'un entier (noeud simple) dans l'AABRI.
	 */
	@Test
	public void testInsertSimpleNodeFromValueOK() throws IntervalleInexistantException {
		this.binaryTree.addSimpleNode(60);

		TreeNode node = this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 50, 75);
		SimpleNode simpleNode = node.findNode(node.getRoot(), 60);

		Assert.assertNotNull(simpleNode);
		Assert.assertTrue(60 == simpleNode.getValue());
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#insert(datastructure.TreeNode)}.
	 */
	@Test
	public void testInsertTreeNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#findTreeNode(datastructure.TreeNode, int, int)}.
	 */
	@Test
	public void testFindTreeNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#findTreeNodeFromValue(datastructure.TreeNode, int)}.
	 */
	@Test
	public void testFindTreeNodeFromValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#delete(int, int)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#addSimpleNode(datastructure.SimpleNode)}.
	 */
	@Test
	public void testAddSimpleNodeSimpleNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#addSimpleNode(int)}.
	 */
	@Test
	public void testAddSimpleNodeInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#removeSimpleNode(int)}.
	 */
	@Test
	public void testRemoveSimpleNode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#ABRtoAABRI()}.
	 */
	@Test
	public void testABRtoAABRI() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#AABRItoABR()}.
	 */
	@Test
	public void testAABRItoABR() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link datastructure.BinaryTree#getRootNode()}.
	 */
	@Test
	public void testGetRootNode() {
		fail("Not yet implemented");
	}

}
