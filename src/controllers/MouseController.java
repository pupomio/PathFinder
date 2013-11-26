package controllers;

import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handles mouse actions.
 */
public class MouseController implements MouseListener, MouseMotionListener {

        // Last pressed pos.
        private static Point lastPressed = null;
        
        @Override
        public void mouseClicked(MouseEvent e) {
        	
        }

        
        @Override
        public void mousePressed(MouseEvent e) {
        	
        }

        @Override
        public void mouseReleased(MouseEvent e) {
               Global.SPRITE.pathTo(e.getPoint()); 
        }

        @Override
        public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }

        private static Point draggedTo = null;
        @Override
        public void mouseDragged(MouseEvent e) {
                draggedTo = e.getPoint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }
        
       
}