package objects;

import java.util.ArrayList;
import java.util.List;

import game.Reference;
import game.Visible;
import generator.RandomLoot;
import items.Items;

public class Chest extends WorldGen implements Visible{
	
	private List<Items> inventory = new ArrayList<>();
	
	public Chest(){
		int rand = Reference.r.nextInt(4);
		for(int i = 0; i < rand; i++){
			inventory.add(new RandomLoot().genRanLoot());
		}
	}
	
	public void add(Items item){
		this.inventory.add(item);
	}
	
	public void remove(int index){
		this.inventory.remove(index);
	}
	
	public Items get(int index){
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
