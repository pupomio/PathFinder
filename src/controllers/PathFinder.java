package controllers;

import global.Global;
import graphs.Graph.Node;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PathFinder {
	

	static {
		
	}
	
	
	public static ArrayList<Point> intelligentPath(Point2D start, Point dest) {
		ArrayList<Point> path =  new ArrayList<Point>();
		long cur = System.currentTimeMillis();
		ArrayList<Node> sho_path = Global.GRAPH.shortest_path(start, dest);
		System.out.println("Time taken:" + (System.currentTimeMillis()-cur));
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
	
	

	public static ArrayList<Point> compress(ArrayList<Point> long_path) {
		if (long_path.size() < 1) return long_path;
		
		
		return long_path;
	}
}
