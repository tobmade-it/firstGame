package objects;

import game.Reference;
import game.Visible;

public class Floor extends WorldGen implements Visible{
	
	public Floor(){
		super.type = " ";
		super.visible = true;
		this.treasure = Reference.r.nextInt(100);
		if(this.treasure >= 20){
			this.treasure = 0;
		}
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

	private int treasure;


}
