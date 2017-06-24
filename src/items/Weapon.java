package items;

import chars.Creatures;
import chars.Player;
import game.Visible;

public abstract class Weapon extends Items{
	
	protected double dmg;
	protected int rareness;
	protected String[] rarenesstypes = {"Rusty","Common","Rare","Epic","Legendary"};
	
	@Override
	public abstract String use(Creatures user, Items item, int index, Visible obj, Creatures character);

}
