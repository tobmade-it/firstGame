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
	int luck;
	public Bagpack bagpack;
	String name;
	
	public abstract void takeDmg(int hp);
	public abstract int getHP();

}
