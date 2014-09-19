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
public class ToAABRIButtonListener implements ActionListener {

    private final MainFrame frame;

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
