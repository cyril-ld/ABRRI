/**
 * 
 */
package ihm.gui;

import ihm.controler.RandomAABRIButtonListener;
import ihm.controler.SaveItemListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.TreeUtils;
import datastructure.AABRI;

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
	 * Menu de la fenêtre
	 */
	private JMenu menu;

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

		this.createMenu();
		this.createPanels();
		this.addActionListeners();
	}

	/**
	 * Création du menu de la fenêtre
	 */
	private void createMenu() {

		this.menu = new JMenu("Menu");
		this.menuBar = new JMenuBar();
		this.loadItem = new JMenuItem("Charger...");
		this.saveItem = new JMenuItem("Sauvegarder...");

		this.menu.add(this.loadItem);
		this.menu.add(this.saveItem);
		this.menuBar.add(this.menu);
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
		this.randomAABRIButton.setBackground(Color.BLUE);
		this.checkButton = new JButton("Vérifier l'arbre");
		this.checkButton.setBackground(Color.CYAN);
		this.insertValueButton = new JButton("Insérer une valeur");
		this.insertValueButton.setBackground(Color.RED);
		this.deleteValueButton = new JButton("Supprimer une valeur");
		this.deleteValueButton.setBackground(Color.YELLOW);
		this.toABRButon = new JButton("Convertir en ABR");
		this.toABRButon.setBackground(Color.DARK_GRAY);
		this.toAABRIButton = new JButton("Convertir en AABRI");
		this.toAABRIButton.setBackground(Color.PINK);

		this.aeraAABRI = new JTextArea("Rien à afficher pour l'instant...");
		this.aeraAABRI.setEditable(false);

		this.actionsPanel.add(this.checkButton);
		this.actionsPanel.add(this.randomAABRIButton);
		this.actionsPanel.add(this.insertValueButton);
		this.actionsPanel.add(this.deleteValueButton);
		this.actionsPanel.add(this.toABRButon);
		this.actionsPanel.add(this.toAABRIButton);

		this.displayPanel.add(this.aeraAABRI);

		this.add(this.displayPanel);
		this.add(this.actionsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Ajoute les listeners sur les différents éléments graphiques
	 */
	private void addActionListeners() {
		this.randomAABRIButton.addActionListener(new RandomAABRIButtonListener(this));
		this.saveItem.addActionListener(new SaveItemListener(this));
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
		this.aeraAABRI.setText(this.aabri.getInfos(this.aabri.getRootNode()));
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
}
