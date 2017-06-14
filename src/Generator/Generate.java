package Generator;

import java.util.Random;

import game.Visible;
import objects.*;
import shops.*;

public class Generate {
	
	public static Visible[][] genDungeon(int x, int y){ 
		Random r = new Random();
		
		Visible[][] tmpArray = new Visible[y][x];
		
		//ini the bining walls
		for(int i = 0; i < x; i++){
			tmpArray[0][i] = new Wall();
			tmpArray[y-1][i] = new Wall();
		}		
		for(int i = 0; i < y; i++){
			tmpArray[i][0] = new Wall();
			tmpArray[i][x-1] = new Wall();
		}
		
		//ini start room
		for(int i = 1; i < 9; i++){
			for(int j = 1; j < 9; j++){
				tmpArray[i][j] = new Floor();
			}
		}
		tmpArray[2][2] = new Healer();
		tmpArray[2][7] = new Armory();
		tmpArray[7][2] = new Bibliotary();
		tmpArray[7][7] = new Weaponary();
		tmpArray[4][4] = new Fire();
		tmpArray[0][4] = new Stairs();
		for(int i = 0; i < 8; i++){
			tmpArray[8][i+1] = new Wall();
		}
		for(int i = 0; i < 8; i++){
			tmpArray[i+1][8] = new Wall();
		}
		//stop ini startroom
		
		
		//create Rooms
		for(int hight = 1; hight < y-1; hight++){
			for(int width = 1; width < x-1; width++){
				//start
				int tmpx = 0;
				int tmpy = 0;
				if(tmpArray[hight][width] == null){
					//check how big it can be (count empty space)
					for(int i = 0; i < y-hight; i++){
						if(tmpArray[i+hight][width] == null){
							tmpy++;
						}else{
							break;
						}
					}
					for(int j = 0; j < x-width; j++){
						if(tmpArray[hight][j+width] == null){
							tmpx++;
						}else{
							break;
						}
					}
				}
				
				//decide how big it should be
				if(tmpx >= 10){
					tmpx = 4 + r.nextInt(tmpx/2-2);
				}
				if(tmpy >= 10){
					tmpy = 4 + r.nextInt(tmpy/2-2);
				}
				
				if(tmpx < 2 || tmpy < 2){
					for(int i = 0; i < tmpy; i++){
						for(int j = 0; j < tmpx; j++){
								tmpArray[width+j][hight+i] = new Wall();
						}
					}
					tmpx = 0;
					tmpy = 0;
				}
				
				//create room with floors and walls around
				for(int i = 0; i < tmpy; i++){
					for(int j = 0; j < tmpx; j++){
						if(i == 0){
							tmpArray[hight+i-1][width+j] = new Wall();
							if(j == 0){
								tmpArray[hight+i-1][width+j-1] = new Wall();
							}
						}
						if(i == tmpy-1){
							tmpArray[hight+i+1][width+j] = new Wall();
							if(j == 0){
								tmpArray[hight+i+1][width+j-1] = new Wall();
							}
						}
						if(j == 0){
							tmpArray[hight+i][width+j-1] = new Wall();
							if(i == tmpy-1){
								tmpArray[hight+i+1][width+j-1] = new Wall();
							}
						}
						if(j == tmpx-1){
							tmpArray[hight+i][width+j+1] = new Wall();
							if(i == 0){
								tmpArray[hight+i-1][width+j+1] = new Wall();
							}
							if(i == tmpy-1){
								tmpArray[hight+i+1][width+j+1] = new Wall();
							}
						}
						tmpArray[hight+i][width+j] = new Floor();

					}
				}
				
			}//end
			
			
		}
		
		return tmpArray;
	}

}
