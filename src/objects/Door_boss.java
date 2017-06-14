package objects;

import game.Visible;

public class Door_boss extends WorldGen implements Visible{

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

}
