package controllers;

import global.Global;
import graphs.Graph;
import graphs.Graph.Node;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PathFinder {
	

	static {
		build_collision_graph();
	}
	
	
	public static ArrayList<Point> intelligentPath(Point2D start, Point dest) {
		ArrayList<Point> path =  new ArrayList<Point>();
		ArrayList<Node> sho_path = Global.GRAPH.shortest_path(start, dest);
		if (sho_path == null) {
			return path;
		}
		else {
			for (Node n : sho_path) {
				path.add(new Point(n.x, n.y));
			}
			return path;
		}
	}
	
	private static void build_collision_graph() {
		int[][] flagArray = new int[Global.BG.getWidth()][Global.BG.getHeight()];
		for(int y = 0; y< Global.BG.getHeight(); y+=5) {
			for (int x = 0; x < Global.BG.getWidth(); x += 5) {
				// Get the colour of the four corners and set the array flag accordingly
				// Top left x,y
				int rgb = Global.BG.getRGB(x, y);
				if(rgb != -1) {
					continue;
				}
				// Top right x+9, y
				rgb = Global.BG.getRGB(x+4, y);
				if(rgb != -1) {
					continue;
				}
				// Top left x,y+9
				rgb = Global.BG.getRGB(x, y+4);
				if(rgb != -1) {
					continue;
				}
				// Top right x+9, y+9
				rgb = Global.BG.getRGB(x+4, y+4);
				if(rgb != -1) {
					continue;
				}
				
				// None of the corners were impassable
				flagArray[x/5][y/5] = 1;
			}
		}
		Global.GRAPH = new Graph(flagArray);
		
	}

	public static ArrayList<Point> compress(ArrayList<Point> long_path) {
		if (long_path.size() < 1) return long_path;
		
		
		return long_path;
	}
}
