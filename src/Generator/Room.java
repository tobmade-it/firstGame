package Generator;

public class Room {
	
	Room(int sx,int sy,int x,int y){
		this.startx = sx;
		this.starty = sy;
		this.x = x;
		this.y = y;
		this.area = x*y;
		this.walkable = false;
		this.bossroom = false;
		this.roomid = Room.id++;
	}
	
	static int id = 0;
	public int roomid;
	public int startx;
	public int starty;
	public int x;
	public int y;
	public int area;
	public boolean walkable;
	public boolean bossroom;
	public int[] neighbours;

}
