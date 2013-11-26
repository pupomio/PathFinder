package global;

import gameItems.Sprite;
import graphs.Graph;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Global {
	public static BufferedImage BG;
	public static Sprite SPRITE;
	public static Graph GRAPH;
	public static int NODE_SPACING = 5;
	
	
	
	static {
		try {
			BG = ImageIO.read(new File("maze.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		build_collision_graph();
	}
	private static void build_collision_graph() {
		int[][] flagArray = new int[Global.BG.getWidth()][Global.BG.getHeight()];
		for(int y = 0; y< Global.BG.getHeight(); y+=NODE_SPACING) {
			for (int x = 0; x < Global.BG.getWidth(); x += NODE_SPACING) {
				// Get the colour of the four corners and set the array flag accordingly
				// Top left x,y
				int rgb = Global.BG.getRGB(x, y);
				if(rgb != -1) {
					continue;
				}
				// Top right x+9, y
				rgb = Global.BG.getRGB(x+(NODE_SPACING-1), y);
				if(rgb != -1) {
					continue;
				}
				// Top left x,y+9
				rgb = Global.BG.getRGB(x, y+(NODE_SPACING-1));
				if(rgb != -1) {
					continue;
				}
				// Top right x+9, y+9
				rgb = Global.BG.getRGB(x+(NODE_SPACING-1), y+(NODE_SPACING-1));
				if(rgb != -1) {
					continue;
				}
				
				// None of the corners were impassable
				flagArray[x/NODE_SPACING][y/NODE_SPACING] = 1;
			}
		}
		Global.GRAPH = new Graph(flagArray);
		
	}
}
