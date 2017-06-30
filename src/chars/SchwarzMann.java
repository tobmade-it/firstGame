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
		
		this.name = "Schwarz Mann";
		super.defense = 1;
		super.maxhp = 7;
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
		int atk = Reference.r.nextInt(4);
		if(chance < 15){
			msg = "Ein Student betritt den Raum!";
		}else{
			switch(atk){
				case 0:
					int dmg = this.intelligence*2 - p.intelligence;
					p.takeDmg(dmg);
					p.gold += dmg;
					msg = "Schwarz Mann setzt Quake ein! Du erleidest " + dmg + " Schaden!";
					this.hp--;
					break;
				case 1:
					p.stun = 10 - ( p.intelligence + p.luck/10 );
					msg = "Autotool Aufgaben! Nur Glück und Inteligenz können dir jetzt noch helfen!";
					break;
				case 2:
					msg = "Induktion! Ein gewaltiger Strom durchfließt dich und wird immer Stärker! Du erleidest " + this.strength + " Schaden!";
					p.takeDmg(this.strength);
					this.strength++;
					break;
				default:
					p.takeDmg(this.strength-p.defense);
					msg = "Beweis deiner Stärke! Erleide "+ (p.strength) +" Schaden!";
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

