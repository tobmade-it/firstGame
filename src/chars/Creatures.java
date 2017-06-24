package chars;

import items.Bagpack;
import items.Weapon;
import objects.WorldGen;

public abstract class Creatures {
	
	WorldGen prepos;
	int hp;
	int maxhp;
	int strength;
	int defense;
	int intelligence;
	int viewdist;
	public Weapon mainweapon;
	int luck;
	public Bagpack bagpack;
	
	public abstract void takeDmg(int hp);
	public abstract int getHP(int hp);

}
