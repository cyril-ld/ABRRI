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
 * @author Cyril
 * 
 */
public class InsertValueDialog extends JDialog implements ActionListener {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -1270304426323164159L;

	/**
	 * Bouton valider
	 */
	private JButton valider;

	/**
	 * Panels de la fenêtre
	 */
	private JPanel donnees, boutons;

	/**
	 * Titres des zones de texte
	 */
	private JLabel valueLabel;

	/**
	 * Zones de texte
	 */
	private JTextField fieldValue;

	/**
	 * Constructeur
	 * 
	 * @param owner - La fenêtre mère de la modale
	 */
	public InsertValueDialog(MainFrame owner) {
		super(owner, "Paramètres AABRI aléatoire", Dialog.ModalityType.APPLICATION_MODAL);
		this.initLayout();
		this.addActionListener();
	}

	private void initLayout() {
		// Options générales
		this.setLocationRelativeTo(null);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());

		donnees = new JPanel(new GridLayout(3, 1));

		valueLabel = new JLabel("Valeur à insérer (doit être supérieure à 0) :");
		fieldValue = new JTextField();

		donnees.add(valueLabel);
		donnees.add(fieldValue);

		boutons = new JPanel(new BorderLayout());

		this.valider = new JButton("Insérer");

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

			int valeur = Integer.parseInt(this.fieldValue.getText());

			if (valeur >= 0) {
				((MainFrame) this.getOwner()).insertValue(valeur);
				this.setVisible(false);
			} else {
				((MainFrame) this.getOwner()).showModal(this, "La valeur à insérer doit être supérieure ou égale à 0.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
