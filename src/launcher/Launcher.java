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

		Splash test = new Splash(3000);
		MainFrame frame = new MainFrame("Tous aux AABRIs !");

		test.setVisible(false);
		frame.setVisible(true);
	}
}
