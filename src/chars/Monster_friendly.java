package chars;

import game.Visible;

public class Monster_friendly extends Mobs implements Visible{
	
	String[] name_list = {"rat" , "Bill Gates" , "dog" , "cat", "old wise man", "Gandalf"};

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "F";
	}

	@Override
	public boolean getIsSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
