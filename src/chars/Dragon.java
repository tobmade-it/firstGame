package chars;

import game.Reference;
import items.Fist;

public class Dragon extends Monster_hostile{

	public Dragon(int strgth) {
		
		//super(strenght);
		this.name = "Dragon";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 3;
		super.maxhp = 5 + strgth*10 + rnd*5;
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
		int chance = Reference.r.nextInt(this.luck);
		int atk = Reference.r.nextInt(2);
		if(chance < 15){
			msg = "Der Bandit hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					int dmg = this.strength-p.defense;
					p.takeDmg(dmg);
					msg = "Der Drache hat dir " + dmg + " Schaden mit seinen Krallen verursacht!";
					break;
				default:
					p.takeDmg(this.strength-p.defense);
					msg = "Der Drache trifft mit seinem Feueratem und verursacht "+ (this.strength - p.defense) +" Schaden!";
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
