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
		int chance = Reference.r.nextInt(this.luck);
		int atk = Reference.r.nextInt(2);
		if(chance < 15){
			msg = "Bob Ross ist in Gedanken verloren!";
		}else{
			switch(atk){
				case 0:
					int dmg = this.strength-p.defense;
					p.takeDmg(dmg);
					msg = "Bob Ross beats the devil out of you! Schaden: " + dmg;
					break;
				case 1:
					p.stun = 1;
					msg = "Bob Ross malt mit dir!";
					break;
				default:
					p.takeDmg(this.strength-p.defense);
					msg = "Du erleidest "+ (this.strength - p.defense) +" Schaden! Happy little accidents!";
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
