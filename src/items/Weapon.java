package items;

import chars.Creatures;
import chars.Player;
import game.Visible;

public abstract class Weapon extends Items{
	
	protected int dmg;
	protected int rareness;
	protected String[] rarenesstypes = {"Rostig","Gew�hnlich","Selten","Episch","Legend�r"};
	
	@Override
	public abstract String use(Creatures user, Items item, int index, Visible obj, Creatures character);
	
	public abstract int getDmg();

}
