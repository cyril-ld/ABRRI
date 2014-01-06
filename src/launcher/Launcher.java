/**
 * 
 */
package launcher;

import util.TreeUtils;
import datastructure.AABRI;

/**
 * @author Cyril
 * 
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AABRI randomTree = TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");

		System.out.println(randomTree.getInfos(randomTree.getRootNode()));
		TreeUtils.saveToFile("C:\\Users\\Cyril\\AABRI.txt", randomTree);

		randomTree.getRootNode().toAABRI(2);
	}
}
