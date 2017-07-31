package items;

import chars.Creatures;
import chars.Player;
import game.Reference;
import game.Visible;
import objects.Floor_bloody;

public class Sword extends Weapon{
	
	private int sharpness;
	
	public Sword(){
		this.rareness = Reference.r.nextInt(this.rarenesstypes.length);
		this.value = 150 + 50*(rareness) + Reference.r.nextInt(100);;
		this.dmg = 5 * (2+rareness) + Reference.r.nextInt(rareness+1);
		this.sharpness = 100;
	}
	
	
	@Override
	public String toString() {
		return this.rarenesstypes[this.rareness] + "es Schwert";
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
		}else if(obj.getType() == "b"){
			user.bagpack.add(new Bloody_Sword(this.rareness, ((Floor_bloody)obj).getDragon()));
			user.bagpack.remove(index);
			msg = "Dein Schwert ist jetzt voller Blut, warum zur Hölle solltest du das wollen?!";
		}
		return msg;
	}


	@Override
	public int getDmg() {
		return this.dmg*this.sharpness/100;
	}


	@Override
	public String getImage() {
		return null;
	}

}
