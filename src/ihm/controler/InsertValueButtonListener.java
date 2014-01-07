/**
 * 
 */
package ihm.controler;

import ihm.gui.InsertValueDialog;
import ihm.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Cyril
 * 
 */
public class InsertValueButtonListener implements ActionListener {

	/**
	 * Fenêtre maitresse de l'application
	 */
	private MainFrame frame;

	public InsertValueButtonListener(MainFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.frame.getInsertValueButton()) {
			if (this.frame.getAabri() != null || this.frame.getAbr() != null) {
				InsertValueDialog dialog = new InsertValueDialog(this.frame);
				dialog.setVisible(true);
			} else {
				this.frame.showModal(this.frame, "Aucun arbre binaire n'existe pour le moment !", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
