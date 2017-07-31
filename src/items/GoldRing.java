package items;

import game.Reference;

public class GoldRing extends Armor implements hasImage{
	
	public GoldRing(){
		this.def = 1;
		this.str = 1;
		super.value = 50 + 35*(Reference.r.nextInt(4)) + Reference.r.nextInt(100);
	}

	@Override
	public String getImage() {
		return "armor";
	}

}
