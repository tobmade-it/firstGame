package shops;

import game.Visible;

public class Bibliotary extends Shops implements Visible {
	
	public Bibliotary(int n){
		super.type = "B";
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
