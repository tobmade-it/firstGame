package items;

import game.Reference;

public class OsRing extends Armor implements hasImage{
	
	public OsRing(){
		this.def = 5;
		this.str = 3;
		super.value = 30 + 20*(Reference.r.nextInt(4)) + Reference.r.nextInt(100);
	}

	@Override
	public String getImage() {
		return "armor";
	}

}
