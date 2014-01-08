/**
 * 
 */
package ihm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @author Cyril
 * 
 */
public class AProposDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058261295896103986L;

	@SuppressWarnings("unused")
	private MainFrame frame;

	private JPanel panelTexteHaut, panelImage, panelTexteBas;

	private JLabel labelTexteHaut, labelTexteBasUn, labelTexteBasDeux;

	public AProposDialog(MainFrame frame) {

		super(frame, "A propos", Dialog.ModalityType.APPLICATION_MODAL);

		this.frame = frame;

		this.constructPanels();

		this.setSize(530, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Construit les différents panels de la fenêtre
	 */
	@SuppressWarnings("static-access")
	private void constructPanels() {

		this.setLayout(new BorderLayout());

		labelTexteHaut = new JLabel("Tous aux AABRIs !");
		labelTexteHaut.setFont(new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(), Font.PLAIN, 25));
		labelTexteHaut.setHorizontalAlignment(JLabel.CENTER);

		labelTexteBasUn = new JLabel("Projet de compléments en algorithme, réalisé dans le cadre du master MIAGE 1 de Nantes.");
		labelTexteBasDeux = new JLabel("Sébastien Heuzé et Cyril Le Driant");

		labelTexteBasDeux.setHorizontalAlignment(JLabel.CENTER);
		labelTexteBasUn.setHorizontalAlignment(JLabel.CENTER);

		// Ajout des différents composants dans les panels
		this.panelImage = new JPanel(new BorderLayout());
		this.panelImage.add(new SplashPicture("/resources/splash.png"));

		this.panelTexteBas = new JPanel(new BorderLayout());
		this.panelTexteHaut = new JPanel(new BorderLayout());
		this.panelTexteHaut.add(labelTexteHaut);
		this.panelTexteBas.add(labelTexteBasUn, BorderLayout.NORTH);
		this.panelTexteBas.add(labelTexteBasDeux, BorderLayout.CENTER);
		this.panelTexteBas.setBackground(Color.LIGHT_GRAY);

		this.add(panelTexteHaut, BorderLayout.NORTH);
		this.add(panelImage, BorderLayout.CENTER);
		this.add(panelTexteBas, BorderLayout.SOUTH);

	}
}
