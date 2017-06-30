package items;

import chars.Creatures;
import chars.Player;
import game.Reference;
import game.Visible;

public class Sword extends Weapon{
	
	private int sharpness;
	
	public Sword(){
		super.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		super.value = 200;
		super.dmg = 50;
		this.sharpness = 100;
	}
	
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + " Schwert";
	}


	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int dmg = (user.getStrength() + this.dmg*this.sharpness/100)/10 - character.getDefense()/10;
			character.takeDmg(dmg);
			msg = character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
		}
		return msg;
	}

}
