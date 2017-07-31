package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import chars.Player;
import game.Settings;
import game.Visible;
import generator.Generate;
import generator.Room;
import items.Axe;

/**
 * Drawing Class
 * In this class every object will be drawn to the Window.
 * The doDrawing() function does all the magic and is called
 * as often as the FPS is set in 'Settings' class.
 */
@SuppressWarnings("serial")
public class GUIMain extends JPanel implements ActionListener {
    private Timer timer;
    public DungeonArea dungeon = null;
    public Inventory inv = null;
    private Visible[][] gameField = null;
    public boolean status = true; // 1 -> Dungeon, 0 -> Combat
    public boolean invOpen = false;
    
    private final int DELAY = 1000/Settings.FPS; // FPS of the game
    
    // coordinates to compare with the current coordinates
    // to see if a player enters a new room
    public int lastPlayerX;
    public int lastPlayerY;
    public static Player p = new Player(20);
    
    public GUIMain(Visible[][] field) {
    	this.setBackground(Settings.BACKGROUND);
    	this.gameField = field;
    	
    	dungeon = new DungeonArea(gameField);
    	inv = new Inventory();
    	
    	Axe a = new Axe();
    	inv.addItem(a);
    	
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    /**
     * All the magic happens here.
     * The whole window is filled with drawings, defined here.
     * 
     * @param g		general Graphics object
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // border around the game area
        g2d.setPaint(Color.white);
        g2d.drawRect(0, 0, Settings.GAME_W, Settings.GAME_H);
        
        // draw help bar
        g2d.setPaint(Color.red);
        g2d.fillRect(0, 600, p.getMaxHP()*7, 60);
        g2d.setPaint(Color.green);
        g2d.fillRect(0, 600, p.getHP()*7, 60);  
        
        int x = Render.X_FIELD;
        int y = Render.Y_FIELD;
        
        // some prompt for debugging
        g2d.drawString("x: " + Integer.toString(p.getX()), 800, 20);
        g2d.drawString("y: " + Integer.toString(p.getY()), 800, 40);
        g2d.drawString("x: " + Integer.toString(this.lastPlayerX), 800, 60);
        g2d.drawString("y: " + Integer.toString(this.lastPlayerY), 800, 80);
        
        if(status) {
        	dungeon.draw(g2d);
        	
        	if(invOpen) {
        		inv.draw(g2d);
        	}
        } else {
        	// combat.draw
        }
        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
