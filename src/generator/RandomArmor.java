package generator;

import game.Reference;
import items.Axe;
import items.Sword;
import items.Weapon;

public class RandomArmor {
	
	int rand = Reference.r.nextInt(1);
	
	public Weapon genRanArm(){
		switch(rand){
			case 0:
				return new Sword();
			case 1:
				return new Axe();
			default:
				return new Sword();
		}
	}

}
