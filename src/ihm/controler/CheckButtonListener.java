/**
 * 
 */
package ihm.controler;

import ihm.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
			if (this.frame.getAbr() != null || this.frame.getAabri() != null) {
				this.frame.checkArbre();
			} else {
				this.frame.showModal(this.frame, "Aucun arbre binaire n'existe pour le moment !", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
