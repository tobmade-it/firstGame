package chars;

import game.Reference;
import items.Claws;
import items.Fist;

public class Dragon extends Monster_hostile{

	public Dragon(int strgth) {
		
		//super(strenght);
		this.name = "Drache";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 30;
		super.maxhp = 18 + strgth*9 + rnd*5;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 10;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 10*rnd;
		this.mainweapon = new Claws();
		this.gold = Reference.r.nextInt(300);
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(2);
		if(chance < 25){
			msg = "Der Drache hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					msg = this.mainweapon.use(this, null, 0, null, p);
					break;
				default:
					p.takeDmg(this.strength*3 *100/(100+p.defense));
					msg = "Der Drache trifft mit seinem Feueratem und verursacht "+ (this.strength*4 *100/(100+p.defense)) +" Schaden!";
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
