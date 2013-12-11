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

			AABRI = (args.length == 2) ? TreeUtils.initBinaryTreeFromFile(args[1]) : TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");

			System.out.println(AABRI.getInfos(AABRI.getRootNode()));
			System.out.println("============================\n");
			AABRI.addSimpleNode(101);
			System.out.println(AABRI.getInfos(AABRI.getRootNode()));
			System.out.println("============================\n");
			AABRI.delete(50, 75);
			System.out.println(AABRI.getInfos(AABRI.getRootNode()));

			TreeUtils.saveToFile("C:\\Users\\Cyril\\AABRI.txt", AABRI);
		} catch (IntervalleInexistantException e) {
			e.printStackTrace();
		}
	}
}
