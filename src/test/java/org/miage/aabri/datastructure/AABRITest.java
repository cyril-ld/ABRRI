/**
 *
 */
package org.miage.aabri.datastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.miage.aabri.exceptions.AABRINodeMalPositionne;
import org.miage.aabri.exceptions.IntervalleChevauchantException;
import org.miage.aabri.exceptions.IntervalleInexistantException;
import org.miage.aabri.exceptions.SimpleNodeMalPositionne;
import org.miage.aabri.exceptions.ValeurDejaPresenteException;
import org.miage.aabri.exceptions.ValeurNonRepresenteeDansABRI;
import org.miage.aabri.interfaces.Node;
import org.miage.aabri.util.TreeUtils;

/**
 * Classe de test de l'AABRI. Utilise le fichier resources/AABRI.txt pour effectuer les tests.
 *
 * @author Cyril
 *
 */
public class AABRITest {

    /**
     * Objet à tester
     */
    private AABRI binaryTree;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.binaryTree = TreeUtils.initBinaryTreeFromFile("src/test/resources/AABRI.txt");
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
     * On part du principe que l'ajout d'une valeur n'est réalisable que si un noeud contient un intervalle incluant la
     * valeur.
     *
     * @throws IntervalleInexistantException attendu
     */
    @Test(expected = IntervalleInexistantException.class)
    public void testInsertSimpleNodeFromValue() throws IntervalleInexistantException {
        try {
            this.binaryTree.addSimpleNode(999999);
        } catch (ValeurDejaPresenteException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Ajout d'un entier (noeud simple) dans l'AABRI.
     *
     * @throws org.miage.aabri.exceptions.IntervalleInexistantException
     */
    @Test
    public void testInsertSimpleNodeFromValueOK() throws IntervalleInexistantException {
        try {
            this.binaryTree.addSimpleNode(60);
        } catch (ValeurDejaPresenteException e) {
            Assert.fail(e.getMessage());
        }

        AABRINode node = this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 50, 75);
        SimpleNode simpleNode = node.findFather(node.getRoot(), 60);

        Assert.assertNotNull(simpleNode);
        Assert.assertTrue("On doit retrouver la valeur insérée dans l'arbre.", 60 == simpleNode.getValue());
    }

    /**
     * On cherche à récupérer le noeud que l'on vient d'ajouter. Si on
     * récupère correctement le noeud c'est qu'il a été correctement inséré dans l'arbre.
     */
    @Test
    public void testInsertTreeNodeWithNoError() {
        AABRINode treeNode = new AABRINode(500, 550, new SimpleNode(530), TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE);
        try {
            this.binaryTree.insert(treeNode);
            Assert.assertEquals(treeNode, this.binaryTree.findTreeNode(this.binaryTree.getRootNode(), 500, 550));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    /**
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
     *
     * La méthode testée retourne a noeud d'AABRI. On teste donc ce noeud afin de savoir s'il correspond vraiment au
     * noeud que l'on recherche.
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
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    /**
     *
     * Supprime un noeud d'AABRI. Le test supprime donc un noeud de l'AABRI et essaie de le retrouver. Si on le trouve
     * dans l'AABRI c'est que la
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
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    /**
     */
    @Test
    public void testAddSimpleNodeSimpleNode() {

        // On réinitialise l'AABRI pour pouvoir travailler sur un seul noeud
        this.binaryTree = new AABRI();

        try {
            this.binaryTree.insert(0, 10, null, TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE);

            this.binaryTree.addSimpleNode(new SimpleNode(2));
            this.binaryTree.addSimpleNode(new SimpleNode(9));
            this.binaryTree.addSimpleNode(new SimpleNode(1));
        } catch (IntervalleChevauchantException | IntervalleInexistantException | ValeurDejaPresenteException e) {
            Assert.fail(e.getMessage());
        }

        AABRINode root = this.binaryTree.getRootNode();

        Assert.assertTrue("Le noeud racine doit être 2.", root.getRoot().getValue() == 2);
        Assert.assertTrue("Le fils gauche doit être supérieur à la racine.", ((SimpleNode) root.getRoot().getLeftSon()).getValue() == 9);
        Assert.assertTrue("Le fils droit droit être inférieur à la racine.", ((SimpleNode) root.getRoot().getRightSon()).getValue() == 1);
    }

    /**
     */
    @Test
    public void testAddSimpleNodeInt() {

        // On réinitialise l'AABRI pour pouvoir travailler sur un seul noeud
        this.binaryTree = new AABRI();

        try {
            this.binaryTree.insert(0, 10, null, TypeABR.ARBRE_BINAIRE_RECHERCHE_INVERSE);

            this.binaryTree.addSimpleNode(2);
            this.binaryTree.addSimpleNode(9);
            this.binaryTree.addSimpleNode(1);
        } catch (IntervalleChevauchantException | IntervalleInexistantException | ValeurDejaPresenteException e) {
            Assert.fail(e.getMessage());
        }

        AABRINode root = this.binaryTree.getRootNode();

        Assert.assertTrue("Le noeud racine doit être 2.", root.getRoot().getValue() == 2);
        Assert.assertTrue("Le fils gauche doit être supérieur à la racine.", ((SimpleNode) root.getRoot().getLeftSon()).getValue() == 9);
        Assert.assertTrue("Le fils droit droit être inférieur à la racine.", ((SimpleNode) root.getRoot().getRightSon()).getValue() == 1);

    }

    /**
     */
    @Test
    public void testRemoveSimpleNode() {
        try {
            this.binaryTree.removeSimpleNode(60);

            // Recherche de la valeur que l'on vient de supprimer
            AABRINode node = this.binaryTree.findTreeNodeFromValue(this.binaryTree.getRootNode(), 60);

            Assert.assertTrue("On ne doit pas retrouver la valeur d'un noeud que l'on vient de supprimer.",
                    node.findNode(node.getRoot(), 60) == null);
        } catch (IntervalleInexistantException | ValeurNonRepresenteeDansABRI e) {
            System.out.println(e.getMessage());
            Assert.fail("La suppression d'un valeur existante ne doit pas lever d'exception.");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            Assert.fail("Erreur non prévue.");
        }
    }

    /**
     */
    @Test
    public void testAABRItoABR() {
        try {
            AABRINode ABR = this.binaryTree.toABR();
            Assert.assertTrue("L'ABR doit être bien formé !", ABR.isWellFormed(ABR.getRoot()));
        } catch (NumberFormatException | ValeurDejaPresenteException | SimpleNodeMalPositionne e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
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
        try {
            Assert.assertTrue("L'arbre binaire est bien formé !", this.binaryTree.isABR(this.binaryTree.getRootNode()));
        } catch (AABRINodeMalPositionne e) {
            Assert.fail(e.getMessage());
        }
    }

    @SuppressWarnings("cast")
    @Test(expected = AABRINodeMalPositionne.class)
    public void testIsABR_notAnABR() throws AABRINodeMalPositionne {

        // Echange des deux fils du root pour faire planter le test directement
        AABRINode tmp = (AABRINode) this.binaryTree.getRootNode().getLeftSon();
        this.binaryTree.getRootNode().setLeftSon(((AABRINode) this.binaryTree.getRootNode().getRightSon()));
        this.binaryTree.getRootNode().setRightSon(tmp);

        this.binaryTree.isABR(this.binaryTree.getRootNode());
    }

    /**
     * Test permettant de voir si le programme se comporte bien lorsqu'on essaie d'insérer un noeud dont l'intervalle
     * chevauche un autre.
     *
     * Pour ce faire, on utilise l'arbre suivant :
     *
     * <pre>
     * 50:75;60:62:75:55
     * 40:55;40:51:53
     * 5:20;7:12:8:6:19
     * </pre>
     *
     * En sortie d'insertion on doit avoir un AABRI contenant deux noeuds : le premier et le dernier. Le second est
     * ignoré.
     */
    @Test
    public void testIsABR_intervallesChevauchant() {

        // Création d'un nouvel arbre depuis un fichier de test possédant des intervalles se chevauchant
        this.binaryTree = TreeUtils.initBinaryTreeFromFile("src/test/resources/AABRI_Intervalles_chevauchants.txt");

        // L'arbre binaire doit être correct (c'est l'insertion qui ne fait rien)
        // On ne doit pas avoir de sag
        boolean result = false;
        try {
            result = (this.binaryTree.getRootNode().getLeftSon() != null) && this.binaryTree.isABR(this.binaryTree.getRootNode())
                    && (this.binaryTree.getRootNode().getRightSon() == null);
        } catch (AABRINodeMalPositionne e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue("L'arbre binaire est bien formé !", result);
    }
}
