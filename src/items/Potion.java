package items;

import chars.Creatures;
import game.Visible;

public abstract class Potion extends Items{
	
	protected int level;
	protected String[] leveltypes = {"Winzig","Gew�hnlich","Gro�","M�chtig","Unglaublich"};

	@Override
	String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

}
