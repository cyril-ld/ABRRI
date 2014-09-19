/**
 *
 */
package org.miage.aabri.ihm.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.miage.aabri.ihm.gui.MainFrame;
import org.miage.aabri.ihm.gui.RandomDialog;

/**
 * @author Cyril
 *
 */
public class RandomAABRIButtonListener implements ActionListener {

    /**
     * Référence sur la frame principale
     */
    private final MainFrame frame;

    public RandomAABRIButtonListener(MainFrame mainFrame) {
        this.frame = mainFrame;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.frame.getRandomAABRIButton()) {
            RandomDialog dialog = new RandomDialog(frame);
            dialog.setVisible(true);
        }
    }
}
