/**
 * 
 */
package ihm.gui;

import ihm.controler.CheckButtonListener;
import ihm.controler.DeleteValueButtonListener;
import ihm.controler.InsertValueButtonListener;
import ihm.controler.LoadItemListener;
import ihm.controler.RandomAABRIButtonListener;
import ihm.controler.SaveItemListener;
import ihm.controler.ToAABRIButtonListener;
import ihm.controler.ToABRButtonListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.TreeUtils;
import datastructure.AABRI;
import datastructure.AABRINode;
import exceptions.IntervalleInexistantException;
import exceptions.SimpleNodeMalPositionne;
import exceptions.ValeurNonRepresenteeDansABRI;

/**
 * 
 * Fenêtre principale de l'application
 * 
 * @author Cyril
 * 
 */
public class MainFrame extends JFrame {

	/**
	 * auto-generated
	 */
	private static final long serialVersionUID = 9181994231971204472L;

	/**
	 * AABRI affiché dans la fenêtre
	 */
	private AABRI aabri;

	/**
	 * ABR obtenu par conversion de l'AABRI
	 */
	private AABRINode abr;

	/**
	 * Menu de la fenêtre
	 */
	private JMenu menu;

	/**
	 * A propos
	 */
	private JMenu autresMenu;

	/**
	 * Barre de menus
	 */
	private JMenuBar menuBar;

	/**
	 * Sous menu de chargement
	 */
	private JMenuItem loadItem;

	/**
	 * Sous menu d'enregistrement
	 */
	private JMenuItem saveItem;

	/**
	 * Sous menu à propos
	 */
	private JMenuItem aProposItem;

	/**
	 * Panel contenant les différents boutons d'action
	 */
	private JPanel actionsPanel;

	/**
	 * Panel d'affichage de l'arbre
	 */
	private JPanel displayPanel;

	/**
	 * Boutton de création d'arbre aléatoire
	 */
	private JButton randomAABRIButton;

	/**
	 * Bouton de vérification de l'arbre
	 */
	private JButton checkButton;

	/**
	 * Bouton d'insertion de valeur
	 */
	private JButton insertValueButton;

	/**
	 * Bouton de suppression d'une valeur
	 */
	private JButton deleteValueButton;

	/**
	 * Bouton de conversion d'un AABRI vers un ABR
	 */
	private JButton toABRButon;

	/**
	 * Bouton de conversion d'un ABR vers un AABRI
	 */
	private JButton toAABRIButton;

	/**
	 * Zone d'affichage de l'AABRI
	 */
	private JTextArea aeraAABRI;

	/**
	 * Construction de la fenêtre
	 * 
	 * @param title - Le titre à donner à la fenêtre
	 */
	public MainFrame(String title) {

		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(650, 600);
		this.setLocationRelativeTo(null);

		this.createMenu();
		this.createPanels();
		this.addActionListeners();
	}

	/**
	 * Création du menu de la fenêtre
	 */
	private void createMenu() {

		this.menu = new JMenu("Fichier");
		this.autresMenu = new JMenu("Autres");

		this.loadItem = new JMenuItem("Charger...");
		this.saveItem = new JMenuItem("Sauvegarder...");
		this.aProposItem = new JMenuItem("A propos...");

		this.menu.add(this.loadItem);
		this.menu.add(this.saveItem);

		this.autresMenu.add(this.aProposItem);

		this.menuBar = new JMenuBar();
		this.menuBar.add(this.menu);
		this.menuBar.add(this.autresMenu);

		this.setJMenuBar(this.menuBar);
	}

	/**
	 * Méthode de création et d'agancement des panels
	 */
	private void createPanels() {

		this.setLayout(new BorderLayout());
		this.actionsPanel = new JPanel(new GridLayout(2, 4));
		this.displayPanel = new JPanel(new GridLayout());

		this.randomAABRIButton = new JButton("Générer un arbre");
		this.checkButton = new JButton("Vérifier l'arbre");
		this.insertValueButton = new JButton("Insérer une valeur");
		this.deleteValueButton = new JButton("Supprimer une valeur");
		this.toABRButon = new JButton("Convertir en ABR");
		this.toAABRIButton = new JButton("Convertir en AABRI");
		this.toAABRIButton.setEnabled(false);

		this.aeraAABRI = new JTextArea("Rien à afficher pour l'instant...");
		this.aeraAABRI.setEditable(false);

		this.actionsPanel.add(this.checkButton);
		this.actionsPanel.add(this.randomAABRIButton);
		this.actionsPanel.add(this.insertValueButton);
		this.actionsPanel.add(this.deleteValueButton);
		this.actionsPanel.add(this.toABRButon);
		this.actionsPanel.add(this.toAABRIButton);

		JScrollPane scrollPane = new JScrollPane(this.displayPanel);
		this.displayPanel.add(this.aeraAABRI);

		this.add(scrollPane);
		this.add(this.actionsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Ajoute les listeners sur les différents éléments graphiques
	 */
	private void addActionListeners() {
		this.randomAABRIButton.addActionListener(new RandomAABRIButtonListener(this));
		this.saveItem.addActionListener(new SaveItemListener(this));
		this.loadItem.addActionListener(new LoadItemListener(this));
		this.insertValueButton.addActionListener(new InsertValueButtonListener(this));
		this.deleteValueButton.addActionListener(new DeleteValueButtonListener(this));
		this.checkButton.addActionListener(new CheckButtonListener(this));
		this.toAABRIButton.addActionListener(new ToAABRIButtonListener(this));
		this.toABRButon.addActionListener(new ToABRButtonListener(this));
	}

	/**
	 * @return the menu
	 */
	public JMenu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	/**
	 * @param menuBar the menuBar to set
	 */
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * @return the loadItem
	 */
	public JMenuItem getLoadItem() {
		return loadItem;
	}

	/**
	 * @param loadItem the loadItem to set
	 */
	public void setLoadItem(JMenuItem loadItem) {
		this.loadItem = loadItem;
	}

	/**
	 * @return the saveItem
	 */
	public JMenuItem getSaveItem() {
		return saveItem;
	}

	/**
	 * @param saveItem the saveItem to set
	 */
	public void setSaveItem(JMenuItem saveItem) {
		this.saveItem = saveItem;
	}

	/**
	 * @return the actionsPanel
	 */
	public JPanel getActionsPanel() {
		return actionsPanel;
	}

	/**
	 * @param actionsPanel the actionsPanel to set
	 */
	public void setActionsPanel(JPanel actionsPanel) {
		this.actionsPanel = actionsPanel;
	}

	/**
	 * @return the displayPanel
	 */
	public JPanel getDisplayPanel() {
		return displayPanel;
	}

	/**
	 * @param displayPanel the displayPanel to set
	 */
	public void setDisplayPanel(JPanel displayPanel) {
		this.displayPanel = displayPanel;
	}

	/**
	 * @return the randomAABRI
	 */
	public JButton getRandomAABRI() {
		return randomAABRIButton;
	}

	/**
	 * @param randomAABRI the randomAABRI to set
	 */
	public void setRandomAABRI(JButton randomAABRI) {
		this.randomAABRIButton = randomAABRI;
	}

	/**
	 * @return the aabri
	 */
	public AABRI getAabri() {
		return aabri;
	}

	/**
	 * @param aabri the aabri to set
	 */
	public void setAabri(AABRI aabri) {
		this.aabri = aabri;
	}

	/**
	 * Génère un AABRI aléatoire.
	 * 
	 * @param nbreNoeuds - Le nombre de noeuds
	 * @param valeurMaxABRI - La valeur maximale de l'AABRI
	 */
	public void generateRandomAABRI(int nbreNoeuds, int valeurMaxABRI) {
		this.aabri = TreeUtils.randomAABRI(nbreNoeuds, valeurMaxABRI);
		this.abr = null;
		this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
		this.toAABRIButton.setEnabled(false);
		this.toABRButon.setEnabled(true);
		this.repaint();
	}

	/**
	 * @return the randomAABRIButton
	 */
	public JButton getRandomAABRIButton() {
		return randomAABRIButton;
	}

	/**
	 * @param randomAABRIButton the randomAABRIButton to set
	 */
	public void setRandomAABRIButton(JButton randomAABRIButton) {
		this.randomAABRIButton = randomAABRIButton;
	}

	/**
	 * @return the checkButton
	 */
	public JButton getCheckButton() {
		return checkButton;
	}

	/**
	 * @param checkButton the checkButton to set
	 */
	public void setCheckButton(JButton checkButton) {
		this.checkButton = checkButton;
	}

	/**
	 * @return the insertValueButton
	 */
	public JButton getInsertValueButton() {
		return insertValueButton;
	}

	/**
	 * @param insertValueButton the insertValueButton to set
	 */
	public void setInsertValueButton(JButton insertValueButton) {
		this.insertValueButton = insertValueButton;
	}

	/**
	 * @return the deleteValueButton
	 */
	public JButton getDeleteValueButton() {
		return deleteValueButton;
	}

	/**
	 * @param deleteValueButton the deleteValueButton to set
	 */
	public void setDeleteValueButton(JButton deleteValueButton) {
		this.deleteValueButton = deleteValueButton;
	}

	/**
	 * @return the toABRButon
	 */
	public JButton getToABRButon() {
		return toABRButon;
	}

	/**
	 * @param toABRButon the toABRButon to set
	 */
	public void setToABRButon(JButton toABRButon) {
		this.toABRButon = toABRButon;
	}

	/**
	 * @return the toAABRIButton
	 */
	public JButton getToAABRIButton() {
		return toAABRIButton;
	}

	/**
	 * @param toAABRIButton the toAABRIButton to set
	 */
	public void setToAABRIButton(JButton toAABRIButton) {
		this.toAABRIButton = toAABRIButton;
	}

	/**
	 * Enregistre l'arbre binaire dans le fichier spécifié.
	 * 
	 * @param pathToFile - le chemin complet vers le fichier, nom du fichier inclus
	 */
	public void saveToFile(String pathToFile) {
		if (this.aabri != null) {
			TreeUtils.saveToFile(pathToFile, this.aabri);
		} else {
			TreeUtils.saveToFile(pathToFile, new AABRI());
		}
	}

	/**
	 * @return the abr
	 */
	public AABRINode getAbr() {
		return abr;
	}

	/**
	 * @param abr the abr to set
	 */
	public void setAbr(AABRINode abr) {
		this.abr = abr;
	}

	/**
	 * Charge un arbre binaire depuis un fichier
	 * 
	 * @param absolutePath - le chemin complet vers le fichier, nom du fichier inclus
	 */
	public void loadFromFile(String pathToFile) {
		this.aabri = TreeUtils.initBinaryTreeFromFile(pathToFile);
		this.abr = null;
		this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
		this.toAABRIButton.setEnabled(false);
		this.toABRButon.setEnabled(true);
		this.repaint();
	}

	/**
	 * @return the aproposMenu
	 */
	public JMenu getAproposMenu() {
		return autresMenu;
	}

	/**
	 * @param aproposMenu the aproposMenu to set
	 */
	public void setAproposMenu(JMenu aproposMenu) {
		this.autresMenu = aproposMenu;
	}

	/**
	 * @return the aeraAABRI
	 */
	public JTextArea getAeraAABRI() {
		return aeraAABRI;
	}

	/**
	 * @param aeraAABRI the aeraAABRI to set
	 */
	public void setAeraAABRI(JTextArea aeraAABRI) {
		this.aeraAABRI = aeraAABRI;
	}

	/**
	 * @return the aProposItem
	 */
	public JMenuItem getaProposItem() {
		return aProposItem;
	}

	/**
	 * @param aProposItem the aProposItem to set
	 */
	public void setaProposItem(JMenuItem aProposItem) {
		this.aProposItem = aProposItem;
	}

	/**
	 * Permet d'afficher des messages d'information dans l'application
	 * 
	 * @param message - Le message à afficher
	 * @param typeMessage - le type du message, exemple : JOptionPane.WARNING_MESSAGE
	 */
	public void showModal(Window owner, String message, int typeMessage) {
		JOptionPane.showMessageDialog(owner, message, "Information", typeMessage);
	}

	/**
	 * Insère une valeur dans l'abre binaire en cours d'affichage dans la fenêtre.
	 * 
	 * @param valeur
	 */
	public void insertValue(int valeur) {
		if (this.abr != null) {
			abr.insert(valeur);
			this.aeraAABRI.setText(this.abr.getInfos(this.abr.getRoot()));
		} else if (this.aabri != null) {
			try {
				this.aabri.addSimpleNode(valeur);
				this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
			} catch (IntervalleInexistantException e) {
				this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
		this.repaint();
	}

	/**
	 * Supprimer une valeur dans l'arbre en cours d'affichage
	 * 
	 * @param valeur
	 */
	public void deleteValue(int valeur) {
		if (this.abr != null) {
			try {
				abr.delete(valeur);
				this.aeraAABRI.setText(this.abr.getInfos(this.abr.getRoot()));
			} catch (ValeurNonRepresenteeDansABRI e) {
				this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.aabri != null) {
			try {
				this.aabri.delete(valeur);
				this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
			} catch (Exception e) {
				this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
		this.repaint();
	}

	/**
	 * Vérifie l'arbre courant
	 */
	public void checkArbre() {

		if (this.aabri != null) {
			try {
				this.aabri.isWellFormed();
				this.showModal(this, "Arbre correctement formé.", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.abr != null) {
			try {
				this.abr.isWellFormed(this.abr.getRoot());
				this.showModal(this, "Arbre correctement formé.", JOptionPane.INFORMATION_MESSAGE);
			} catch (SimpleNodeMalPositionne e) {
				this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Permet de transformer l'arbre binaire courant en AABRI à condition qu'il ne le soit pas déjà.
	 */
	public void toAABRI() {
		if (this.aabri == null) {

			String nbreIntervalles;
			int nbreIntervallesAsInt;

			// Gestion du grisage des boutons
			this.toAABRIButton.setEnabled(false);
			this.toABRButon.setEnabled(true);

			// Récupération du nombre d'intervalles désiré auprès de l'utilisateur
			nbreIntervalles = JOptionPane.showInputDialog(this, "Nombre d'intervalles de l'AABRI :", "Veuillez répondre",
			        JOptionPane.QUESTION_MESSAGE);

			if (nbreIntervalles != null && !nbreIntervalles.equals("")) {
				nbreIntervallesAsInt = Integer.parseInt(nbreIntervalles);

				// Tranformation
				try {
					this.aabri = this.abr.toAABRI(nbreIntervallesAsInt);
				} catch (Exception e) {
					this.showModal(this, e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
				this.abr = null;

				// Actualisation de l'interface
				this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
				this.repaint();
			}
		}
	}

	/**
	 * Permet de transformer l'arbre binaire courant en ABR à condition qu'il ne le soit pas déjà
	 */
	public void toABR() {
		if (this.abr == null) {

			// Gestion du grisage des boutons
			this.toABRButon.setEnabled(false);
			this.toAABRIButton.setEnabled(true);

			// Tranformation
			this.abr = this.aabri.toABR();
			this.aabri = null;

			// Actualisation de l'interface
			this.aeraAABRI.setText(this.abr.getInfos(this.abr.getRoot()));
			this.repaint();
		}
	}
}
