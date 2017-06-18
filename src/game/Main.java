package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Generator.Generate;

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
	
	public static void hitsound(Visible e){
		if(e.getType() == "^" || e.getType() == "Y"){
			playsong("autsch");
		}
	}

	public static void main(String[] args) {
		
		//limit: 127! warum? keine ahnung, fehler in generator klasse, stack overflow? aber kein fehler?!
		int x = 100;
		int y = 100;
		
		Visible[][] gameField = Generate.genDungeon(x, y);
		
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
