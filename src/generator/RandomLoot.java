package generator;

import game.Reference;
import items.Axe;
import items.Bow;
import items.Hammer;
import items.Items;
import items.Sword;
import items.Weapon;

public class RandomLoot {
	
	int rand = Reference.r.nextInt(5);
	
	public Items genRanLoot(){
		switch(rand){
			case 0:
				return new RandomPotion().genRanPot();
			case 1:
				return new RandomWeapon().genRanWea();
			case 2:
				return new RandomSpell().genRanSpe();
			case 3:
				return new RandomArmor().genRanArm();
			default:
				return new RandomPotion().genRanPot();
		}
	}

}
