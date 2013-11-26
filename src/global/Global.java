package global;

import gameItems.Sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Global {
	public static BufferedImage BG;
	public static Sprite SPRITE;
	
	
	
	static {
		try {
			BG = ImageIO.read(new File("simple_coll_map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
