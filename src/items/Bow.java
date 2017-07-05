package items;

import chars.Creatures;
import game.Visible;

public class Bow extends Weapon{
	
	private int range;
	private int platzhalter;
	
	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPlatzhalter() {
		return platzhalter;
	}

	public void setPlatzhalter(int platzhalter) {
		this.platzhalter = platzhalter;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}
