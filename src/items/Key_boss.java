package items;

import chars.Creatures;
import chars.Player;
import game.Visible;
import objects.Door_boss;

public class Key_boss extends Items{

	@Override
	String use(Creatures user, Items item, int index, Visible obj, Creatures character) {
		String msg = "Nichts scheint zu passieren";
		if(obj != null && obj.getType() == "6"){
			Door_boss.setClosed(false);
			user.bagpack.remove(index);
			msg = "Der Schl�ssel zerf�llt zu Staub und die T�r vor dir �ffnet sich!";
		}
		return msg;
	}

}
