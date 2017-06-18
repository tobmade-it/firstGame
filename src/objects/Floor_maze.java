package objects;

import game.Reference;
import game.Visible;

public class Floor_maze extends WorldGen implements Visible{
	
	public Floor_maze(){
		super.type = " ";
		super.visible = false;
		this.walkable = false;
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

	public boolean walkable;


}
