package gui;

import java.awt.EventQueue;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.Visible;
import generator.Generate;

/**
 * 
 * Start class for graphical interface
 * Generates the Dungeon, start game sound and initialize the Window Object.
 *
 */
public class Render {
	// Size of the generated game
	public final static int X_FIELD = 70;
	public final static int Y_FIELD = 60;
	
	public static void main(String args[]) {		
		Visible[][] gameField = Generate.genDungeon(Render.X_FIELD, Render.Y_FIELD, 9 , 25);
		System.out.println("FELD: " + gameField.length);
		
		try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sounds/rpgmusic1.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            
        } catch(Exception ex) {
            System.out.println("Error with playing sound");
            ex.printStackTrace();
        }
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window ex = new Window(gameField);
                ex.setVisible(true);
            }
        });
	}
}
