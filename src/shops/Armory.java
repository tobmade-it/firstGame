package shops;

import game.Visible;

public class Armory extends Shops implements Visible {
	
	public Armory(int n){
		super.type = "A";
		this.id = n;
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
		return true;
	}

}
