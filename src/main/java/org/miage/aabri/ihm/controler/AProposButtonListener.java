/**
 *
 */
package org.miage.aabri.ihm.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.miage.aabri.ihm.gui.MainFrame;

/**
 * @author Cyril
 *
 */
public class AProposButtonListener implements ActionListener {

    private final MainFrame frame;

    public AProposButtonListener(MainFrame frame) {
        this.frame = frame;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.frame.getaProposItem() == e.getSource()) {
            this.frame.showAProposFrame();
        }
    }

}
