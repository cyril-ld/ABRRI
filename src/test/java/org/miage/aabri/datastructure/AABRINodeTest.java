/**
 *
 */
package org.miage.aabri.datastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.miage.aabri.exceptions.SimpleNodeMalPositionne;
import org.miage.aabri.util.TreeUtils;

/**
 * @author Cyril
 *
 */
public class AABRINodeTest {

    /**
     * Le noeud à tester
     */
    private AABRINode nodeToTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Récupération d'un noeud d'AABRI généré aléatoirement
        nodeToTest = TreeUtils.randomAABRI(5, 15).getRootNode();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Partant du principe que la méthode d'insertion fait bien son boulot, on échange le fils gauche avec le fils
     * droit. On attend que la méthode
     * isWellFormed nous retourne une exception puisque l'arbre n'est plus bien formé.
     *
     * @throws org.miage.aabri.exceptions.SimpleNodeMalPositionne
     */
    @Test(expected = SimpleNodeMalPositionne.class)
    public void testIsWellFormedNotWellFormed() throws SimpleNodeMalPositionne {

        while (this.nodeToTest.getRoot() == null || this.nodeToTest.getRoot().getLeftSon() == null || this.nodeToTest.getRoot().getRightSon() == null) {
            this.nodeToTest = TreeUtils.randomAABRI(5, 15).getRootNode();
        }

        // Partant du principe que l'insertion est correcte, on va échanger le fils gauche et le fils droit du noeud.
        SimpleNode tmp = (SimpleNode) this.nodeToTest.getRoot().getLeftSon();
        this.nodeToTest.getRoot().setLeftSon(this.nodeToTest.getRoot().getRightSon());
        this.nodeToTest.getRoot().setRightSon(tmp);

        Assert.assertEquals(this.nodeToTest.isWellFormed(this.nodeToTest.getRoot()), false);
    }

    /**
     * Ici la méthode doit renvoyer true (partant du principe que la méthode d'insertion fait correctement son boulot).
     */
    @Test
    public void testIsWellFormedOK() {
        try {
            Assert.assertEquals(this.nodeToTest.isWellFormed(this.nodeToTest.getRoot()), true);
        } catch (SimpleNodeMalPositionne e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}
