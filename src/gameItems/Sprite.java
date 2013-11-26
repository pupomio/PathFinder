package gameItems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Sprite {
		private int x;
		private int y;
		 // Speed.
        private double speed = 2;
        
        // Bug heading.
        private double heading = 0;
        
        // Path colour.
        private Color pathColour;
        
        // Movement Path.
        private ArrayList<Point> path = new ArrayList<Point>();
        
        // Selected.
        private boolean selected = false;

		public Sprite(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void update() {
            if (!path.isEmpty()) {
            		System.out.println(path.size());
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
               path.add(p);
        }
		public void draw(Graphics2D g2d) {
			g2d.drawArc(x, y, 10, 10, 0, 360);
			g2d.setColor(Color.RED);
			g2d.fillArc(x+1, y+1, 9, 9, 0, 360);
			g2d.setColor(Color.BLACK);
		}
}
