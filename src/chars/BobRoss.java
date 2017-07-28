package chars;

import game.Reference;
import items.Fist;

public class BobRoss extends Boss{

	public BobRoss(int strgth) {
		
		this.name = "Bob Ross";
		super.defense = 2;
		super.maxhp = 100 + strgth*10;
		super.hp = maxhp;
		super.strength = 1;
		super.intelligence = 5;
		super.luck = 90;
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
			msg = "Bob Ross ist in Gedanken verloren!";
		}else{
			switch(atk){
				case 0:
					int dmg = crit*(this.getStrength()+Reference.r.nextInt(5)) *100/(100+p.getDef());
					p.takeDmg(dmg);
					msg += "Bob beats the devil out of you! Schaden: " + dmg;
					break;
				case 1:
					p.setStun(1);
					msg = "Bobs Charm ist überwältigend, du setzt eine Runde aus!!";
					break;
				default:
					int dmgg = crit*(this.getIntelligence()+Reference.r.nextInt(10)) *100/(100+p.getIntelligence());
					p.takeDmg(dmgg);
					msg += "Du erleidest "+ (dmgg) +" Schaden! Happy little accidents!";
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
