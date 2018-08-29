package graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/*
 * Loads images and returns them as  buffered image
 * @author MCForsas 2018
 */
public class BufferedImageLoader {
	
	BufferedImage image;

	/*
	 * returns buffered image when provided a path
	 * @param String path path to file
	 * @return BufferedImage image
	 */
	public BufferedImage loadImage(String path) {
		try {
			this.image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.image;
	}
}
