package ihm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**
 * A splash screen to show while the main program is loading. A typical use is:
 * 
 * <pre>
 * 
 * public static void main(String[] args) {
 * 	Splash s = new Splash(delay1);
 * 	new MainProgram();
 * 	s.dispose(delay2);
 * }
 * 
 * </pre>
 * 
 * The first line creates a Splash that will appear until another frame hides it (MainProgram), but at least during "delay1" milliseconds.<br>
 * To distroy the Splash you can either call "s.dispose()" or "s.dispose(delay2)", that will actually show the Splash for "delay2" milliseconds and
 * only then hide it.<br>
 * The picture to show must be in a file called "splash.png".
 */
public class Splash extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7233939832298425119L;

	/**
	 * Creates a Splash that will appear until another frame hides it, but at least during "delay" milliseconds.
	 * 
	 * @param delay the delay in milliseconds
	 */
	@SuppressWarnings("static-access")
	public Splash(int delay) {

		getContentPane().setLayout(new BorderLayout());

		JPanel panelImage = new JPanel();
		panelImage.setLayout(new BorderLayout());
		panelImage.setBackground(Color.WHITE);
		panelImage.add(new SplashPicture("resources/splash.png"));

		JPanel panelText = new JPanel();
		JLabel texte = new JLabel("Tous aux AABRIs !!!");
		texte.setFont(new Font(UIManager.getDefaults().getFont("TabbedPane.font").getName(), Font.PLAIN, 25));
		panelText.add(texte);

		getContentPane().add(panelText, BorderLayout.NORTH);
		getContentPane().add(panelImage, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);

		JPanel panelCredits = new JPanel();
		panelCredits.setLayout(new BorderLayout());

		JLabel creditsLabel1 = new JLabel("Projet de compléments en algorithme réalisé dans le cadre du master 1 MIAGE de Nantes.");
		creditsLabel1.setHorizontalAlignment(JLabel.CENTER);

		JLabel creditsLabel3 = new JLabel("Sébastien Heuzé et Cyril Le Driant");
		creditsLabel3.setHorizontalAlignment(JLabel.CENTER);
		panelCredits.add(creditsLabel1, BorderLayout.NORTH);
		panelCredits.add(creditsLabel3, BorderLayout.SOUTH);
		panelCredits.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panelCredits, BorderLayout.SOUTH);

		setSize(520, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		try {
			Thread.sleep(delay);
		} catch (Exception e) {
		}
	}

	/**
	 * Shows the Splash during the specified time (in milliseconds) and then hides it.
	 * 
	 * @param delay the delay in milliseconds
	 */
	public void dispose(int delay) {
		dispose();
		Splash s = new Splash(delay);
		s.dispose();
	}
}
