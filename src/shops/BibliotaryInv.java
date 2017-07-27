package shops;

import java.util.ArrayList;
import java.util.List;

import items.Armor;
import items.Spells;

public class BibliotaryInv extends ShopInv{
	
	public BibliotaryInv(int n) {
		super.type = "W";
		this.id = n;
	}

	private List<Spells> inventory = new ArrayList<Spells>();

}
