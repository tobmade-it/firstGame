package chars;

import game.Visible;
import items.Bagpack;
import items.Fist;
import items.Sword;
import items.Weapon;

public class Player extends Creatures implements Visible {
	private int x;
	private int y;
	private int viewdirection; //2 unten 1 links 0 oben 3 rechts
	public Weapon mainweapon;
	private int level;
	private int xp;
	

	public Player(int viewdist) {
		super.viewdist = viewdist;
		super.maxhp = 200;
		super.hp = this.maxhp;
		this.strength = 50;
		super.bagpack = new Bagpack();
		this.mainweapon = new Sword();
		this.viewdirection = 2;
		super.gold = 1000;
		this.level = 1;
		this.xp = 0;
	}
	
	@Override
	public String toString() {
		return "Player";
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
	public int getHP() {
		return this.hp;
	}
	
	public int getMaxHP() {
		return this.maxhp;
	}
	
	public int getViewdirection() {
		return viewdirection;
	}

	public void setViewdirection(int viewdirection) {
		this.viewdirection = viewdirection;
	}

}
