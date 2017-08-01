package chars;

import game.Reference;
import items.Dagger;
import items.Fist;

public class Bwlstudent extends Monster_friendly{

	public Bwlstudent(int strgth) {
		
		//super(strenght);
		this.name = "BWL - Student";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 15;
		super.maxhp = 5 + strgth*7 + rnd*3;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 10;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 11*rnd+strgth*2+3+rnd;
		this.mainweapon = new Fist();
		this.gold = Reference.r.nextInt(10);
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(2);
		if(chance < 25){
			msg = "Der Student hat verfehlt!";
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
