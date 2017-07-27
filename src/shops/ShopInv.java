package shops;

import java.util.ArrayList;
import java.util.List;

import items.Items;
import items.Weapon;

public class ShopInv {
	
	String type;
	int id;
	protected List<Items> inventory = new ArrayList<Items>();
	
	public List<Items> getInv(){
		return this.inventory;
	}
	
	public void addToInv(Items e){
		this.inventory.add(e);
	}
	
}
