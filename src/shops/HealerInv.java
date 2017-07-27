package shops;

import java.util.ArrayList;
import java.util.List;

import items.Armor;

public class HealerInv extends ShopInv {

	public HealerInv(int n) {
		super.type = "W";
		this.id = n;
	}

	private List<Armor> inventory = new ArrayList<Armor>();
	
}
