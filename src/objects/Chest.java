package objects;

import game.Visible;

public class Chest extends WorldGen implements Visible{

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "C";
	}
	@Override
	public boolean getIsSolid() {
		return true;
	}

}
