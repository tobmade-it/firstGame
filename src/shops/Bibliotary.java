package shops;

import game.Visible;

public class Bibliotary extends Shops implements Visible {
	
	public Bibliotary(){
		super.type = "B";
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


}