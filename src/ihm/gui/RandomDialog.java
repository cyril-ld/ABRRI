/**
 * 
 */
package ihm.gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fenêtre modale permettant de récupérer les informations relatives à la génération d'un AABRI.
 * 
 * @author Cyril
 * 
 */
public class RandomDialog extends JDialog implements ActionListener {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -957202734821952021L;

	/**
	 * Bouton de validation de la fenêtre
	 */
	private JButton valider;

	/**
	 * Panels de la fenêtre
	 */
	private JPanel donnees, boutons;

	/**
	 * Titres des zones de texte
	 */
	private JLabel nbreNoeuds, valeurMax;

	/**
	 * Zones de texte
	 */
	private JTextField fieldNoeuds, fieldValeurMax;

	/**
	 * Constructeur
	 * 
	 * @param owner - La fenêtre mère de la modale
	 */
	public RandomDialog(MainFrame owner) {
		super(owner, "Paramètres AABRI aléatoire", Dialog.ModalityType.APPLICATION_MODAL);
		this.initLayout();
		this.addActionListener();
	}

	/**
	 * Initialise le contenu de la fenêtre
	 */
	private void initLayout() {

		// Options générales
		this.setLocationRelativeTo(null);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());

		donnees = new JPanel(new GridLayout(6, 1));

		nbreNoeuds = new JLabel("Nombre de noeuds dans l'arbre généré :");
		fieldNoeuds = new JTextField();

		valeurMax = new JLabel("Valeur maximale de l'arbre (doit être >= 2 * NbreNoeuds) :");
		fieldValeurMax = new JTextField();

		donnees.add(nbreNoeuds);
		donnees.add(fieldNoeuds);
		donnees.add(new JLabel());
		donnees.add(valeurMax);
		donnees.add(fieldValeurMax);
		donnees.add(new JLabel());

		boutons = new JPanel(new BorderLayout());

		this.valider = new JButton("Créer");

		boutons.add(valider, BorderLayout.CENTER);

		this.add(donnees, BorderLayout.CENTER);
		this.add(boutons, BorderLayout.SOUTH);
	}

	private void addActionListener() {
		this.valider.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.valider) {

			int nbreNoeuds = Integer.parseInt(this.fieldNoeuds.getText());
			int max = Integer.parseInt(this.fieldValeurMax.getText());

			if (max >= 2 * nbreNoeuds) {
				((MainFrame) this.getOwner()).generateRandomAABRI(nbreNoeuds, max);
				this.setVisible(false);
			} else {
				((MainFrame) this.getOwner()).showModal(this, "valeur max doit être >= 2 * nbreNoeuds", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
