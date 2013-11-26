/**
 * 
 */
package launcher;

import util.TreeUtils;
import datastructure.BinaryTree;

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
		// TreeNode node1, node2;

		AABRI = (args.length == 2) ? TreeUtils.initBinaryTreeFromFile(args[1]) : TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");

		AABRI.getInfos(AABRI.getRootNode());
	}
}
