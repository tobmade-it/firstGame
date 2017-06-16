package Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chars.Monster_friendly;
import chars.Monster_hostile;
import game.Visible;
import objects.*;
import shops.*;

public class Generate {
	
	static List<Room> rooms = new ArrayList<Room>();
	
	public static Visible[][] genDungeon(int x, int y){ 
		Random r = new Random();
		
		Visible[][] tmpArray = new Visible[y][x];
		
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
						tmpArray[height+i][width+j] = new Floor(); //isnt needed, set layout will place floor

					}	
				}
				//set layout:
				Visible[][] layoutarr = genRoomLayout(tmpx-2,tmpy-2);
				for(int i = 0; i < tmpy-2; i++){
					for(int j = 0; j < tmpx-2; j++){
						tmpArray[height+i+1][width+j+1] = layoutarr[i][j];
					}
				}
				
				//add room to list
				if(tmpx > 1 && tmpy > 1){
					rooms.add(new Room(width, height, tmpx, tmpy));
					width+=4;//dangerous d-d-d-dangerous
				}
			}//end
			
		}
		
		//get neighbours
		for(int i = 0; i < rooms.size(); i++){
			for(int j = 0; j < rooms.size(); j++){
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
		rooms.get(rooms.size()/2).walkable = true;
		int timeout = 0;
		while(!allreachable){
			//is a neighbour walkable? if yes, build a door so we can be walkable too!
			for(int i = 1; i < rooms.size(); i++){
				if(!rooms.get(i).walkable){
					for(int j = 0; j < rooms.get(i).neighbours.size(); j++){
						if(rooms.get(rooms.get(i).neighbours.get(j)).walkable){
							tmpArray[rooms.get(i).neidoory.get(j)][rooms.get(i).neidoorx.get(j)] = new Door();
							rooms.get(i).doors++;
							rooms.get(rooms.get(i).neighbours.get(j)).doors++;
							rooms.get(i).walkable = true;
						}
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
			if(timeout > 2*rooms.size()){
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
	
	static Visible[][] genRoomLayout(int x, int y){
		Random r = new Random();
		int rand = r.nextInt(3);
		
		Visible[][] layout = genEmptyRoom(x,y);
		/*
		if(x/2 > y){
			Visible[][] layout1 = genRoomLayout(x/2,y);
			Visible[][] layout2 = genRoomLayout(x/2+x%2,y);
			for(int i = 0; i < y; i++){
				for(int j = 0; j < x/2; j++){
					layout[i][j] = layout1[i][j];
				}
			}
			for(int i = 0; i < y; i++){
				for(int j = 0; j < x/2+x%2; j++){
					layout[i][x/2+j+1] = layout2[i][j];
				}
			}
			return layout;
		}
		
		if(y/2 > x){
			Visible[][] layout1 = genRoomLayout(x,y/2);
			Visible[][] layout2 = genRoomLayout(x,y/2+y%2);
			for(int i = 0; i < y/2; i++){
				for(int j = 0; j < x; j++){
					layout[i][j] = layout1[i][j];
				}
			}
			for(int i = 0; i < y/2+y%2; i++){
				for(int j = 0; j < x; j++){
					layout[i+y/2+1][j] = layout2[i][j];
				}
			}
			return layout;
		}
		*/
		if(x > 2 && y > 2){
			if(y > 14 && x > 14){
				switch(rand){
					case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 1: 
							layout[0][x-1] = new Floor_spikes();
							layout[0][0] = new Floor_spikes();
							layout[y-1][0] = new Floor_spikes();
							layout[y-1][x-1] = new Floor_spikes();
							return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					default:
						return layout;
				}
			}else if(y > 12 && x > 12){
				switch(rand){
					case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					default:
						return layout;
				}	
			}else if(y > 10 && x > 10){
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				default:
					return layout;
			}	
			}else if(y > 8 && x > 8){
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 1:	
					int ranposx = 1 + r.nextInt(x-2);
					int ranposy = 1 + r.nextInt(y-2);
					layout[ranposy][0] = new Monster_hostile();
					layout[y-1][ranposx] = new Monster_hostile();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 2:	
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				default:
					return layout;
			}	
			}else if(y > 6 && x > 6){
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 1: 
						for(int i = 0; i < x; i++){
							layout[0][i] = new Wall();
							layout[y-1][i] = new Wall();
						}
						for(int i = 0; i < y; i++){
							layout[i][0] = new Wall();
							layout[i][x-1] = new Wall();
						}
						layout[y/2][0] = new Door();
						layout[y/2][x-1] = new Door();
						layout[0][x/2] = new Door();
						layout[y-1][x/2] = new Door();
						return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				default:
						return layout;
			}	
			}else if(y > 4 && x > 4){
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 1:
						int ranposx = 1 + r.nextInt(x-2);
						int ranposy = 1 + r.nextInt(y-2);
						layout[ranposy][ranposx] = new Monster_hostile();
						return layout;
				default:
					return layout;
			}	
			}else{
				switch(rand){
				case 0: layout[y/2][x/2] = new RandomObj().genRanObj();
						return layout;
				case 1: 
						layout[y/2][x/2] = new Chest();
						layout[y/2+1][x/2+1] = new Wall();
						layout[y/2-1][x/2-1] = new Wall();
						layout[y/2-1][x/2+1] = new Wall();
						layout[y/2+1][x/2-1] = new Wall();
						return layout;
				case 2: 
					layout[y/2][x/2] = new Monster_friendly();
					layout[y/2][x/2+(x%2-1)] = new Fire();
					return layout;
						
				default:
					return genEmptyRoom(x,y);
				}
			}
		}else{
			return genEmptyRoom(x,y);
		}
		
		//return new Visible[0][0];
	}
	
	static Visible[][] genEmptyRoom(int x, int y){
		if(x>0 && y > 0){
			Visible[][] layout = new Visible[y][x];
			for(int i = 0; i < y; i++){
				for(int j = 0; j < x; j++){
					layout[i][j] = new Floor();
				}
			}
			return layout;
		}else{
			Visible[][] layout = new Visible[0][0];
			return layout;
		}
	}
	
	static Visible[][] mergeRooms(Visible[][] a, Visible[][] b, int x, int y){
		Visible[][] layout = a;
		for(int i = 0; i < y-2; i++){
			for(int j = 0; j < x-2; j++){
				layout[i+1][j+1] = b[i][j];
			}
		}
		return layout;
	}

}
