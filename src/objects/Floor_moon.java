package objects;

import game.Reference;
import game.Visible;

public class Floor_moon extends WorldGen implements Visible{
	
	public Floor_moon(){
		super.type = "1";
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