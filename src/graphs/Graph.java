package graphs;

import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;



public class Graph {
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	private Node[][] ns;
	private Node current;
	
	public Graph(int[][] flagArray) {
		// New array for nodes
		Node[][] ns = new Node[flagArray.length][flagArray[0].length];
		for(int y = 0; y<flagArray.length; y++ ) {
			for (int x = 0; x < flagArray[y].length; x++) {
				if(flagArray[y][x] == 1) {
					int pos_x = y*Global.NODE_SPACING+(Global.NODE_SPACING/2);
					int pos_y = x*Global.NODE_SPACING+(Global.NODE_SPACING/2);
					Node n = new Node(pos_x, pos_y);
					ns[y][x] = n;
					nodes.add(n);
					
				}
			}
		}
		this.ns = ns;
		for(int y = 0; y<ns.length; y++ ) {
			for (int x = 0; x < ns[y].length; x++) {
				if(ns[y][x] != null) {
					// Create edges to adjacent nodes
					if (y > 0) {
						Node node =  (ns[y-1][x] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					
					if (x > 0 && y > 0) {
						Node node =  (ns[y-1][x-1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					if (y > 0 && x < ns.length) {
						Node node =  (ns[y-1][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					if (y < ns.length && x > 0) {
						Node node =  (ns[y+1][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					
					if (x < ns[y].length) {
						Node node =  (ns[y][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
				}
			}
			
		}
		System.out.println(edges.size());
		System.out.println(nodes.size());
	}
	
	public void draw(Graphics2D g2d) {
		//return;
//		g2d.setColor(Color.BLUE);
//		for (Node n : nodes) {
//			g2d.setColor(n == current ? Color.YELLOW : Color.BLUE);
//			g2d.fillArc(n.x, n.y, n.isVisited() ? 10 : 3,n.isVisited() ? 10 : 3, 0, 360);
//		}
//		g2d.setColor(Color.GREEN);
//		for (Edge e : edges) {
//			g2d.drawLine(e.a.x, e.a.y, e.b.x, e.b.y);
//		}
		g2d.setColor(Color.BLUE);
		for (Node n : nodes) {
			g2d.setColor(n == current ? Color.YELLOW : Color.BLUE);
			g2d.fillArc(n.x-1, n.y-1, n.isVisited() ? 10 : 3,n.isVisited() ? 10 : 3, 0, 360);
		}
		g2d.setColor(Color.BLACK);
	}
	public class Node implements Comparable<Node> {
		private ArrayList<Edge> neighbours = new ArrayList<Edge>();
		public ArrayList<Edge> getNeighbours() {
			return neighbours;
		}
		public boolean isVisited() {
			return visited;
		}
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		public Node(int pos_x, int pos_y) {
			this.x = pos_x;
			this.y = pos_y;
		}
		public void addNeighbour(Edge e) {
			this.neighbours.add(e);
			
		}
		// Position on collision map
		public int x;
		public int y;
		boolean visited = false;
		public int f_score = 0;
		public int g_score;
		public Node came_from = null;
		@Override
		public int compareTo(Node arg0) {
			if (arg0.f_score > f_score) return -1;
			if (arg0.f_score < f_score) return 1;
			return 0;
		}
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
		ArrayList<Node> sho_path = new ArrayList<Node>();
		ArrayList<Node> open_set = new ArrayList<Node>();
		ArrayList<Node> closed_set = new ArrayList<Node>();
		
		current = ns[(int)start.getX()/Global.NODE_SPACING][(int) start.getY()/Global.NODE_SPACING];
		int crow_flies = ((int) Math.sqrt(Math.pow(start.getX()-dest.x, 2)+Math.pow(dest.y-start.getY(), 2)));
		while (current == null) {
			current = ns[(int)start.getX()-1/Global.NODE_SPACING][(int) start.getY()-1/Global.NODE_SPACING];
		}
		current.f_score = crow_flies;
		
		current.g_score = 0;
		open_set.add(current);
		Node goal = ns[dest.x/Global.NODE_SPACING][dest.y/Global.NODE_SPACING];
		if (goal == null) return null;
		goal.setVisited(true);
		System.out.println(crow_flies);
		while (open_set.size() > 0) {
			
			Collections.sort(open_set);
			current = open_set.get(0);
			open_set.remove(current);
			closed_set.add(current);
			if (current == goal)
				return reconstruct_path(current.came_from , goal, new ArrayList<Node>());
			for(Edge n : current.neighbours) {
				Node neighbour;
				if (n.a == current) {
					neighbour = n.b;
				}
				else {
					neighbour = n.a;
				}
				
				int tent_g_score = current.g_score + 1;
				int tent_f_score = (int) (tent_g_score + (Math.sqrt(Math.pow(neighbour.x - goal.x, 2)+Math.pow(neighbour.y - goal.y, 2))));
				if (closed_set.contains(neighbour) && tent_f_score >= neighbour.f_score) {
					continue;
				}
				
				if (!open_set.contains(neighbour) || tent_f_score < neighbour.f_score) {
					neighbour.came_from = current;
					neighbour.f_score = tent_f_score;
					neighbour.g_score = tent_g_score;
					if (!open_set.contains(neighbour)) 
						open_set.add(neighbour);
				}
			}
			
		}
		Collections.sort(closed_set);
		current = closed_set.get(0);
		return shortest_path(start, new Point(current.x, current.y));
	}

	private ArrayList<Node> reconstruct_path(Node came_from,
			Node goal, ArrayList<Node> path) {
		
		while (came_from != null && !path.contains(came_from)) {
			path.add(0,goal);
			goal = came_from;
			came_from = came_from.came_from;
		}
		for (Node n : nodes) {
			
				n.setVisited(false);
				n.f_score = 0;
				n.g_score = 0;
				n.came_from = null;
			
		}
		
		
		return path;
	}
}
