package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import chars.Player;
import game.Visible;

public class GameArea extends JPanel implements ActionListener {
	private final int DELAY = 150; // FPS of the game
    private Timer timer;
    private int width = 700;
    private int height = 600;
    private Visible[][] gameField = null;
    public Player p = new Player();
    
    public GameArea(Color background, Visible[][] gameField) {   	
    	this.setBackground(background);
    	this.gameField = gameField;
    	this.p.setX(20);
    	this.p.setY(20);
    	
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
        
        g2d.drawRect(0, 0, this.width, this.height);
        
        int x = 70;
        int y = 60;

        
        for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				Visible v = gameField[i][j];
				if(v != null){
					// draw wall
					if(v.getType() == "w") {
						g2d.setPaint(Color.white);
						g2d.fillRect(j*10, i*10, 10, 10);
					}
					if(v.getType() == " ") {
						g2d.setPaint(Color.blue);
						g2d.fillRect(j*10, i*10, 10, 10);
					}
				}else{
					System.out.print("! ");
				}
			}
        }
        
        // draw Player
        g2d.fillRect(p.getX(), p.getY(), 10, 10);
        
    }
    
    public Visible getFieldPosition(int x, int y) {
    	return gameField[x][y];
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
