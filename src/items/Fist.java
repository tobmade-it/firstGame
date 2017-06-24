package items;

import chars.Creatures;
import game.Visible;

public class Fist extends Weapon{
	
	public Fist(){
		this.dmg = 5;
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		character.takeDmg((int) this.dmg);
		return character.toString() + " erlitt 5 Schaden!";
	}

}
