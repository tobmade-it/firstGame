package shops;

import game.Visible;

public class Weaponary extends Shops implements Visible {
	
	public Weaponary(int n){
		super.type = "W";
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
