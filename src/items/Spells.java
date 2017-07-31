package items;

import chars.Creatures;
import chars.Player;
import game.Visible;

public abstract class Spells extends Items{
	
	protected int dmg;
	protected int rareness;
	protected String[] rarenesstypes = {"Schwach","Alt","Selten","Episch","Ultimativ"};
	
	public abstract int getDmg();


}
