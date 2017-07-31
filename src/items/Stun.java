package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Stun extends Spells{
	
	
	public Stun(){
		super.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		super.value = 270 + 35*(rareness) + Reference.r.nextInt(100);;
		super.dmg = 8 + 4 * (2+rareness) + Reference.r.nextInt(rareness+1);
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int chance = Reference.r.nextInt(100+user.getLuck());
			int crit = Reference.r.nextInt(100+user.getLuck());
			if(chance+this.dmg > 50){
				if(crit < user.getLuck()){
					crit = 2;
					msg = "Ein kritischer Treffer! ";
				}else{
					crit = 1;
				}
				character.setStun(1);
				msg += character.getName() + " wird durch " + this.toString() + " betäubt!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "e Hypnose";
	}

	@Override
	public int getDmg() {
		return this.dmg;
	}

}
