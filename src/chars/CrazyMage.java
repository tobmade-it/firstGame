package chars;

import game.Reference;
import items.Fist;

public class CrazyMage extends Monster_hostile{

	public CrazyMage(int strgth) {
		
		//super(strenght);
		this.name = "Verr�ckter Magier";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 1;
		super.maxhp = 5 + strgth*5 + rnd*5;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 1+rnd*2;
		super.luck = 90;
		super.viewdist = 3;
		super.xp = 5*rnd;
		this.mainweapon = new Fist();
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck);
		int atk = Reference.r.nextInt(3);
		if(chance < 15){
			msg = "Der Verr�ckte Magier hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					p.stun = 1;
					msg = "Der Ver�ckte Magier bet�ubt dich eine Runde lang!";
					break;
				default:
					p.takeDmg(this.intelligence-p.intelligence);
					msg = "Der Ver�ckte Magier sprengt sich in die Luft und verursacht "+ (this.intelligence - p.intelligence) +" Schaden!";
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
