package gui;

import java.awt.Color;
import java.awt.EventQueue;

import Generator.Generate;
import game.Visible;

public class Render {
	public static void main(String args[]) {
		int x = 70;
		int y = 60;
		
		Visible[][] gameField = Generate.genDungeon(x, y);
		System.out.println("FELD: " + gameField.length);
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window ex = new Window(gameField);
                ex.setVisible(true);
            }
        });
	}
}
