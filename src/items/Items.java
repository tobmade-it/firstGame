package items;

import chars.Creatures;
import chars.Player;
import game.Visible;

public abstract class Items {

	protected int value;
	
	/**
	 * @param user the char that uses an item
	 * @param item the item it is used on
	 * @param index the place in the inventory if it is a player item
	 * @param obj the object it is used on
	 * @param character the char it is used on
	 * @return
	 * a String with the message what happened
	 */
	abstract String use(Creatures user, Items item, int index, Visible obj, Creatures character);
	
}
