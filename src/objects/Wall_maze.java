package objects;

import game.Visible;

public class Wall_maze extends WorldGen implements Visible{
	
	public Wall_maze(){
		super.type = "=";
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
