package chars;

import game.Reference;
import items.Fist;

public class Bandit extends Monster_hostile{

	public Bandit(int strenght) {
		
		//super(strenght);
		this.name = "Bandit";
		this.rnd = Reference.r.nextInt(strenght);
		super.defense = 3;
		super.maxhp = 5 + rnd*5;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 1;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 10*rnd;
		this.mainweapon = new Fist();
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(luck);
		if(chance < 20){
			msg = "Der Bandit hat verfehlt!";
		}else{
			p.takeDmg(this.strength-p.defense);
			msg = "Der Bandit trifft mit seinem Dolch und verursacht "+ (this.strength - p.defense) +" Schaden!";
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
