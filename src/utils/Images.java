package utils;

import java.awt.Image;
import java.io.File;

public class Images {
	
	public static final Image LOGO = ImageUtils.read(new File("images/logo.png"));
	public static final Image ADD_TO_QUEUE = ImageUtils.read(new File("images/addToQueue.png"));
	public static final Image NEXT = ImageUtils.read(new File("images/next.png"));
	public static final Image SHOW_QUEUE = ImageUtils.read(new File("images/queue.png"));
	
}
