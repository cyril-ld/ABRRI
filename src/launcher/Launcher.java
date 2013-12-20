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

		BinaryTree randomTree = TreeUtils.randomAABRI(5, 100);
		System.out.println(randomTree.getInfos(randomTree.getRootNode()));
		TreeUtils.saveToFile("C:\\Users\\Cyril\\AABRI.txt", randomTree);

		System.out.println(randomTree.isWellFormed(randomTree.getRootNode()));
	}
}
