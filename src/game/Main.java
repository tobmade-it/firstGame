package game;

import Generator.Generate;

public class Main {

	public static void main(String[] args) {
		
		//limit: 127! ist die liste voll, warum genau 2^7-1????, wow ich verwende ne 4fach schleife, 2^8^4 = 2^32 > Integer.Max .... aber wie kann ich das umgehen!
		int x = 70;
		int y = 55;

		
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
