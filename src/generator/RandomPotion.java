package generator;

import game.Reference;
import items.DmgPotion;
import items.HealingPotion;
import items.Potion;

public class RandomPotion {
	
	int rand = Reference.r.nextInt(2);
	
	public Potion genRanPot(){
		switch(rand){
			case 0:
				return new HealingPotion();
			case 1:
				return new DmgPotion();
			default:
				return new HealingPotion();
		}
	}

}
