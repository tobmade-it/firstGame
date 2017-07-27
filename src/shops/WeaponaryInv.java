package shops;

import java.util.ArrayList;
import java.util.List;

import items.Armor;
import items.Weapon;

public class WeaponaryInv extends ShopInv {
	
	private List<Weapon> inventory = new ArrayList<Weapon>();

	public WeaponaryInv(int n) {
		super.type = "W";
		this.id = n;
	}

	
}
