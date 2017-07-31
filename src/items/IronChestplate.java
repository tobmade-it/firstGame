package items;

import game.Reference;

public class IronChestplate extends Armor implements hasImage{
	
	public IronChestplate(){
		this.def = 25;
		this.str = -1;
		super.value = 200 + 35*(Reference.r.nextInt(4)) + Reference.r.nextInt(100);
	}

	@Override
	public String getImage() {
		return "armor";
	}

}
