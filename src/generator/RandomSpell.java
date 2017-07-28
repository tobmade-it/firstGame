package generator;

import game.Reference;
import items.Axe;
import items.Fireball;
import items.Spells;
import items.Stun;
import items.Sword;
import items.ThundersTruck;
import items.Weapon;

public class RandomSpell {

	int rand = Reference.r.nextInt(1);
	
	public Spells genRanSpe(){
		switch(rand){
			case 0:
				return new Fireball();
			case 1:
				return new Stun();
			case 2:
				return new ThundersTruck();
			default:
				return new Fireball();
		}
	}
	
}
