package items;

import chars.Creatures;
import game.Visible;

public class Fist extends Weapon{
	
	public Fist(){
		this.dmg = 50;
	}

	
	@Override
	public String toString() {
		return "deine Faust";
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int dmg = (user.getStrength() + this.dmg)/10 - character.getDefense()/10;
			character.takeDmg(dmg);
			msg = character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
		}
		return msg;
	}

	
}
