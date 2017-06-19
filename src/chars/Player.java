package chars;

import game.Visible;

public class Player extends Creatures implements Visible {
	private int x;
	private int y;
	
	public Player(int viewdist) {
		super.viewdist = viewdist;
		super.maxhp = 100;
		super.hp = 100;
	}
	
	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getType() {
		return "P";
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public boolean getIsSolid() {
		return false;
	}
	
	public void setHP(int hp) {
		if(this.hp - hp >= 0 && this.hp - hp <= this.maxhp){
			this.hp -= hp;
		}else if(this.hp - hp >= 0){
			this.hp = this.maxhp;
		}else{
			this.hp = 0;
		}
	}
	
	public int getHP(int hp) {
		return this.hp;
	}


}
