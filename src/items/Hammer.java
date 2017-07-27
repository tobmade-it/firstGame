package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class Hammer extends Weapon{
	
	private int weight;
	
	public Hammer(){
		this.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		this.value = 150 + 50*(rareness) + Reference.r.nextInt(100);;
		this.dmg = 5 * (2+rareness) + Reference.r.nextInt(rareness+1);
		this.weight = 1 + 2*(rareness+3)/10;
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		
		if(character != null){
			int chance = Reference.r.nextInt(100+user.getLuck());
			int crit = Reference.r.nextInt(100+user.getLuck());
			if(chance > 40){
				if(crit < user.getLuck()){
					crit = 2;
					msg = "Ein kritischer Treffer! ";
				}else{
					crit = 1;
				}
				int dmg = crit*this.weight*(user.getStrength() + this.getDmg()) *100/(100+character.getDef());
				character.takeDmg(dmg);
				user.setStun(1);
				msg += character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "er Hammer";
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int getDmg() {
		return this.dmg;
	}

}
