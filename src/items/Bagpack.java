package items;

import java.util.ArrayList;
import java.util.List;

import chars.Player;
import game.Visible;

public class Bagpack extends Items{
	
	private List<Items> inventory = new ArrayList<>();
	
	void add(Items item){
		this.inventory.add(item);
	}
	
	void remove(int index){
		this.inventory.remove(index);
	}
	
	Items get(int index){
		return this.inventory.get(index);
	}

	@Override
	String use(Player player, Items item, int index, Visible obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
