package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Utilities for images.
 * @author Grayson Spidle
 *
 */
public class ImageUtils {
	
	public static BufferedImage read(File arg0) {
		try {
			return ImageIO.read(arg0);
		} catch (IOException e) {
			return null;
		} catch (Exception e2) {
			e2.printStackTrace();
			return null;
		}
	}
}
