package chars;

<<<<<<< HEAD
import game.Visible;

public class Monster_hostile extends Mobs implements Visible{
	
	String[] name_list = {"orc" , "dark elf" , "bandit" , "BWL-student", "dragon", "evil chest"};

	@Override
	public boolean getvisibility() {
		return false;
	}

	@Override
	public String getType() {
		return "X";
	}

	@Override
	public boolean getIsSolid() {
		return false;
	}

=======
public class Monster_hostile extends Mobs{
	
	String[] name_list = {"orc" , "dark elf" , "bandit" , "BWL-student", "dragon", "evil chest"};

>>>>>>> d4c61286ebbc6120c423786c73398e8383fbe1c7
}
