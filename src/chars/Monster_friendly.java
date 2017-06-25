package chars;

import java.util.Arrays;

import game.Visible;
import items.Fist;

public class Monster_friendly extends Mobs implements Visible{
	
	private String[] name_list = {"rat" , "Bill Gates" , "dog" , "cat", "old wise man", "Gandalf"};
	
	public Monster_friendly(int strenght){
		
		super.defense = 0;
		super.maxhp = 10;
		super.hp = maxhp;
		super.strength = 0;
		super.intelligence = 0;
		super.luck = 80;
		super.viewdist = 0;
		super.xp = 0;
		super.mainweapon = new Fist();
		
	}

	@Override
	public String toString() {
		return name_list[1];
	}

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "M";
	}

	@Override
	public boolean getIsSolid() {
		// TODO Auto-generated method stub
		return true;
	}

}
