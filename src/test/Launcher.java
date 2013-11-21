/**
 * 
 */
package test;

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

		AABRI = (args.length == 2) ? TreeUtils.initBinaryTreeFromFile(args[1]) : TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");
		// TODO : tester la suppression //
	}
}
