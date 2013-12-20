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

		AABRI randomTree = TreeUtils.randomAABRI(5, 100);
		System.out.println(randomTree.getInfos(randomTree.getRootNode()));
		TreeUtils.saveToFile("C:\\Users\\Cyril\\AABRI.txt", randomTree);

		System.out.println(randomTree.isWellFormed(randomTree.getRootNode()));
	}
}
