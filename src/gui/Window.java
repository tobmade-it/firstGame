package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import chars.Player;
import game.Visible;

public class Window extends JFrame {
	private Color background = new Color(56,56,56);
	private String name = "Our first game";
	public final static int WIDTH = 1000;
	public final static int HEIGHT = 700;
	private GameArea gameArea = null;
	
	public Window(Visible[][] field) {
		gameArea = new GameArea(background, field);
        initUI();
    }
	
    private void initUI() {      
        add(this.gameArea);
        
        addKeyListener(new KeyListener() {
        	
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				int dis = 10;
				
				Player p = gameArea.p;
				System.out.println(p.getX()/10-1);
				System.out.println(p.getY()/10-1);
				if(key == 37) {
					// left
					if(!(gameArea.getFieldPosition((p.getX()/10)-1, p.getY()/10).getType() == "w")) {
						gameArea.p.setX(p.getX()-dis);
					}
				} else if(key == 38) {
					// top
					if(!(gameArea.getFieldPosition(p.getX()/10, (p.getY()/10)-1).getType() == "w")) {
						gameArea.p.setY(p.getY()-dis);
					}
				} else if(key == 39) {
					// right
					if(!(gameArea.getFieldPosition((p.getX()/10)+1, p.getY()/10).getType() == "w")) {
						gameArea.p.setX(p.getX()+dis);
					}
				} else if(key == 40) {
					// bottom
					if(!(gameArea.getFieldPosition(p.getX()/10, (p.getY()/10)+1).getType() == "w")) {
						gameArea.p.setY(p.getY()+dis);
					}
				}
				
			}
		});
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = gameArea.getTimer();
                timer.stop();
            }
        });

        setTitle(this.name);
        setSize(Window.WIDTH, Window.HEIGHT);
        setLocationRelativeTo(null); // Window in the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
