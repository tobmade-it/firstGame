package chars;

import game.Visible;

public class Player extends Creatures implements Visible{

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getType() {
		return "P";
	}


}
