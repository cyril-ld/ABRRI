/**
 * 
 */
package test;

import util.TreeUtils;
import datastructure.BinaryTree;
import datastructure.TreeNode;
import exceptions.IntervalleInexistantException;

/**
 * @author Cyril
 * 
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinaryTree AABRI;
		TreeNode node1, node2;

		AABRI = (args.length == 2) ? TreeUtils.initBinaryTreeFromFile(args[1]) : TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");
		// TODO : tester la suppression //
		try {
			node1 = AABRI.findTreeNode(AABRI.getRootNode(), 50, 75);
			System.out.println(node1.getMin());
			node2 = AABRI.findTreeNode(AABRI.getRootNode(), 1, 2);
			System.out.println(node2.getRoot());
		} catch (IntervalleInexistantException e) {
			e.printStackTrace();
		}
	}
}
