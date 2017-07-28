package chars;

import game.Reference;

public class TonaldDrump extends Boss{

	public TonaldDrump(int strgth) {
		
		this.name = "Tonald Drump";
		super.defense = 15;
		super.maxhp = 100 + strgth*8;
		super.hp = maxhp;
		super.strength = 10;
		super.intelligence = 40;
		super.luck = 40;
		super.viewdist = 3;
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int crit = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(3);
		if(crit < this.getLuck()){
			crit = 2;
			msg = "Ein kritischer Treffer! ";
		}else{
			crit = 1;
		}
		if(chance < 25){
			msg = "Tonald twittert!";
		}else{
			switch(atk){
				case 0:
					this.strength += 10;
					msg = "Toland läd sich auf, seine Wut gegen dich erhöt sich!";
					break;
				case 1:
					p.setStun(1);
					msg = "US-Sanktionen! Du setzt eine Runde aus!";
					break;
				default:
					int dmg = crit*(this.getStrength()+Reference.r.nextInt(5)) *100/(100+p.getDef());
					p.takeDmg(dmg);
					msg += "Du wirst als Fake-News dargestellt und musst dich rechtfertigen, erleide "+ (dmg) +" Schaden!";
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
