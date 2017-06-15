package objects;

import game.Visible;

public class Stairs extends WorldGen implements Visible{
	
	public Stairs(){
		super.type = "s";
		super.visible = true;
	}


	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	@Override
	public boolean getIsSolid() {
		return false;
	}


}
