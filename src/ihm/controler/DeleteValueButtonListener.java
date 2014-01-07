/**
 * 
 */
package ihm.controler;

import ihm.gui.DeleteValueDialog;
import ihm.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Cyril
 * 
 */
public class DeleteValueButtonListener implements ActionListener {

	/**
	 * Fenêtre maitresse de l'application
	 */
	private MainFrame frame;

	public DeleteValueButtonListener(MainFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.frame.getDeleteValueButton()) {
			if (this.frame.getAabri() != null || this.frame.getAbr() != null) {
				DeleteValueDialog dialog = new DeleteValueDialog(this.frame);
				dialog.setVisible(true);
			} else {
				this.frame.showModal(this.frame, "Aucun arbre binaire n'existe pour le moment !", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
