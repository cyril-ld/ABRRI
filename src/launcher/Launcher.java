/**
 * 
 */
package launcher;

import ihm.gui.MainFrame;
import ihm.gui.Splash;

/**
 * @author Cyril
 * 
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// AABRI randomTree = TreeUtils.initBinaryTreeFromFile("resources/AABRI.txt");

		// AABRI randomTree = TreeUtils.randomAABRI(3, 20);
		//
		// System.out.println(randomTree.getInfos(randomTree.getRootNode()));
		// TreeUtils.saveToFile("C:\\Users\\Cyril\\AABRI.txt", randomTree);

		// AABRI generate = randomTree.getRootNode().toAABRI(1);

		// System.out.println(generate.getInfos(generate.getRootNode()));

		// System.out.println(randomTree.getValues(randomTree.getRootNode()));

		// AABRINode abr = randomTree.toABR();
		// System.out.println(abr.getInfos(abr.getRoot()));
		@SuppressWarnings("unused")
		Splash test = new Splash(3000);

		MainFrame frame = new MainFrame("Tous aux AABRIs !");
		frame.setVisible(true);
	}
}
