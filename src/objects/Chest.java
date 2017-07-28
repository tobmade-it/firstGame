package objects;

import java.util.ArrayList;
import java.util.List;

import game.Visible;
import items.Items;

public class Chest extends WorldGen implements Visible{
	
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
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "C";
	}
	@Override
	public boolean getIsSolid() {
		return true;
	}

}
