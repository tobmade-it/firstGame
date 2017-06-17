package objects;

import game.Visible;

public class Fire extends WorldGen implements Visible{
	
	public Fire(){
		super.type = "Y";
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
