/**
 * 
 */
package launcher;

import util.TreeUtils;
import datastructure.BinaryTree;
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

		try {
			BinaryTree AABRI;
			// TreeNode node1, node2;

			AABRI = (args.length == 2) ? TreeUtils.initBinaryTreeFromFile(args[1]) : TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");

			AABRI.getInfos(AABRI.getRootNode());
			System.out.println("============================");
			AABRI.addSimpleNode(101);
			AABRI.delete(50, 75);
			AABRI.getInfos(AABRI.getRootNode());
		} catch (IntervalleInexistantException e) {
			e.printStackTrace();
		}
	}
}
