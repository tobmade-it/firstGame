package chars;

import game.Visible;

public class Boss extends Creatures  implements Visible{
	
	String[] name_list = {"John Cena", "Bob Ross", "Xenomorph", "Browser", "ImmunToPoison"};

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String attack(Creatures p){
		return "Nichts geschieht";
	}
	
	@Override
	public void takeDmg(int hp) {
		if(this.hp - hp >= 0 && this.hp - hp <= this.maxhp){
			this.hp -= hp;
		}else if(this.hp - hp >= 0){
			this.hp = this.maxhp;
		}else{
			this.hp = 0;
		}
	}

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public boolean getIsSolid() {
		// TODO Auto-generated method stub
		return true;
	}

}
