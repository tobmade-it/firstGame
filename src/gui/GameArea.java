package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Visible;

public class GameArea extends JPanel implements ActionListener {
	private final int DELAY = 550; // FPS of the game
    private Timer timer;
    private int width = 700;
    private int height = 600;
    private Visible[][] gameField = null;
    
    public GameArea(Color background, Visible[][] gameField) {   	
    	this.setBackground(background);
    	this.gameField = gameField;
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
        
        // simulated player
        g2d.fillRect(200, 200, 10, 10);
        int x = 70;
        int y = 60;
        
        System.out.println(gameField.length);
        
        for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				Visible v = gameField[i][j];
				if(v != null){
					if(v.getType() == "w") {
						System.out.println("WAND" + "X = " + (i*10) + "Y = " + (j*10));
						System.out.println(v.getvisibility());
						g2d.drawRect(j*10, i*10, 10, 10);
					}
				}else{
					System.out.print("! ");
				}
			}
			System.out.println();
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
