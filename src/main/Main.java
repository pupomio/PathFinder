package main;

import gameItems.Sprite;
import global.Global;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import controllers.MouseController;

public class Main extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		

	}
	 // Canvas on which the graphs are drawn.
    private static Canvas canvas = new Canvas();
    
    // Buffer strategy for frame blitting on canvas.
    private BufferStrategy bs;
    
    /**
     * Constructor.
     */
    public Main()
    {
        // Setup.
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas.addMouseListener(new MouseController());
        canvas.setIgnoreRepaint(true);
        
        Global.SPRITE = new Sprite(100, 285);
        
        // Add Components
        add(canvas, BorderLayout.CENTER);
        
        // Finish
        setVisible(true);
        
        // Set up buffer strategy.
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
    
        // Start game threads.
        GameThread.startGameThreads(canvas, bs);
    }

}
