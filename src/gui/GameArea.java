package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import chars.Player;
import game.Settings;
import game.Visible;

public class GameArea extends JPanel implements ActionListener {
	private final int DELAY = 1000/Settings.FPS; // FPS of the game
    private Timer timer;
    private Visible[][] gameField = null;
    public Player p = new Player(20);
    
    public GameArea(Visible[][] gameField) {   	
    	this.setBackground(Settings.BACKGROUND);
    	this.gameField = gameField;
    	this.p.setX(4);
    	this.p.setY(1);
    	
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        return timer;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        int w = getWidth();
        int h = getHeight();
        
        g2d.setPaint(Color.white);
        
        g2d.drawRect(0, 0, Settings.GAME_W, Settings.GAME_H);
        
        int x = Render.X_FIELD;
        int y = Render.Y_FIELD;
        
        try {
        	// System.out.println();
        } catch(ArrayIndexOutOfBoundsException ax) {
        	
        }
        
        for(int i = 0; i < 20; i++) {
        	for(int j = 0; j < 20; j++) {
        		int xValue = p.getX()-(10-i);
        		int yValue = p.getY()-(10-j);
        		System.out.println("x: " + xValue);
        		System.out.println("y: " + yValue);
        		if(xValue >= 0 && xValue < Render.X_FIELD && yValue >= 0 && yValue < Render.Y_FIELD) {
        			Visible v = gameField[yValue][xValue];
        			if(v.getType() == "w") {
        				g2d.setPaint(Color.white);
        				g2d.fillRect(i*35, j*30, 35, 30);
        			}
        			if(v.getType() == " ") {
						g2d.setPaint(Color.blue);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        			else if(v.getType() == "C") {
						g2d.setPaint(Color.ORANGE);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        			else if(v.getType() == "Y") {
						g2d.setPaint(Color.red);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        			else if(v.getType() == "^") {
						g2d.setPaint(Color.gray);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        			else if(v.getType() == "=") {
						g2d.setPaint(Color.GREEN);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        			else if(v.getType() == "0") {
						g2d.setPaint(Color.pink);
						g2d.fillRect(i*35, j*30, 35, 30);
					}
        		}
            }
        }
        /*
        for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				Visible v = gameField[i][j];
				if(v != null){
					// draw wall
					int off = 200;
					if(v.getType() == "w") {
						g2d.setPaint(Color.white);
						g2d.fillRect(off+j*30, off+i*35, 30, 35);
					}
					if(v.getType() == " ") {
						g2d.setPaint(Color.blue);
						g2d.fillRect(off+j*30, off+i*35, 30, 35);
					}
				}else{
					System.out.print("! ");
				}
			}
        } */
        
        // draw Player
        g2d.setPaint(Color.red);
        g2d.fillRect((Settings.GAME_W/2), (Settings.GAME_H/2), 35, 30);
        
    }
    
    public Visible getFieldPosition(int x, int y) {
    	return gameField[y][x];
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
