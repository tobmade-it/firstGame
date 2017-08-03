package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import chars.Creatures;
import game.Settings;
import game.Visible;
import generator.Generate;
import generator.Room;

public class DungeonArea implements Drawable {
	public Graphics2D g2d = null;
	private BufferedImage img;
	private BufferedImage charImg;
	private BufferedImage shadowImg;
	private BufferedImage mobImg, mobDeadImg;
	private Visible[][] gameField = null;
	private int imgWidth  = Settings.GAME_W/16;
    private int imgHeight = Settings.GAME_H/9;
    public boolean activeFight = false;
    public String fightMessage1 = "";
    public String fightMessage2 = "";
    public int mobsKilled = 0;
    
    /** array that memorizes every position the player has seen */
    private boolean[][] visited = new boolean[Render.Y_FIELD][Render.X_FIELD];
    /** every position the player can look at the moment */
    private boolean[][] activeRoom = new boolean[Render.Y_FIELD][Render.X_FIELD];
	
	DungeonArea(Visible[][] gameField) {
		
		this.gameField = gameField;
		
    	try {
    		this.img  = ImageIO.read(getClass().getResourceAsStream("/wall-top.png"));
    		this.charImg  = ImageIO.read(getClass().getResourceAsStream("/char.png"));
    		this.shadowImg  = ImageIO.read(getClass().getResourceAsStream("/dropshadow.png"));
    		this.mobImg  = ImageIO.read(getClass().getResourceAsStream("/slimeBlock.png"));
    		this.mobDeadImg  = ImageIO.read(getClass().getResourceAsStream("/slimeBlock_dead.png"));
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
		
		// player start position
    	GUIMain.p.setX(4);
    	GUIMain.p.setY(1);
    	
    	// set start room to visited and active
    	for (int y = 0; y < 9; y++) {
    		for (int x = 0; x < 9; x++) {
    			this.activeRoom[y][x] = true;
    			this.visited[y][x] = true;
    		}
		}
	}
	
	
	@Override
	public void draw(Graphics2D g2d) {
		boolean search = true;
        
        Room neighbour1 = null;
		Room neighbour2 = null;
		
		
        
		// draw the field of view
		// complete visible game area is ...
        for(int i = 0; i < Settings.GAME_W/imgWidth; i++) {
        	for(int j = 0; j < Settings.GAME_H/imgHeight; j++) {
        		int xValue = GUIMain.p.getX()-((Settings.GAME_W/imgWidth)/2-i);
        		int yValue = GUIMain.p.getY()-((Settings.GAME_H/imgHeight)/2-j);
        		
        		if(xValue >= 0 && xValue < Render.X_FIELD && yValue >= 0 && yValue < Render.Y_FIELD) {
        			Visible v = gameField[yValue][xValue];
        			
        			// draw objects
        			if(visited[yValue][xValue])    { drawDarkObject(v, g2d, i, j); }
        			if(activeRoom[yValue][xValue]) { drawObject    (v, g2d, i, j); }
        			
        			
        			Visible vv = gameField[GUIMain.p.getY()][GUIMain.p.getX()];
        			if(vv.getType() == "D") {
        	        	if(GUIMain.p.getX() == 4 && GUIMain.p.getY() == 8) {
        	        		resetActiveRoom();
        	        		// Entrance
        	        		// is not included in the rooms array
        	        		// TODO: make it possible to go back to the entrance
        	        		for (Room room : Generate.rooms) {
        	        			if(room.starty == 9 && room.startx == 1) {
        	        				System.out.println(room.x + " ... " + room.y);
        	        				for(int l = room.startx-1; l < (room.startx + room.x+1); l++) {
										for(int m = room.starty-1; m < (room.starty + room.y+1); m++) {
    										this.visited[m][l] = true;
    										this.activeRoom[m][l] = true;
    									}
									}
        	        			}
        	        		}
        	        	} else {
        	        		BREAKOUT:		// stop the for-loop when we find the rooms
        	        		if(search) {
        	        			// find the rooms with a door at (x,y)
	    	        			for (Room room : Generate.rooms) {
	    							for(int k=0; k < room.neidoorx.size(); k++) {
	    								int xx = room.neidoorx.get(k);
	    								int yy = room.neidoory.get(k);
	    								if(xx == GUIMain.p.getX() && yy == GUIMain.p.getY()) {
	    									// end the for loop
	    									k = room.neidoorx.size();
	    									
	    									if(neighbour1 == null) {
	    										neighbour1 = room;
	    									} else {
	    										neighbour2 = room;
	    										search = false;
	    										break BREAKOUT;
	    									}
	    								
	    								}
	    							}
	    			        	}
        					}
        	        		
        	        		// make the rooms holding the same door visible
        	        		if(neighbour1 != null) {
	        	        		Room room = neighbour1;
	        	        		
	        	        		/*
	        	        		// only needed, if only one room should be visible once
	        	        		if(lastPlayerY < neighbour1.starty) {			// down
	        	        			room = neighbour1;
								} else if(lastPlayerY < neighbour2.starty) {	// up
									room = neighbour2;
								}
	        	        		
	        	        		if(lastPlayerX < neighbour1.startx) {			// right
	        	        			room = neighbour1;
								} else if(lastPlayerX < neighbour2.startx) {	// left
									room = neighbour2;
								} */
	        	        		
	        	        		resetActiveRoom();
	        	        		
	        	        		for(int l = room.startx-1; l < (room.startx + room.x +1); l++) {
									for(int m = room.starty-1; m < (room.starty + room.y+1); m++) {
										this.visited[m][l] = true;
										this.activeRoom[m][l] = true;
									}
								}

	        	        		room = neighbour2;
	        	        		for(int l = room.startx-1; l < (room.startx + room.x +1); l++) {
									for(int m = room.starty-1; m < (room.starty + room.y+1); m++) {
										this.visited[m][l] = true;
										this.activeRoom[m][l] = true;
									}
								}
        	        		}
        	        	}
        	        }
        		}
            }
        }
        
        // draw Player
        g2d.setPaint(Color.red);
        // player will be all the time in the center of the game area
        // attention: -40 offset 
        g2d.drawImage(shadowImg,(Settings.GAME_W/2)-5, (Settings.GAME_H/2)-12, 50, 50, null);
        g2d.drawImage(charImg,(Settings.GAME_W/2), (Settings.GAME_H/2)-40, 40, 60, null);
        
        // draw fight messages
        if(activeFight) {
        	g2d.setColor(Color.white);
        	g2d.fillRect(200, 600, 600, 50);
        	g2d.setColor(Color.black);
        	g2d.drawString(fightMessage1, 220, 620);
        	g2d.drawString(fightMessage2, 220, 635);
        }
        
        if(GUIMain.p.getX() == Render.X_FIELD-11 && (GUIMain.p.getY() == 3 || GUIMain.p.getY() == 4 || GUIMain.p.getY() == 5)) {
			if(mobsKilled >= 3 && GUIMain.p.getHP() > 0) {
				Font myFont = new Font (GUIMain.fontnameSpecial, 1, 27);
				g2d.setFont(myFont);
				g2d.drawString("DU HAST GEWONNEN! Herzlichen Gl�ckwunsch.", 300, 300);
			} else {
				g2d.drawString("Du musst mindestens 3 Monster gekillt haben um zu gewinnen.", 300, 300);
				g2d.drawString("Au�erdem d�rfen deine HP nicht NULL sein.", 300, 320);
			}
		}
		
	}
	
    public Visible getFieldPosition(int x, int y) {
    	return gameField[y][x];
    }
    
    public void setFieldPosition(int x, int y, Visible e) {
    	gameField[y][x] = e;
    }
    
    /**
     * Draws every object in the game by getting their type.
     * (Type "D" will not be handled here. Done in {@code doDrawing}.)
     * This method will be called if the position of the object
     * has in the {@code activeRoom} array the value {@code true}.
     * 
     * @param v			current Game Object to draw
     * @param g2d		graphic interface
     * @param i			position x to draw
     * @param j			position y to draw
     */
    private void drawObject(Visible v, Graphics2D g2d, int i, int j) {
    	Color useColor = Color.gray; // just initialize
    	
    	if(v.getType() == "w") {
			useColor = Color.white;
		} 
    	else if(v.getType() == "D" || v.getType() == "6") {
			useColor = new Color(139, 69, 19);
		}
    	else if(v.getType() == " ") {
			useColor = new Color(107,130,157);
		}
		else if(v.getType() == "C") {
			useColor = Color.orange;
		}
		else if(v.getType() == "Y") {
			useColor = Color.red;
		}
		else if(v.getType() == "^") {
			useColor = new Color(0,0,128);
		}
		else if(v.getType() == "=") {
			useColor = Color.green;
		}
		else if(v.getType() == "0") {
			useColor = Color.pink;
		}
		else if(v.getType() == "1") {
			useColor = Color.darkGray;
		}
		else if(v.getType() == "2") {
			useColor = Color.yellow;
		}
		else if(v.getType() == "W") {
			useColor = Color.CYAN;
		} 
    	if(v.getType() == "w") {
    		g2d.drawImage(img, i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgHeight, null);
    	} else if(v.getType() == "M") {
    		// draw Mobname
    		Creatures mob = (Creatures) v;
    		String mobname = mob.getName();
    		Font myFont = new Font (GUIMain.fontname, 1, 12);
            g2d.setFont(myFont);
    		g2d.drawString(mobname, i*imgWidth-GUIMain.p.getExactX()+5, j*imgHeight-GUIMain.p.getExactY()-10);;
    		// draw monster image with floor color
    		useColor = new Color(107,130,157);
    		g2d.setPaint(useColor);
	    	g2d.fillRect(i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgWidth);
    		g2d.drawImage(mobImg, i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgHeight, null);
		} else if(v.getType() == "b") {
			// draw dead monster
			g2d.setPaint(useColor);
	    	g2d.fillRect(i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgWidth);
	    	g2d.drawImage(mobDeadImg, i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgHeight, null);
		} else {
    		g2d.setPaint(useColor);
	    	g2d.fillRect(i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgWidth);
    	}
    }
    
    /**
     * Draws all these Objects, that are not visible at the moment.
     * This method will be called if the position of the object
     * has in the {@code activeRoom} array the value {@code false}.
     * 
     * @param v			current Game Object to draw
     * @param g2d		graphic interface
     * @param i			position x to draw
     * @param j			position y to draw
     */
    private void drawDarkObject(Visible v, Graphics2D g2d, int i, int j) {
    	Color useColor = Color.lightGray;
    	
    	if(v.getType() == "w") {
			useColor = Color.lightGray;
		} else  {
			useColor = Color.black;
		}
    	
    	g2d.setPaint(useColor);
    	g2d.fillRect(i*imgWidth-GUIMain.p.getExactX(), j*imgHeight-GUIMain.p.getExactY(), imgWidth, imgHeight);
    }
    
    /**
     * Resets all values of the boolean array to false.
     * Important, when the player enters a new Room.
     */
    private void resetActiveRoom() {
    	for (int y = 0; y < Render.Y_FIELD; y++) {
    		for (int x = 0; x < Render.X_FIELD; x++) {
    			this.activeRoom[y][x] = false;
    		}
		}
    }

}
