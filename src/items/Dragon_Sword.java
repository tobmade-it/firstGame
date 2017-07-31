package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Dragon_Sword extends Weapon implements hasImage{
	
	private int sharpness;
	
	public Dragon_Sword(int rare){
		this.rareness = rare;
		this.value = 350 + 100*(rareness) + Reference.r.nextInt(100);;
		this.dmg = 8 * (2+rareness) + Reference.r.nextInt(rareness+1);
		this.sharpness = 100;
	}	
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "es Drachentöter Schwert";
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int chance = Reference.r.nextInt(100+user.getLuck());
			int crit = Reference.r.nextInt(100+user.getLuck());
			if(chance > 30){
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
		return this.dmg*this.sharpness/100;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
