/**
 * 
 */
package ihm.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Cyril
 *
 */
/**
 * This class loads and shows a picture, that can be either in the same jar file than the program or not. If the picture is smaller than the available
 * space, it will be centered. If the picture is bigger than the available space, a zoom will be applied in order to fit exactly the space.
 */
public class SplashPicture extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4678904039624006698L;
	Image img;

	public SplashPicture(String pathToFile) {

		try {
			BufferedImage bimg = ImageIO.read(new File(pathToFile));
			img = new ImageIcon(bimg).getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img == null)
			return;
		int w = img.getWidth(this);
		int h = img.getHeight(this);
		boolean zoom = (w > getWidth() || h > getHeight());
		if (zoom)
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		else
			g.drawImage(img, (getWidth() - w) / 2, (getHeight() - h) / 2, this);
	}
}