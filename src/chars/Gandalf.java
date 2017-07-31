package chars;

import game.Reference;
import items.Dagger;
import items.Wand;

public class Gandalf extends Monster_friendly{

	public Gandalf(int strgth) {
		
		this.name = "Gandalf";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 25;
		super.maxhp = 50 + strgth*10 + rnd*3;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 1;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 11*rnd+strgth*2+3;
		this.mainweapon = new Wand();
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "YOU SHALL NOT PASS!";
		return msg;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}