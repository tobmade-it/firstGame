package gui;

import java.awt.EventQueue;
import Generator.Generate;
import game.Main;
import game.Visible;

public class Render {
	public final static int X_FIELD = 70;
	public final static int Y_FIELD = 60;
	
	public static void main(String args[]) {		
		Visible[][] gameField = Generate.genDungeon(Render.X_FIELD, Render.Y_FIELD);
		System.out.println("FELD: " + gameField.length);
		
		Main.playsong();
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window ex = new Window(gameField);
                ex.setVisible(true);
            }
        });
	}
}
