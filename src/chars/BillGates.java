package chars;

import game.Reference;

public class BillGates extends Boss{

	public BillGates(int strgth) {
		
		this.name = "Bill Gates";
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
			msg = "Bill Gates muss Rechnungen schreiben!";
		}else{
			switch(atk){
				case 0:
					int dmg = crit*(this.getStrength()+Reference.r.nextInt(5)) *100/(100+p.getDef());
					p.takeDmg(dmg);
					p.gold += dmg;
					msg += "Bill Gates wirft Geld auf dich! Du erleidest " + dmg + " Schaden und sammelst das Geld auf!";
					break;
				case 1:
					p.setStun(1);
					msg = "Windows Update!";
					break;
				default:
					int dmgg = crit*(this.getIntelligence()+Reference.r.nextInt(10)) *100/(100+p.getIntelligence());
					p.takeDmg(dmgg);
					msg += "Bill Gates installiert einen Virus! Du erleidest "+ (dmgg) +" Schaden! WannaCry now?";
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
