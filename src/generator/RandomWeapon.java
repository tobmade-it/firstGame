package generator;

import game.Reference;
import items.*;


public class RandomWeapon {
	
	int rand = Reference.r.nextInt(5);
	
	public Weapon genRanWea(){
		switch(rand){
			case 0:
				return new Sword();
			case 1:
				return new Axe();
			case 2:
				return new Hammer();
			case 3:
				return new Bow();
			default:
				return new Sword();
		}
	}

}
