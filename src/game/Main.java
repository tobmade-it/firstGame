package game;

import Generator.Generate;
import objects.Floor;
import objects.Wall;

public class Main {

	public static void main(String[] args) {
		
		//Visible[][] gameField = new Visible[30][30];
		/*
		for(int i = 0; i < gameField.length; i++){
			for(int j = 0; j < gameField.length; i++){
				if(i == 0 || i == gameField.length-1 || j == 0 || j == gameField.length-1){
					gameField[i][j] = new Wall();
				}else{
					gameField[i][j] = new Floor();
				}
			}
		}
		*/
		
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
