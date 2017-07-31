package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class HealingPotion extends Potion implements hasImage{
	
	private int sharpness;
	private int heal;
	
	public HealingPotion(){
		super.level = Reference.r.nextInt(this.leveltypes.length);
		super.value = 50 + 10*(level) + Reference.r.nextInt(100);;
		heal = 10 + 5 * (2+level);
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "Nichts geschieht!";
		if(character != null){
			character.takeDmg(-this.heal);
			user.bagpack.remove(index);
			msg = character.toString() + "wurde um " + this.heal + " Lebenspunkte geheilt!";
		}else if(obj.getType() == "b"){
			user.bagpack.remove(index);
			user.bagpack.add(new DmgPotion());
			msg = "Dein Heiltrank verdirbt!";
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.leveltypes[this.level] + "er Heilungstrank";
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
