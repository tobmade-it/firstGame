package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Bow extends Weapon{
	
	private int range;
	private int tension;
	
	public Bow(){
		super.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		super.value = 20 + 80*(rareness) + Reference.r.nextInt(60);
		super.dmg = 7 * (1+rareness) + Reference.r.nextInt(rareness+1);
		this.tension = 100;
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
				this.tension--;
				msg += character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "er Bogen";
	}
	
	public int getTension() {
		return tension;
	}

	public void setTension(int n) {
		this.tension = n;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public int getDmg() {
		return this.dmg*this.tension/100;
	}

}
