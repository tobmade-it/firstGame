package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Generator.Generate;
import chars.Player;
import objects.Floor_moon;
import objects.Floor_sun;

public class Main {
	
	//ich weiß , gehört hier nicht hin
	public static void playsong(String sound){
		try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./src/sound/" + sound + ".wav").getAbsoluteFile());
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
			playsong("autsch");
			p.takeDmg(5);
		}else if(e.getType() == "1" || e.getType() == "2"){
			playsong("autsch");
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
		
		playsong("rpgmusic1");
		
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
