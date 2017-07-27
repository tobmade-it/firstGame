package generator;

import game.Reference;
import items.*;


public class RandomWeapon {
	
	int rand = Reference.r.nextInt(1);
	
	public Weapon genRanWea(){
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
