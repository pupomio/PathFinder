package gameItems;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;



import controllers.PathFinder;

public class Sprite {
		private double x, y;
		 // Speed.
        private double speed = 2;
        
        // Bug heading.
        private double heading = 0;
        
        // Path colour.
        private Color pathColour = Color.CYAN;
        
        // Movement Path.
        private ArrayList<Point> path = new ArrayList<Point>();
        
        // Selected.
        private boolean selected = false;

		public Sprite(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public void update() {
            if (!path.isEmpty()) {
                    Point p = path.get(0);
                    
                    double l = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
                    
                    synchronized (path) {
                            if (l < speed) {
                                    path.remove(0);
                                    return;
                            }
                    }
                    
                    double dx = (x - p.x) / l;
                    double dy = (y - p.y) / l;
                    
                    x -= speed * dx;
                    y -= speed * dy;
                    
                    heading = Math.toDegrees(Math.atan2(dy, dx)) - 90;
                    
            }
    }
		/**
         * Find path to given point and set bug path.
         */
        public void pathTo(Point p) {
        		path.clear();
               path.addAll(PathFinder.intelligentPath(new Point2D.Double(x, y), p));
        }
		public void draw(Graphics2D g2d) {
			if (!path.isEmpty()) {
				g2d.setStroke(new BasicStroke(3));
				g2d.setColor(pathColour);
				Point prev = new Point((int) x, (int) y);
				synchronized (path) {
					Iterator<Point> it = path.iterator();
					while (it.hasNext()) {
						Point p = it.next();
						g2d.drawLine(prev.x, prev.y, p.x, p.y);
						prev = p;
					}
					g2d.setColor(Color.BLACK);
				}
			}
			g2d.drawArc((int)x-5, (int)y-5, 10, 10, 0, 360);
			g2d.setColor(Color.RED);
			g2d.fillArc((int)x-4, (int)y-4, 9, 9, 0, 360);
			g2d.setColor(Color.BLACK);
		}
}
