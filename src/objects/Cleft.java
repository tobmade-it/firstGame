package objects;

import game.Visible;

public class Cleft extends WorldGen implements Visible{
	
	public Cleft(){
		super.type = "0";
		super.visible = true;
	}

	@Override
	public boolean getvisibility() {
		return this.visible;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	@Override
	public boolean getIsSolid() {
		return true;
	}


}