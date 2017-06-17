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
								//tmpArray[height+i][width+j] = new Wall();
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
	
	//returns a room[y][x] with random generated layout
	static Visible[][] genRoomLayout(int x, int y){
		Random r = new Random();
		int rand = 0;
		
		Visible[][] layout = genEmptyRoom(x,y);
		
		if(x > 2 && y > 2){
			if(y > 14 && x > 14){
				rand = r.nextInt(4);
				switch(rand){
					case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 1: 
							layout[0][x-1] = new Floor_spikes();
							layout[0][0] = new Floor_spikes();
							layout[y-1][0] = new Floor_spikes();
							layout[y-1][x-1] = new Floor_spikes();
							return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 2: return genLabyrinth(x, y);
					case 3: 
						layout[0][0] = new Floor_spikes();
						layout[0][1] = new Floor_spikes();
						layout[1][0] = new Floor_spikes();
						layout[0][x-1] = new Floor_spikes();
						layout[1][x-1] = new Floor_spikes();
						layout[0][x-2] = new Floor_spikes();
						layout[y-1][0] = new Floor_spikes();
						layout[y-1][1] = new Floor_spikes();
						layout[y-2][0] = new Floor_spikes();
						layout[y-2][x-1] = new Floor_spikes();
						layout[y-1][x-1] = new Floor_spikes();
						layout[y-1][x-2] = new Floor_spikes();
						return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					default:
						return layout;
				}
			}else if(y > 12 && x > 12){
				rand = r.nextInt(4);
				switch(rand){
					case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 1: 
						for(int i = 1; i < y-1; i++){
							for(int j = 1; j < x-1; j++){
								layout[i][j] = new Cleft();
							}
						}
						for(int j = 1; j < x-1; j++){
							layout[y/2][j] = new Floor();
						}
						for(int j = 1; j < y-1; j++){
							layout[j][x/2] = new Floor();
						}
						for(int i = 0; i < 3; i++){
							layout[y/2-1][x/2-1+i%3] = new Floor();
							layout[y/2][x/2-1+i%3] = new Floor();
							layout[y/2+1][x/2-1+i%3] = new Floor();
						}
						layout[y/2][x/2] = new Monster_hostile();//new RandomObj().genRanObj();
						return layout;
					case 2: 
						layout[0][0] = new Floor_spikes();
						layout[0][1] = new Floor_spikes();
						layout[1][0] = new Floor_spikes();
						layout[0][x-1] = new Floor_spikes();
						layout[1][x-1] = new Floor_spikes();
						layout[0][x-2] = new Floor_spikes();
						layout[y-1][0] = new Floor_spikes();
						layout[y-1][1] = new Floor_spikes();
						layout[y-2][0] = new Floor_spikes();
						layout[y-2][x-1] = new Floor_spikes();
						layout[y-1][x-1] = new Floor_spikes();
						layout[y-1][x-2] = new Floor_spikes();
						return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 3: 
						layout[0][x-1] = new Floor_spikes();
						layout[0][0] = new Floor_spikes();
						layout[y-1][0] = new Floor_spikes();
						layout[y-1][x-1] = new Floor_spikes();
						return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					default:
						return layout;
				}	
			}else if(y > 10 && x > 10){
				rand = r.nextInt(4);
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 1: 
					layout[0][0] = new Floor_spikes();
					layout[0][1] = new Floor_spikes();
					layout[1][0] = new Floor_spikes();
					layout[0][x-1] = new Floor_spikes();
					layout[1][x-1] = new Floor_spikes();
					layout[0][x-2] = new Floor_spikes();
					layout[y-1][0] = new Floor_spikes();
					layout[y-1][1] = new Floor_spikes();
					layout[y-2][0] = new Floor_spikes();
					layout[y-2][x-1] = new Floor_spikes();
					layout[y-1][x-1] = new Floor_spikes();
					layout[y-1][x-2] = new Floor_spikes();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 2: 
					layout[0][x-1] = new Floor_spikes();
					layout[0][0] = new Floor_spikes();
					layout[y-1][0] = new Floor_spikes();
					layout[y-1][x-1] = new Floor_spikes();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 3: 
					layout[0][x-1] = new Fire();
					layout[0][0] = new Fire();
					layout[y-1][0] = new Fire();
					layout[y-1][x-1] = new Fire();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				default:
					return layout;
			}	
			}else if(y > 8 && x > 8){
				rand = r.nextInt(5);
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
				case 3: 
					for(int i = 1; i < y-1; i++){
						for(int j = 1; j < x-1; j++){
							layout[i][j] = new Cleft();
						}
					}
					for(int j = 1; j < x-1; j++){
						layout[y/2][j] = new Floor();
					}
					for(int j = 1; j < y-1; j++){
						layout[j][x/2] = new Floor();
					}
					for(int i = 0; i < 3; i++){
						layout[y/2-1][x/2-1+i%3] = new Floor();
						layout[y/2][x/2-1+i%3] = new Floor();
						layout[y/2+1][x/2-1+i%3] = new Floor();
					}
					layout[y/2][x/2] = new Monster_hostile();//new RandomObj().genRanObj();
					return layout;
				case 4:
					for(int i = 3; i < x-3; i++){
						layout[1][i] = new Cleft();
						layout[y-2][i] = new Cleft();
					}
					for(int i = 3; i < y-3; i++){
						layout[i][1] = new Cleft();
						layout[i][x-2] = new Cleft();
					}
					
					for(int i = 4; i < x-4; i++){
						layout[2][i] = new Cleft();
						layout[y-3][i] = new Cleft();
					}
					for(int i = 4; i < y-4; i++){
						layout[i][2] = new Cleft();
						layout[i][x-3] = new Cleft();
					}
					layout[y/2][x/2] = new Monster_hostile();
					return layout;
				default:
					return layout;
			}	
			}else if(y > 6 && x > 6){
				rand = r.nextInt(7);
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
					case 2:
						layout[0][x-1] = new Floor_spikes();
						layout[y-1][x-1] = new Floor_spikes();
						layout[y-1][0] = new Floor_spikes();
						layout[0][0] = new Floor_spikes();
						return layout;
					case 3:
						return genLabyrinth(x, y);
					case 4: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
					case 5: 				
						for(int i = 0; i < 2; i++){
							int ranposx = 1 + r.nextInt(x-2);
							int ranposy = 1 + r.nextInt(y-2);
							layout[ranposy][ranposx] = new Monster_hostile();
						}
						return layout;
					case 6:
						for(int i = 2; i < x-2; i++){
							layout[2][i] = new Wall();
							layout[y-3][i] = new Wall();
						}
						for(int i = 2; i < y-2; i++){
							layout[i][2] = new Wall();
							layout[i][x-3] = new Wall();
						}
						layout[y-3][x/2] = new Door();
						layout[3][x/2] = new Chest();
						layout[y-2][x/2] = new Monster_friendly(); //new Gandalf();
						return layout;
					default:
						return layout;
				}	
			}else if(y > 4 && x > 4){
				rand = r.nextInt(7);
				switch(rand){
				case 0: return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 1:
						int ranposx = 1 + r.nextInt(x-2);
						int ranposy = 1 + r.nextInt(y-2);
						layout[ranposy][ranposx] = new Monster_hostile();
						return layout;
				case 2: 
					for(int i = 0; i < y; i++){
						layout[i][0] = new Floor_spikes();
						layout[i][x-1] = new Floor_spikes();
					}
					for(int i = 0; i < x; i++){
						layout[0][i] = new Floor_spikes();
					}
					for(int i = x-1; i > 1; i--){
						layout[y-1][i] = new Floor_spikes();
					}
					for(int i = y-1; i > 1; i--){
						layout[i][2] = new Floor_spikes();
					}
					for(int i = 2; i < x-2; i++){
						layout[2][i] = new Floor_spikes();
					}
					layout[y-2][3] = new Chest();
					return layout;
				case 3:
					layout[0][x-1] = new Floor_spikes();
					layout[y-1][x-1] = new Floor_spikes();
					layout[y-1][0] = new Floor_spikes();
					layout[0][0] = new Floor_spikes();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 4:
					for(int i = 0; i < x; i++){
						if(i%2 == 1){
							layout[0][i] = new Floor_spikes();
							layout[y-1][i] = new Floor_spikes();
						}
					}
					for(int i = 0; i < y; i++){
						if(i%2 == 1){
							layout[i][0] = new Floor_spikes();
							layout[i][x-1] = new Floor_spikes();
						}
					}
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				case 5:
					for(int j = 1; j < x-1; j++){
						layout[y/2][j] = new Cleft();
					}
					for(int j = 1; j < y-1; j++){
						layout[j][x/2] = new Cleft();
					}
					return layout;
				case 6: 					
					layout[0][x-1] = new Floor_spikes();
					layout[0][0] = new Floor_spikes();
					layout[y-1][0] = new Floor_spikes();
					layout[y-1][x-1] = new Floor_spikes();
					return mergeRooms(layout, genRoomLayout(x-2,y-2),x,y);
				default:
					return layout;
			}	
			}else{
				rand = r.nextInt(7);
				switch(rand){
					case 0: layout[y/2][x/2] = new RandomObj().genRanObj();
						return layout;
					case 1: 
						layout[y/2][x/2] = new Monster_hostile();
						layout[y/2+1][x/2+1] = new Wall();
						layout[y/2-1][x/2-1] = new Wall();
						layout[y/2-1][x/2+1] = new Wall();
						layout[y/2+1][x/2-1] = new Wall();
						return layout;
					case 2: 
						layout[y/2][x/2] = new Monster_friendly();
						layout[y/2][x/2+(x%2-1)] = new Fire();
						return layout;
					case 3:
						layout[0][x-1] = new Floor_spikes();
						layout[y-1][x-1] = new Floor_spikes();
						layout[y-1][0] = new Floor_spikes();
						layout[0][0] = new Floor_spikes();
						return layout;
					case 4:
						layout[y/2][x/2] = new Monster_hostile();
						return layout;
					case 5:
						layout[y/2][x/2] = new Monster_friendly();
						return layout;
					case 6:
						layout[y/2][x/2] = new Chest();
						layout[y/2+1][x/2] = new Floor_spikes();
						layout[y/2][x/2+1] = new Floor_spikes();
						layout[y/2][x/2-1] = new Floor_spikes();
						layout[y/2-1][x/2] = new Floor_spikes();
						return layout;
					default:
						return genEmptyRoom(x,y);
				}
			}
		}else{
			return genEmptyRoom(x,y);
		}
	}
	
	//generates Room[y][x] with all floor
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
	
	//merges Room of size [y][x] with room of size [y-2][x-2]
	static Visible[][] mergeRooms(Visible[][] a, Visible[][] b, int x, int y){
		Visible[][] layout = a;
		for(int i = 0; i < y-2; i++){
			for(int j = 0; j < x-2; j++){
				layout[i+1][j+1] = b[i][j];
			}
		}
		return layout;
	}
	
	//generates a Room[y][x] with a labyrinth+chest in it
	static Visible[][] genLabyrinth(int x, int y){
		Visible[][] labyrinth = new Visible[y][x];
		List<Floor_maze> mazefloor = new ArrayList<Floor_maze>();
		for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				if(i%2 == 0 || j%2 == 0){
					labyrinth[i][j] = new Wall_maze();
				}else{
					labyrinth[i][j] = new Floor_maze();
					mazefloor.add((Floor_maze) labyrinth[i][j]);
				}
			}
		}
		//((Floor_maze) labyrinth[y/2+(y%2-1)][x/2+(x%2-1)]).walkable = true;
		mazefloor.get(mazefloor.size()-1).walkable = true;
		mazefloor.get(0).walkable = true;

		boolean search = true;
		int maxt = 0;
		int chestx = 0;
		int chesty = 0;
		while(search){
			for(int dt = 0; dt < 3; dt++){
				Random r = new Random();
				int j = 2*r.nextInt(x/2) +1;
				int i = 2*r.nextInt(y/2) +1;
				if(!((Floor_maze) labyrinth[i][j]).walkable){
					for(int c = 0; c < 2; c++){
						for(int d = 0; d < 2; d++){
							int xm = (int) (j + Math.pow(-1, d)*(c%2) *2);
							int ym = (int) (i + Math.pow(-1, d)*((c+1)%2) *2);
							if(xm > 0 && xm < x && ym > 0 && ym < y){
								if(((Floor_maze) labyrinth[ym][xm]).walkable && !((Floor_maze) labyrinth[i][j]).walkable){
									((Floor_maze) labyrinth[i][j]).walkable = true;
									chestx = j;
									chesty = i;
									labyrinth[(int) (i+Math.pow(-1, d)*(c+1)%2)][(int) (j+Math.pow(-1, d)*c%2)] = new Floor_maze();
								}
							}
						}
					}
				}
			}
			search = false;
			for(int t = 0; t < mazefloor.size(); t++){
				if(!mazefloor.get(t).walkable){
					search = true;
				}
			}
			if(maxt > 5000){
				search = false;
				System.out.println("Achtung!");
			}
			maxt++;
		}
		labyrinth[chesty][chestx] = new Chest();
		labyrinth[0][1] = new Floor_maze();
		labyrinth[y-1][x-2] = new Floor_maze();
		
		return labyrinth;
	}

}
