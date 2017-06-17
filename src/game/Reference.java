package game;

import java.util.Random;

public class Reference {
	
	public static Random r = new Random();

}


/*
for(int hight = 1; hight < y-1; hight++){
	for(int width = 1; width < x-1; width++){
		//start
		int tmpx = 0;
		int tmpy = 0;
		if(tmpArray[width][hight] == null){
			for(int i = 0; i < y-hight; i++){
				if(tmpArray[width][i+hight] == null){
					tmpy++;
				}else{
					break;
				}
			}
			for(int j = 0; j < x-width; j++){
				if(tmpArray[j+width][hight] == null){
					tmpx++;
				}else{
					break;
				}
			}
		}
		
		System.out.println(tmpy + " " + tmpx + "!");
		
		if(tmpx >= 10){
			tmpx = 5 + r.nextInt(tmpx/2);
		}
		if(tmpy >= 10){
			tmpy = 5 + r.nextInt(tmpy/2);
		}
		
		System.out.println(tmpy + " " + tmpx);
		
		if(tmpx < 2 || tmpy < 2){
			for(int i = 0; i < tmpy; i++){
				for(int j = 0; j < tmpx; j++){
						tmpArray[width+j][hight+i] = new Wall();
				}
			}
			tmpx = 0;
			tmpy = 0;
		}
		
		for(int i = 0; i < tmpy; i++){
			for(int j = 0; j < tmpx; j++){
				if(i == 0){// || i == tmpy-1 || j == 0 || j == tmpy-1 ){
					tmpArray[width+j][hight+i-1] = new Wall();
					if(j == 0){
						tmpArray[width+j-1][hight+i-1] = new Wall();
					}
				}
				if(i == tmpy-1){
					tmpArray[width+j][hight+i+1] = new Wall();
					if(j == 0){
						tmpArray[width+j-1][hight+i+1] = new Wall();
					}
				}
				if(j == 0){
					tmpArray[width+j-1][hight+i] = new Wall();
					if(i == tmpy-1){
						tmpArray[width+j-1][hight+i+1] = new Wall();
					}
				}
				if(j == tmpx-1){
					tmpArray[width+j+1][hight+i] = new Wall();
					if(i == 0){
						tmpArray[width+j+1][hight+i-1] = new Wall();
					}
					if(i == tmpy-1){
						tmpArray[width+j+1][hight+i+1] = new Wall();
					}
				}//else{
					tmpArray[width+j][hight+i] = new Floor();
				//}
			}
		}
		
	}//end
}
*/