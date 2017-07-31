package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.Settings;
import items.Items;

class Inventory implements Drawable {
	private int width = 300;
	private int height = 300;
	private int x,y;
	private int itemsPerRow = 8;
	private int columns = 8;
	private int itemHeight, itemWidth;
	private List<Items> items = new ArrayList<Items>();
	
	private BufferedImage img;
	
	Inventory() {
		// centered
		x = Settings.GAME_W/2  - width/2;
		y = Settings.GAME_H/2 - height/2;
		
		itemHeight = (height / columns) - 5;
		itemWidth  = (width / itemsPerRow) - 5;
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.green);
		g2d.fillRect(x, y, width, height);	
		
		drawItems(g2d);
	}
	
	public void addItem(Items i) {
		items.add(i);
	}
	
	private void drawItems(Graphics2D g2d) {
		int rowSpace = 5;
		int columnSpace = 5;
		
		for (int column = 1; column < columns+1; column++) {
			for (int row = 1; row < itemsPerRow+1; row++) {
				boolean notDefinded = false;
				Items item = null;
				try {
					item = items.get((column * row)-1);
				} catch(IndexOutOfBoundsException ex) {
					notDefinded = true;
				}
				
				if(!notDefinded) {
					System.out.println(item.getImage());
					try {
			    		this.img  = ImageIO.read(getClass().getResourceAsStream("/items/" + item.getImage() + ".png"));
			    	} catch(IOException e) {
			    		e.printStackTrace();
			    	}
				} else {
					try {
			    		this.img  = ImageIO.read(getClass().getResourceAsStream("/items/default.png"));
			    	} catch(IOException e) {
			    		e.printStackTrace();
			    	}
				}
				
				g2d.drawImage(img, x + (row-1)*itemWidth + rowSpace, y + (column-1)*itemHeight + columnSpace, itemWidth, itemHeight, null);
				
				rowSpace += 5;
			}
			rowSpace = 5;
			columnSpace += 5;
		}
	}

}
