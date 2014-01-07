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
public class ToAABRIButtonListener implements ActionListener {

	private MainFrame frame;

	public ToAABRIButtonListener(MainFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.frame.getToAABRIButton()) {
			if (this.frame.getAbr() != null) {
				this.frame.toAABRI();
			} else {
				this.frame.showModal(this.frame, "Aucun ABR n'existe pour le moment !", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
