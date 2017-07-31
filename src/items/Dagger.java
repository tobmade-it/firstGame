package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Dagger extends Weapon implements hasImage{
	
	private int sharpness;
	
	public Dagger(){
		super.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		super.value = 80 + 15*(rareness) + Reference.r.nextInt(100);;
		super.dmg = 3 * (2+rareness) + Reference.r.nextInt(rareness+1);
		this.sharpness = 100;
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int chance = Reference.r.nextInt(100+user.getLuck());
			int crit = Reference.r.nextInt(100+user.getLuck());
			if(chance > 25){
				if(crit < user.getLuck()){
					crit = 2;
					msg = "Ein kritischer Treffer! ";
				}else{
					crit = 1;
				}
				int dmg = crit*(user.getStrength() + this.getDmg()) *100/(100+character.getDef());
				character.takeDmg(dmg);
				if(this.sharpness>50){
					this.sharpness--;
				}
				msg += character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "er Dolch";
	}

	public int getSharpness() {
		return sharpness;
	}

	public void setSharpness(int sharpness) {
		this.sharpness = sharpness;
	}

	@Override
	public int getDmg() {
		return this.dmg*this.sharpness/100;
	}
	
	@Override
	public String getImage() {
		return "Dagger";
	}

}
