package chars;

import game.Reference;
import game.Visible;

public class Boss extends Creatures  implements Visible{

	@Override
	public int getHP() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String attack(Creatures p){
		return "Nichts geschieht";
	}
	
	public Boss genBoss(int strgth){
		int rnd = Reference.r.nextInt(5);
		switch(rnd){
		case 0:
			return new BillGates(strgth*4);
		case 1: 
			return new BobRoss(strgth*4);
		case 2:
			return new MarcelDavis(strgth*4);
		case 3:
			return new TonaldDrump(strgth*4);
		default:
			return new SchwarzMann(strgth*4);
		}
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
