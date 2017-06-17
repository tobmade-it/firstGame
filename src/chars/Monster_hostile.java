package chars;

import game.Visible;

public class Monster_hostile extends Mobs implements Visible{
	
	String[] name_list = {"orc" , "dark elf" , "bandit" , "BWL-student", "dragon", "evil chest"};

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
		return false;
	}


}
