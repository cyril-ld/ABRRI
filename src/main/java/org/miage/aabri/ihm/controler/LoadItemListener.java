/**
 *
 */
package org.miage.aabri.ihm.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import org.miage.aabri.ihm.gui.MainFrame;

/**
 * @author Cyril
 *
 */
public class LoadItemListener implements ActionListener {

    /**
     * Fenêtre dont on interprète les actions
     */
    private final MainFrame frame;

    /**
     * Constructeur.
     *
     * @param frame
     */
    public LoadItemListener(MainFrame frame) {
        this.frame = frame;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jfc;
        String homeDir;
        int userAnswer;

        if (e.getSource() == this.frame.getLoadItem()) {
            homeDir = System.getProperty("user.home");

            if (homeDir != null && !homeDir.equals("")) {
                jfc = new JFileChooser(homeDir);
            } else {
                jfc = new JFileChooser();
            }

            userAnswer = jfc.showDialog(this.frame, "Charger");

            if (userAnswer == JFileChooser.APPROVE_OPTION) {
                this.frame.loadFromFile(jfc.getSelectedFile().getAbsolutePath());
            }
        }
    }

}
