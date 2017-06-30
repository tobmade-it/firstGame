package objects;

import game.Visible;

public class Door_boss extends WorldGen implements Visible{
	
	public Door_boss(){
		this.closed = false;//true;
	}

	@Override
	public boolean getvisibility() {
		return false;
	}

	@Override
	public String getType() {
		return "6";
	}
	@Override
	public boolean getIsSolid() {
		return this.closed;
	}
	
	public static boolean closed;

	public static boolean isClosed() {
		return closed;
	}

	public static void setClosed(boolean closed) {
		Door_boss.closed = closed;
	}

}
