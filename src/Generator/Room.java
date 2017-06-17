package Generator;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	Room(int sx,int sy,int x,int y){
		this.doors = 0;
		this.startx = sx;
		this.starty = sy;
		this.x = x;
		this.y = y;
		this.area = x*y;
		this.walkable = false;
		this.bossroom = false;
		this.roomid = Room.id++;
		
		for(int i = sx-1; i <= sx + x+1; i++){
			this.wallsx.add(i);
		}
		for(int i = 0; i <= y; i++){
			this.wallsx.add(sx-1);
			this.wallsx.add(sx+x+1);
		}
		for(int i = sx-1; i <= sx + x+1; i++){
			this.wallsx.add(i);
		}
		
		
		for(int i = 0; i <= x; i++){
			this.wallsy.add(sy-1);
		}
		//for(int j = 0; j < 2 ; j++){
<<<<<<< HEAD
			for(int i = sy-1; i <= sy + y+1; i++){
=======
			for(int i = sy-1; i <= sy + y + 1; i++){
>>>>>>> d4c61286ebbc6120c423786c73398e8383fbe1c7
				this.wallsy.add(i);
				this.wallsy.add(i);
			}
		//}
		for(int i = 0; i <= x; i++){
			this.wallsy.add(sy+y+1);
		}
	}
	public int doors;
	static int id = 0;
	public int roomid;
	public int startx;
	public int starty;
	public int x;
	public int y;
	public int area;
	public boolean walkable;
	public boolean bossroom;
	public List<Integer> neighbours = new ArrayList<Integer>();
	public List<Integer> neidoorx = new ArrayList<Integer>();
	public List<Integer> neidoory = new ArrayList<Integer>();
	public List<Integer> wallsx = new ArrayList<Integer>();
	public List<Integer> wallsy = new ArrayList<Integer>();

}
