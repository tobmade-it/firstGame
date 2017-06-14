package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Visible;
import objects.*;
import shops.*;

public class Generate {
	
	public static Visible[][] genDungeon(int x, int y){ 
		Random r = new Random();
		
		Visible[][] tmpArray = new Visible[y][x];
		List<Room> rooms = new ArrayList<Room>();
		
		//ini the binding walls
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
		//rooms.add(new Room(1,1,7,7));
		//stop ini startroom
		
		//ini bossroom
		for(int j = 1; j < 10; j++){
			for(int i = x-2; i > x-11; i--){
				tmpArray[j][i] = new Floor();	
			}
			tmpArray[j][x-11] = new Wall();
			tmpArray[10][x-j] = new Wall();
		}
		tmpArray[5][x-6] = new Floor_spikes();
		tmpArray[10][x-11] = new Wall();
		rooms.add(new Room(x-11,1,9,9));
		rooms.get(0).bossroom = true;
		
		//create Rooms
		for(int height = 1; height < y-1; height++){
			for(int width = 1; width < x-1; width++){
				//start
				int tmpx = 0;
				int tmpy = 0;
				if(tmpArray[height][width] == null){
					//check how big it can be (count empty space)
					for(int i = 0; i < y-height; i++){
						if(tmpArray[i+height][width] == null){
							tmpy++;
						}else{
							break;
						}
					}
					for(int j = 0; j < x-width; j++){
						if(tmpArray[height][j+width] == null){
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
				
				//useless, safety for empty spaces or too small rooms
				if(tmpx < 2 || tmpy < 2){
					for(int i = 0; i < tmpy; i++){
						for(int j = 0; j < tmpx; j++){
								//tmpArray[width+j][height+i] = new Wall();
						}
					}
					tmpx = 0;
					tmpy = 0;
				}
				
				//create room with floors and walls around
				for(int i = 0; i < tmpy; i++){
					for(int j = 0; j < tmpx; j++){
						if(i == 0){
							tmpArray[height+i-1][width+j] = new Wall();
							if(j == 0){
								tmpArray[height+i-1][width+j-1] = new Wall();
							}
						}
						if(i == tmpy-1){
							tmpArray[height+i+1][width+j] = new Wall();
							if(j == 0){
								tmpArray[height+i+1][width+j-1] = new Wall();
							}
						}
						if(j == 0){
							tmpArray[height+i][width+j-1] = new Wall();
							if(i == tmpy-1){
								tmpArray[height+i+1][width+j-1] = new Wall();
							}
						}
						if(j == tmpx-1){
							tmpArray[height+i][width+j+1] = new Wall();
							if(i == 0){
								tmpArray[height+i-1][width+j+1] = new Wall();
							}
							if(i == tmpy-1){
								tmpArray[height+i+1][width+j+1] = new Wall();
							}
						}
						int ran = r.nextInt(100);
						if(i > 2 && i < tmpy-2 && j > 2 && j < tmpx-2 && ran < 20){
							tmpArray[height+i][width+j] = new RandomObj().genRanObj();
						}else{
							tmpArray[height+i][width+j] = new Floor();
						}

					}	
				}
				//add room to list
				if(tmpx > 2 && tmpy > 2){
					rooms.add(new Room(width, height, tmpx, tmpy));
				}
			}//end
			
		}
		
		//get neighbours
		for(int i = 0; i < rooms.size(); i++){
			for(int j = 1; j < rooms.size(); j++){
				int cnt = 0;
				int firstx = 0;
				int firsty = 0;
				if(i != j){
					for(int w1 = 0; w1 < rooms.get(i).wallsx.size() ; w1++){
						for(int w2 = 0; w2 < rooms.get(j).wallsx.size() ; w2++){
							if(rooms.get(i).wallsx.get(w1) == rooms.get(j).wallsx.get(w2) && rooms.get(i).wallsy.get(w1) == rooms.get(j).wallsy.get(w2)){
								cnt++;
								firstx += rooms.get(j).wallsx.get(w2);
								firsty += rooms.get(j).wallsy.get(w2);
							}
						}
					}
					if(cnt > 2){
						rooms.get(i).neighbours.add(rooms.get(j).roomid);
						rooms.get(i).neidoorx.add(firstx/cnt);
						rooms.get(i).neidoory.add(firsty/cnt);
					}
				}
			}
		}
		
		//Add doors
		boolean allreachable = false;
		rooms.get(1).walkable = true;
		int timeout = 0;
		while(!allreachable){
			//is a neighbour walkable? if yes, build a door so we can be walkable too!
			for(int i = 2; i < rooms.size(); i++){
				for(int j = 0; j < rooms.get(i).neighbours.size(); j++){
					if(!rooms.get(i).walkable && rooms.get(rooms.get(i).neighbours.get(j)).walkable){
						tmpArray[rooms.get(i).neidoory.get(j)][rooms.get(i).neidoorx.get(j)] = new Door();
						rooms.get(i).doors++;
						rooms.get(rooms.get(i).neighbours.get(j)).doors++;
						rooms.get(i).walkable = true;
					}
				}
			}
			allreachable = true;
			for(int i = 1; i < rooms.size(); i++){
				if(!rooms.get(i).walkable){
					allreachable = false;
				}				
			}
			
			//safety first
			timeout++;
			if(timeout > 500){
				allreachable = true; //REMOVE
				System.out.println("ACHTUNG!");
			}
		}
		//doors for boss and start
		tmpArray[8][4] = new Door();
		tmpArray[3][x-11] = new Door_boss();
		tmpArray[4][x-11] = new Door_boss();
		tmpArray[5][x-11] = new Door_boss();
		
		return tmpArray;
	}

}
