package chars;

import game.Visible;

public class Monster_hostile extends Mobs implements Visible{
	
	private String[] name_list = {"orc" , "dark elf" , "bandit" , "BWL-student", "dragon", "evil chest"};
	private int rnd;
	
	public Monster_hostile(int strenght){
		
		this.rnd = 0;
		super.defense = 0;
		super.maxhp = 0;
		super.hp = maxhp;
		super.strength = 0;
		super.intelligence = 0;
		super.luck = 80;
		super.viewdist = 0;
		super.xp = 0;
		super.mainweapon = null;
		
	}

	@Override
	public String toString() {
		return name_list[1];
	}


	@Override
	public boolean getvisibility() {
		return false;
	}

	@Override
	public String getType() {
		return "X";
	}

	@Override
	public boolean getIsSolid() {
		return true;
	}


}
