package items;

import chars.Creatures;
import game.Visible;

public class Armor extends Items{
	
	public Armor(){
		this.def = 10;
	}
	
	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	private int def;

	@Override
	String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

}
