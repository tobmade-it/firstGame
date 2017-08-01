package chars;

import game.Reference;
import items.Claws;
import items.Fist;

public class Rat extends Monster_friendly{

	public Rat(int strgth) {
		
		//super(strenght);
		this.name = "Ratte";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 35;
		super.maxhp = 3 + strgth*6 + rnd*3;
		super.hp = maxhp;
		super.strength = 3+rnd;
		super.intelligence = 10;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 11*rnd+strgth*2+3+rnd;
		this.mainweapon = new Claws();
		this.gold = Reference.r.nextInt(100);
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(2);
		if(chance < 25){
			msg = "Die Ratte hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					msg = this.mainweapon.use(this, null, 0, null, p);
					break;
				default:
					msg = this.mainweapon.use(this, null, 0, null, p);
					break;
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
