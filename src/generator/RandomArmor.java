package generator;

import game.Reference;
import items.Armor;
import items.IronChestplate;


public class RandomArmor {
	
	int rand = Reference.r.nextInt(1);
	
	public Armor genRanArm(){
		switch(rand){
			case 0:
				return new IronChestplate();
			case 1:
				return new IronChestplate();
			default:
				return new IronChestplate();
		}
	}

}
