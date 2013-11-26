package controllers;

import global.Global;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handles mouse actions.
 */
public class MouseController implements MouseListener, MouseMotionListener {

       
        
        @Override
        public void mouseClicked(MouseEvent e) {
        	
        }

        
        @Override
        public void mousePressed(MouseEvent e) {
        	Global.SPRITE.pathTo(e.getPoint()); 
        }

        @Override
        public void mouseReleased(MouseEvent e) {
               
        }

        @Override
        public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }
        @Override
        public void mouseDragged(MouseEvent e) {
               
        }

        @Override
        public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
        }
        
       
}