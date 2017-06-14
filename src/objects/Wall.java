package objects;

import game.Visible;

public class Wall extends WorldGen implements Visible{
	
	public Wall(){
		super.type = "w";
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


}
