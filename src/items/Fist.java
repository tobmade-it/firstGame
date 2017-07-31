package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Fist extends Weapon implements hasImage{
	
	public Fist(){
		this.dmg = 5;
	}

	
	@Override
	public String toString() {
		return "ein Faustschlag";
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		if(character != null){
			int chance = Reference.r.nextInt(100+user.getLuck());
			int crit = Reference.r.nextInt(100+user.getLuck());
			if(chance > 20){
				if(crit < user.getLuck()){
					crit = 2;
					msg = "Ein kritischer Treffer! ";
				}else{
					crit = 1;
				}
				int dmg = crit*(user.getStrength() + this.getDmg()) *100/(100+character.getDef());
				character.takeDmg(dmg);
				msg += character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}
		return msg;
	}


	@Override
	public int getDmg() {
		return this.dmg;
	}


	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
