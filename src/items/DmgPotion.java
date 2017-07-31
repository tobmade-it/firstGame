package items;

import chars.Creatures;
import game.Reference;
import game.Visible;

public class DmgPotion extends Potion implements hasImage{
	
	private int sharpness;
	private int heal;
	
	public DmgPotion(){
		super.level = Reference.r.nextInt(this.leveltypes.length);
		super.value = 50 + 10*(level) + Reference.r.nextInt(100);;
		heal = 10 + 5 * (2+level);
	}

	@Override
	public String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "";
		if(character != null){
			character.takeDmg(this.heal);
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
