package chars;

import game.Reference;
import game.Visible;

public class SchwarzMann extends Boss{

	public SchwarzMann(int strgth) {
		
		
		
		/*
		 *      0   0 0   0
		 *       \ /   \ /
		 *        0     0
		 *         \   /
		 *          \ /
		 *           0
		 */
		
		this.name = "SchwarzMann";
		super.defense = 0;
		super.maxhp = 7;
		super.hp = maxhp;
		super.strength = 1;
		super.intelligence = 33;
		super.luck = 42;
		super.viewdist = 3;
	}
	
	@Override
	public String attack(Creatures p){
		String msg = "";
		int chance = Reference.r.nextInt(this.luck+100);
		int crit = Reference.r.nextInt(this.luck+100);
		int atk = Reference.r.nextInt(4);
		if(crit < this.getLuck()){
			crit = 2;
			msg = "Ein kritischer Treffer! ";
		}else{
			crit = 1;
		}
		if(chance < 25){
			msg = "Ein Student betritt den Raum! Schwarzmann setzt aus!";
		}else{
			switch(atk){
				case 0:
					int dmg = crit*(this.getIntelligence()+Reference.r.nextInt(10)) *100/(100+p.getIntelligence());
					p.takeDmg(dmg);
					p.gold += dmg;
					msg += "SchwarzMann setzt Quake ein! Du erleidest " + dmg + " Schaden!";
					this.hp--;
					break;
				case 1:
					p.setStun(1);
					msg = "Autotool Aufgaben! Nur Glück und Inteligenz können dir jetzt noch helfen!";
					break;
				case 2:
					msg = "Induktion! Ein gewaltiger Strom durchfließt dich und wird immer Stärker! Du erleidest " + this.strength*2 + " Schaden!";
					p.takeDmg(this.strength*2);
					this.strength = this.strength*2;
					break;
				default:
					int dmgg = crit*(this.getStrength()+Reference.r.nextInt(5)) *100/(100+p.getDef());
					p.takeDmg(dmgg);
					msg += "Beweis deiner Stärke! Erleide "+ (dmgg) +" Schaden!";
					break;
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public void takeDmg(int hp) {
		if(this.hp - 1 >= 0 && this.hp - 1 <= this.maxhp){
			this.hp--;
		}else if(this.hp - 1 >= 0){
			this.hp = this.maxhp;
		}else{
			this.hp = 0;
		}
	}
}

