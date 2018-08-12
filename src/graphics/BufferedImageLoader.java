package graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	BufferedImage image;

	/*
	 * returns buffered image when provided a path
	 * @param {String} path path to file
	 * @return {BufferedImage}
	 */
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
