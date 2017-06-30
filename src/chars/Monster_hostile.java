package chars;

import game.Reference;
import game.Visible;
import items.Fist;
import items.Weapon;

public class Monster_hostile extends Mobs implements Visible{
	
	private String[] name_list = {"orc" , "dark elf" , "bandit" , "BWL-student", "dragon", "evil chest"};
	public Weapon mainweapon;
	
	public Monster_hostile(){//int strenght){
		//genMob(strenght);
	}
	
	public Monster_hostile genMob(int strgth){
		int rnd = Reference.r.nextInt(4);
		switch(rnd){
		case 0:
			return new Bandit(strgth*4);
		case 1: 
			return new Dragon(strgth*4);
		case 2:
			return new Chest_evil(strgth*4);
		case 3:
			return new CrazyMage(strgth*4);
		default:
			return new Bandit(strgth*4);
		}
	}

	@Override
	public String toString() {
		return "Ein Monster!";
	}


	@Override
	public boolean getvisibility() {
		return false;
	}

	@Override
	public String getType() {
		return "M";
	}

	@Override
	public boolean getIsSolid() {
		return true;
	}


}
