package chars;

public class Mobs extends Creatures{
	
	protected int xp;
	protected int rnd;
	
	public String attack(Creatures p){
		return "Nichts geschieht";
	}

	@Override
	public int getHP() {
		return this.hp;
	}
	
	public int getXP() {
		return this.xp;
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
}
