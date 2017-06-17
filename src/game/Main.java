package game;

import Generator.Generate;

public class Main {

	public static void main(String[] args) {
		
		//limit: 127! warum? keine ahnung, fehler in generator klasse, stack overflow? aber kein fehler?!
		int x = 100;
		int y = 100;

		
		Visible[][] gameField = Generate.genDungeon(x, y);
		
		//test
		for(int i = 0; i < y; i++){
			for(int j = 0; j <x; j++){
				if(gameField[i][j] != null){
					System.out.print(gameField[i][j].getType() + " ");
				}else{
					System.out.print("! ");
				}
			}
			System.out.println();
		}		

	}

}
