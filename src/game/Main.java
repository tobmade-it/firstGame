package game;

import Generator.Generate;

public class Main {

	public static void main(String[] args) {
		
		int x = 50;
		int y = 40;
		
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
