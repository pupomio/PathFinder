package graphs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;



public class Graph {
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public Graph(int[][] flagArray) {
		// New array for nodes
		Node[][] ns = new Node[flagArray.length][flagArray[0].length];
		for(int y = 0; y<flagArray.length; y++ ) {
			for (int x = 0; x < flagArray[y].length; x++) {
				if(flagArray[y][x] == 1) {
					int pos_x = y*5+3;
					int pos_y = x*5+3;
					Node n = new Node(pos_x, pos_y);
					ns[y][x] = n;
					nodes.add(n);
					
				}
			}
		}
		for(int y = 0; y<ns.length; y++ ) {
			for (int x = 0; x < ns[y].length; x++) {
				if(ns[y][x] != null) {
					// Create edges to adjacent nodes
					if (y > 0) {
						Node node =  (ns[y-1][x] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e))
								edges.add(e);
						}
					}
					if (x > 0) {
						Node node =  (ns[y][x-1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e))
								edges.add(e);
						}
					}
					if (y < ns.length) {
						Node node =  (ns[y+1][x] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e))
								edges.add(e);
						}
					}
					if (x < ns[y].length) {
						Node node =  (ns[y][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e))
								edges.add(e);
						}
					}
				}
			}
			
		}
		System.out.println(edges.size());
		System.out.println(nodes.size());
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		for (Node n : nodes) {
			g2d.fillArc(n.x, n.y, 3, 3, 0, 360);
		}
		g2d.setColor(Color.GREEN);
		for (Edge e : edges) {
			g2d.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
		}
		g2d.setColor(Color.BLACK);
	}
	public class Node {
		public Node(int pos_x, int pos_y) {
			this.x = pos_x;
			this.y = pos_y;
		}
		// Position on collision map
		public int x;
		public int y;
		boolean visited = false;
	}
	public class Edge {
		public Edge(Node node, Node node2) {
			a = node;
			b = node2;
		}
		@Override
		public boolean equals(Object edge) {
			Edge e = (Edge) edge;
			if (e.a == a && e.b == b) {
				return true;
			}
			if (e.b == a && e.a == b) {
				return true;
			}
			return false;
		}
		// Connected nodes
		Node a, b;
	}
	public ArrayList<Node> shortest_path(Point2D start, Point dest) {
		// TODO Auto-generated method stub
		return null;
	}
}
