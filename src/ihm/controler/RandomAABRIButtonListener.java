/**
 * 
 */
package ihm.controler;

import ihm.gui.MainFrame;
import ihm.gui.RandomDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cyril
 * 
 */
public class RandomAABRIButtonListener implements ActionListener {

	/**
	 * Référence sur la frame principale
	 */
	private MainFrame frame;

	public RandomAABRIButtonListener(MainFrame mainFrame) {
		this.frame = mainFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.frame.getRandomAABRIButton()) {
			RandomDialog dialog = new RandomDialog(frame);
			dialog.setVisible(true);
		}
	}
}
