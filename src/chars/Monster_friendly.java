package chars;

import java.util.Arrays;

import game.Reference;
import game.Visible;
import items.Fist;
import items.Weapon;

public class Monster_friendly extends Mobs implements Visible{
	
	private String[] name_list = {"rat" , "Bill Gates" , "dog" , "cat", "old wise man", "Gandalf"};
	public Weapon mainweapon;
	
	public Monster_friendly(){
		
	}
	
	public Monster_friendly genMob(int strgth){
		int rnd = Reference.r.nextInt(5);
		switch(rnd){
		case 0:
			return new Bwlstudent(strgth*4);
		case 1: 
			return new Flattards(strgth*4);
		case 2:
			return new Jehovas(strgth*4);
		case 3:
			return new OldWiseMan(strgth*4);
		case 4:
			return new Rat(strgth*4);
		default:
			return new Rat(strgth*4);
		}
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
