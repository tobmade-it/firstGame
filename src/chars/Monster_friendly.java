package chars;

<<<<<<< HEAD
import game.Visible;

public class Monster_friendly extends Mobs implements Visible{
	
	String[] name_list = {"rat" , "Bill Gates" , "dog" , "cat", "old wise man", "Gandalf"};

	@Override
	public boolean getvisibility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "F";
	}

	@Override
	public boolean getIsSolid() {
		// TODO Auto-generated method stub
		return false;
	}

=======
public class Monster_friendly extends Mobs{
	
	String[] name_list = {"rat" , "Bill Gates" , "dog" , "cat", "old wise man", "Gandalf"};

>>>>>>> d4c61286ebbc6120c423786c73398e8383fbe1c7
}
