/**
 * 
 */
package ihm.controler;

import ihm.gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

/**
 * @author Cyril
 * 
 */
public class SaveItemListener implements ActionListener {

	/**
	 * Fenêtre dont on interprète les actions
	 */
	private MainFrame frame;

	/**
	 * Constructeur.
	 * 
	 * @param frame - La fenêtre possédant l'item de sauvegarde
	 */
	public SaveItemListener(MainFrame frame) {
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * @see http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser jfc;
		String homeDir;
		int userAnswer;

		if (e.getSource() == this.frame.getSaveItem()) {

			homeDir = System.getProperty("user.home");

			if (homeDir != null && !homeDir.equals("")) {
				jfc = new JFileChooser(homeDir);
			} else {
				jfc = new JFileChooser();
			}

			userAnswer = jfc.showDialog(this.frame, "Enregistrer");

			if (userAnswer == JFileChooser.APPROVE_OPTION) {
				this.frame.saveToFile(jfc.getSelectedFile().getAbsolutePath());
			}
		}
	}
}
