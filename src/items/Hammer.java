package items;

import chars.Creatures;
import game.Visible;

public class Hammer extends Weapon{
	
	private int weight;

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
