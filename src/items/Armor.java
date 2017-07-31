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

	protected int def;
	protected int str;

	@Override
	String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getBuff(){
		return this.str;
	}
}
