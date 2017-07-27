package chars;

import items.Armor;
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
	public int gold;
	int stun;
	public Armor armor = new Armor();
	
	public abstract void takeDmg(int hp);
	public abstract int getHP();
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxhp() {
		return maxhp;
	}
	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public int getViewdist() {
		return viewdist;
	}
	public void setViewdist(int viewdist) {
		this.viewdist = viewdist;
	}
	public int getLuck() {
		return luck;
	}
	public void setLuck(int luck) {
		this.luck = luck;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getStun() {
		return stun;
	}
	public void setStun(int stun) {
		this.stun = stun;
	}
	protected int dmgamount(int dmg, int def){
		int ret = dmg * 100 / (100 + def);
		return ret;
	}
	public int getDmg() {
		return this.strength;
	}
	public int getDef() {
		return this.defense;
	}

}
