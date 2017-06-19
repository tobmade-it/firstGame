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
import game.Main;
import game.Settings;
import game.Visible;

public class Window extends JFrame {
	private String name = "Our first game";
	private GameArea gameArea = null;
	
	public Window(Visible[][] field) {
		gameArea = new GameArea(field);
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
				int dis = 1;
				
				Player p = gameArea.p;
				
				if(key == 37) {
					// left
					if(!(gameArea.getFieldPosition(p.getX()-1, p.getY()).getIsSolid())) {
						gameArea.p.setX(p.getX()-dis);
						Main.hitsound(gameArea.getFieldPosition(p.getX(), p.getY()),p);
					}
				} else if(key == 38) {
					// top
					if(!(gameArea.getFieldPosition(p.getX(), p.getY()-1).getIsSolid())) {
						gameArea.p.setY(p.getY()-dis);
						Main.hitsound(gameArea.getFieldPosition(p.getX(), p.getY()),p);
					}
				} else if(key == 39) {
					// right
					if(!(gameArea.getFieldPosition(p.getX()+1, p.getY()).getIsSolid())) {
						gameArea.p.setX(p.getX()+dis);
						Main.hitsound(gameArea.getFieldPosition(p.getX(), p.getY()),p);
					}
				} else if(key == 40) {
					// bottom
					if(!(gameArea.getFieldPosition(p.getX(), p.getY()+1).getIsSolid())) {
						gameArea.p.setY(p.getY()+dis);
						Main.hitsound(gameArea.getFieldPosition(p.getX(), p.getY()),p);
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
        setSize(Settings.WINDOW_H, Settings.WINDOW_W);
        setLocationRelativeTo(null); // Window in the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
