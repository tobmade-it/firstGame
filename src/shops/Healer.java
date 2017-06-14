package shops;

import game.Visible;

public class Healer extends Shops implements Visible {
	
	public Healer(){
		super.type = "H";
	}


	@Override
	public boolean getvisibility() {
		return true;
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
