package game;

/**
 * Anmerkung: Diese Datei diese Datei dient nur für Testzwecke.
 * Die eigentliche Main befindet sich im package gui. Die Render.java
 * muss ausgeführt werden.
 */
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import chars.Player;
import generator.Generate;
import objects.Floor_moon;
import objects.Floor_sun;

public class Main {
	
	//ich weiß , gehört hier nicht hin
	public static void playsound(String sound){
		try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sounds/" + sound + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            /*
            while(true){
               Thread.sleep(10);
                if(!clip.isActive()) break;
            }
            */
            
        } catch(Exception ex) {
            System.out.println("Error with playing sound");
            ex.printStackTrace();
        }
	}
	
	public static void hitsound(Visible e, Player p){
		if(e.getType() == "^" || e.getType() == "Y"){
			playsound("autsch");
			p.takeDmg(5);
		}else if(e.getType() == "1" || e.getType() == "2"){
			playsound("test");
		}
	}
	
	public static Visible change(Visible e){
		if(e.getType() == "1"){
			return new Floor_sun();
		}else if(e.getType() == "2"){
			return new Floor_moon();
		}
		return e;
	}

	public static void main(String[] args) {
		
		//limit: 127! warum? keine ahnung, fehler in generator klasse, stack overflow? aber kein fehler?!
		int x = 100;
		int y = 100;
		
		Visible[][] gameField = Generate.genDungeon(x, y, 9 , 25);
		
		playsound("rpgmusic1");
		
		//test
		for(int i = 0; i < y; i++){
			for(int j = 0; j <x; j++){
				if(gameField[i][j] != null){
					System.out.print(gameField[i][j].getType() + " ");
				}else{
					System.out.print("! ");
				}
			}
			System.out.println();
		}		

	}

}
