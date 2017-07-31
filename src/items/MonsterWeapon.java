package items;

import chars.Creatures;
import game.Visible;

public class MonsterWeapon extends Weapon implements hasImage{

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDmg() {
		return this.dmg;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
