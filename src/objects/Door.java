package objects;

import game.Visible;

public class Door extends WorldGen implements Visible{

	@Override
	public boolean getvisibility() {
		return false;
	}

	@Override
	public String getType() {
		return "D";
	}

}
