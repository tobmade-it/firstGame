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
						tmpArray[height+i][width+j] = new Floor();

					}	
				}
				//add room to list
				if(tmpx > 2 && tmpy > 2){
					rooms.add(new Room(width, height, tmpx, tmpy));
				}
			}//end
			
		}
		
		
		//get neighbours
		for(int i = 1; i < rooms.size(); i++){
			for(int j = 1; j < rooms.size(); j++){
				int cnt = 0;
				int firstx = 0;
				int firsty = 0;
				boolean first = true;
				for(int y1 = rooms.get(i).starty-1; y1 < rooms.get(i).y+2 ; y1++){
					for(int y2 = rooms.get(j).starty-1; y2 < rooms.get(j).y+2 ; y2++){
						if(i != j && y1 == y2){
							for(int x1 = rooms.get(i).startx-1; x1 < rooms.get(i).x+2 ; x1++){
								for(int x2 = rooms.get(j).startx-1; x2 < rooms.get(j).x+2 ; x2++){
									if(x1 == x2){
										System.out.println("!"); //
										cnt++;
										if(first){
											first = false;
											firstx = x1;
											firsty = y1;
										}
									}
								}
							}
						}	
					}
				}
				if(cnt > 2){
					System.out.println(i + " " + j + " " + rooms.get(j).roomid); //
					rooms.get(i).neighbours.add(rooms.get(j).roomid);
					rooms.get(i).neidoorx.add(firstx+(cnt/2));
					rooms.get(i).neidoory.add(firsty+(cnt/2));
				}
			}
		}
		
		//Add doors
		boolean allreachable = false;
		rooms.get(1).walkable = true;
		while(!allreachable){
			
			//wähle solange nachbarn aus, bis jeder jeden erreicht
			
			for(int i = 1; i < rooms.size(); i++){
				allreachable = rooms.get(i).walkable;
			}
			allreachable = true; //REMOVE
		}
		
		return tmpArray;
	}

}
