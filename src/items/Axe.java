package items;

import chars.Creatures;
import game.Visible;

public class Axe extends Weapon{
	
	private int sharpness;

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSharpness() {
		return sharpness;
	}

	public void setSharpness(int sharpness) {
		this.sharpness = sharpness;
	}

}
