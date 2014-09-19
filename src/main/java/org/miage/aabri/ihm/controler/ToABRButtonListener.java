/**
 *
 */
package org.miage.aabri.ihm.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.miage.aabri.ihm.gui.MainFrame;

/**
 * @author Cyril
 *
 */
public class ToABRButtonListener implements ActionListener {

    private final MainFrame frame;

    public ToABRButtonListener(MainFrame frame) {
        this.frame = frame;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frame.getToABRButon()) {
            if (this.frame.getAabri() != null) {
                this.frame.toABR();
            } else {
                this.frame.showModal(this.frame, "Aucun AABRI n'existe pour le moment !", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
