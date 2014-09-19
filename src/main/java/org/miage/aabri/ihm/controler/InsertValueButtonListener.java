/**
 *
 */
package org.miage.aabri.ihm.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.miage.aabri.ihm.gui.InsertValueDialog;
import org.miage.aabri.ihm.gui.MainFrame;

/**
 * @author Cyril
 *
 */
public class InsertValueButtonListener implements ActionListener {

    /**
     * Fenêtre maitresse de l'application
     */
    private final MainFrame frame;

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
