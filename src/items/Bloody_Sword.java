package items;

import chars.Creatures;
import game.Reference;
import game.Visible;
import objects.Floor_bloody;

public class Bloody_Sword extends Weapon{
	
	private int sharpness;
	private boolean dragon;
	private String state;
	
	public Bloody_Sword(int rare, boolean bool){
		this.rareness = rare;
		this.value = 50 + 35*(rareness) + Reference.r.nextInt(100);;
		this.dmg = 4 * (2+rare) + Reference.r.nextInt(rareness+1);
		this.sharpness = 100;
		this.dragon = bool;
		this.state = "blutverschmiertes";
	}
	
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "es " + this.state + " Schwert";
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
				if(this.sharpness>50){
					this.sharpness--;
				}
				msg += character.getName() + " erleidet " + dmg + " Schaden durch " + this.toString() + "!";
			}else{
				msg = "Der Angriff hat verfehlt!";
			}
		}else if(obj.getType() == "Y"){
			if(this.dragon){
				user.bagpack.add(new Dragon_Sword(this.rareness));
				user.bagpack.remove(index);
				msg = "Das Drachenblut um dein Schwert verhärtet sich und hat deine Waffe verbessert!";
			}else{
				this.state = "blutrotes";
				this.dmg = 5 * (2+this.rareness) + Reference.r.nextInt(rareness+1);
				msg = "Das Blut um dein Schwert ist eingetrocknet und hat es rot gefärbt!";
			}
		}
		return msg;
	}


	@Override
	public int getDmg() {
		return this.dmg*this.sharpness/100;
	}

}
