/**
 * 
 */
package ihm.controler;

import ihm.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cyril
 * 
 */
public class CheckButtonListener implements ActionListener {

	private MainFrame frame;

	public CheckButtonListener(MainFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.frame.getCheckButton()) {
			this.frame.checkABR();
		}
	}

}
