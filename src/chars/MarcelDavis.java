package chars;

import game.Reference;

public class MarcelDavis extends Boss{

	public MarcelDavis(int strgth) {
		
		this.name = "Darcel Mavis";
		super.defense = 15;
		super.maxhp = 100 + strgth*8;
		super.hp = maxhp;
		super.strength = 5;
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
			msg = "Kundenbetreuung!";
		}else{
			switch(atk){
				case 0:
					int dmg = crit*(this.getStrength()+Reference.r.nextInt(5)) *100/(100+p.getDef());
					p.takeDmg(dmg);
					msg += "Darcel schmeißt mit Routern auf dich und du erleidest " + dmg + " Schaden!";
					break;
				case 1:
					p.setStun(1);
					msg = "Bandwidth throttling! Setze eine Runde lang aus!";
					break;
				default:
					int dmgg = crit*(this.getIntelligence()+Reference.r.nextInt(10)) *100/(100+p.getIntelligence());
					p.takeDmg(dmgg);
					msg += "1 und 1 macht "+ (dmgg) +" Schaden! Für weitere Fragen wenden sie sich an: 0180 66642069";
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

