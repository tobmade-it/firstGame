package generator;

import game.Reference;
import game.Visible;
import objects.*;

public class RandomObj implements Visible {
	
	int rand = Reference.r.nextInt(5);
	
	public Visible genRanObj(){
		switch(rand){
			case 0:
				return new Floor_spikes();
			case 1:
				return new Wall();
			case 2:
				return new Fire();
			case 3:
				return new Chest();
			case 4:
				return new Floor();
			default:
				return new Floor();
		}
	}

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getIsSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
