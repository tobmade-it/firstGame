package chars;

import game.Reference;

public class BillGates extends Boss{

	public BillGates(int strgth) {
		
		this.name = "Bill Gates";
		super.defense = 1;
		super.maxhp = 100 + strgth*8;
		super.hp = maxhp;
		super.strength = 1;
		super.intelligence = 6;
		super.luck = 90;
		super.viewdist = 3;
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck);
		int atk = Reference.r.nextInt(3);
		if(chance < 15){
			msg = "Bill Gates muss Rechnungen schreiben!";
		}else{
			switch(atk){
				case 0:
					int dmg = this.strength-p.defense;
					p.takeDmg(dmg);
					p.gold += dmg;
					msg = "Bill Gates wirft Geld auf dich! Du erleidest " + dmg + " Schaden und Sammelst das Geld auf!";
					break;
				case 1:
					p.stun = 1;
					msg = "Windows Update!";
					break;
				default:
					p.takeDmg(this.strength-p.defense);
					msg = "Bill Gates installiert einen Virus! Du erleidest "+ (this.strength - p.defense) +" Schaden! WannaCry now?";
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
