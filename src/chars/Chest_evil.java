package chars;

import game.Reference;
import items.Fist;
import items.Trash;

public class Chest_evil extends Monster_hostile{

	public Chest_evil(int strgth) {
		
		//super(strenght);
		this.name = "Kiste";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 30;
		super.maxhp = 5 + strgth*10 + rnd*5;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 1;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 10*rnd;
		this.mainweapon = new Trash();
		this.gold = Reference.r.nextInt(400);
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(2);
		if(chance < 25){
			msg = "Der Kasten hat verfehlt!";
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
