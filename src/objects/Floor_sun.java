package objects;

import game.Reference;
import game.Visible;

public class Floor_sun extends WorldGen implements Visible{
	
	public Floor_sun(){
		super.type = "2";
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
		return false;
	}
	
}
