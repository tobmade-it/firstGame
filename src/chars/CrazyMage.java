package chars;

import game.Reference;
import items.Fist;
import items.Wand;

public class CrazyMage extends Monster_hostile{

	public CrazyMage(int strgth) {
		
		//super(strenght);
		this.name = "Verrückter Magier";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 1;
		super.maxhp = 5 + strgth*5 + rnd*5;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 10+rnd*3;
		super.luck = 90;
		super.viewdist = 3;
		super.xp = 5*rnd;
		this.mainweapon = new Wand();
		this.gold = Reference.r.nextInt(150);
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(3);
		if(chance < 25){
			msg = "Der Verrückte Magier hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					p.stun = 1;
					msg = "Der Verückte Magier betäubt dich eine Runde lang!";
					break;
				default:
					p.takeDmg(this.intelligence-p.intelligence);
					msg = "Der Verückte Magier sprengt sich in die Luft und verursacht "+ (this.intelligence*2 *100/(p.intelligence+100)) +" Schaden!";
					this.hp = 0;
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
