package chars;

import game.Reference;
import items.Fist;

public class Bandit extends Monster_hostile{

	public Bandit(int strgth) {
		
		//super(strenght);
		this.name = "Bandit";
		this.rnd = Reference.r.nextInt(strgth);
		super.defense = 25;
		super.maxhp = 5 + strgth*10 + rnd*3;
		super.hp = maxhp;
		super.strength = 1+rnd;
		super.intelligence = 1;
		super.luck = 70;
		super.viewdist = 3;
		super.xp = 11*rnd+strgth*2+3;
		this.mainweapon = new Fist();
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(2);
		if(chance < 25){
			msg = "Der Bandit hat verfehlt!";
		}else{
			switch(atk){
				case 0:
					int steal = p.gold/20;
					p.gold -= steal;
					msg = "Der Bandit hat dir " + steal + " Gold gestohlen!";
					break;
				default:
					System.out.println(this.mainweapon.use(this, null, 0, null, p));
					//p.takeDmg(dmgamount(this.getDmg(), p.getDef()));
					//msg = "Der Bandit trifft mit seinem Dolch und verursacht "+ (dmgamount(this.getDmg(), p.getDef())) +" Schaden!";
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
