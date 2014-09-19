/**
 *
 */
package org.miage.aabri.launcher;

import org.miage.aabri.ihm.gui.MainFrame;
import org.miage.aabri.ihm.gui.Splash;

/**
 * @author Cyril
 *
 */
public class Launcher {

    /**
     * @param args
     */
    public static void main(String[] args) {

        final Splash test = new Splash(3000);
        final MainFrame frame = new MainFrame("Tous aux AABRIs !");

        test.setVisible(false);
        frame.setVisible(true);
    }
}
