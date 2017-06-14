package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

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
