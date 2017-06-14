package objects;

import game.Visible;

public class Floor_bloody extends WorldGen implements Visible{

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "b";
	}
	@Override
	public boolean getIsSolid() {
		return false;
	}

}
