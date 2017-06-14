package objects;

import game.Visible;

public class Door_boss extends WorldGen implements Visible{
	
	public Door_boss(){
		this.open = false;
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
		return false;
	}
	
	public static boolean open;

}
