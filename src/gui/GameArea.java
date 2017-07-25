package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import chars.Player;
import game.Settings;
import game.Visible;
import generator.Generate;
import generator.Room;

/**
 * Drawing Class
 * In this class every object will be drawn to the Window.
 * The doDrawing() function does all the magic and is called
 * as often as the FPS is set in 'Settings' class.
 */
@SuppressWarnings("serial")
public class GameArea extends JPanel implements ActionListener {
    private Timer timer;
    private Visible[][] gameField = null;
    private BufferedImage img;
    private final int DELAY = 1000/Settings.FPS; // FPS of the game
    private int imgWidth  = Settings.GAME_W/16;
    private int imgHeight = Settings.GAME_H/9;
    
    /** array that memorizes every position the player has seen */
    private boolean[][] visited = new boolean[Render.Y_FIELD][Render.X_FIELD];
    /** every position the player can look at the moment */
    private boolean[][] activeRoom = new boolean[Render.Y_FIELD][Render.X_FIELD];
    
    // coordinates to compare with the current coordinates
    // to see if a player enters a new room
    public int lastPlayerX;
    public int lastPlayerY;
    public Player p = new Player(20);
    
    public GameArea(Visible[][] gameField) {
    	this.setBackground(Settings.BACKGROUND);
    	this.gameField = gameField;
    	
    	try {
    		this.img  = ImageIO.read(getClass().getResourceAsStream("/wall4.png"));
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	// player start position
    	this.p.setX(4);
    	this.p.setY(1);
    	
    	// set start room to visited and active
    	for (int y = 0; y < 9; y++) {
    		for (int x = 0; x < 9; x++) {
    			this.activeRoom[y][x] = true;
    			this.visited[y][x] = true;
    		}
		}
    	
        initTimer();
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
    	else if(v.getType() == " ") {
			useColor = Color.blue;
		}
		else if(v.getType() == "C") {
			useColor = Color.orange;
		}
		else if(v.getType() == "Y") {
			useColor = Color.red;
		}
		else if(v.getType() == "^") {
			useColor = Color.gray;
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
		else if(v.getType() == "b") {
			useColor = Color.magenta;
		}
    	if(useColor != Color.white) {
	    	g2d.setPaint(useColor);
	    	g2d.fillRect(i*imgWidth, j*imgHeight, imgWidth, imgWidth);
    	} else {
    		g2d.drawImage(img, i*imgWidth, j*imgHeight, imgWidth, imgHeight, null);
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
    	g2d.fillRect(i*imgWidth, j*imgHeight, imgWidth, imgHeight);
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

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    /**
     * All the magic happens here.
     * The whole window is filled with drawings, defined here.
     * 
     * @param g		general Graphics object
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // border around the game area
        g2d.setPaint(Color.white);
        g2d.drawRect(0, 0, Settings.GAME_W, Settings.GAME_H);
        
        // draw help bar
        g2d.setPaint(Color.red);
        g2d.fillRect(0, 600, p.getMaxHP()*7, 60);
        g2d.setPaint(Color.green);
        g2d.fillRect(0, 600, p.getHP()*7, 60);  
        
        int x = Render.X_FIELD;
        int y = Render.Y_FIELD;
        
        // some prompt for debugging
        g2d.drawString("x: " + Integer.toString(p.getX()), 800, 20);
        g2d.drawString("y: " + Integer.toString(p.getY()), 800, 40);
        g2d.drawString("x: " + Integer.toString(this.lastPlayerX), 800, 60);
        g2d.drawString("y: " + Integer.toString(this.lastPlayerY), 800, 80);
        
        boolean search = true;
        
        Room neighbour1 = null;
		Room neighbour2 = null;
        
		// draw the field of view
		// complete visible game area is ...
        for(int i = 0; i < Settings.GAME_W/imgWidth; i++) {
        	for(int j = 0; j < Settings.GAME_H/imgHeight; j++) {
        		int xValue = p.getX()-((Settings.GAME_W/imgWidth)/2-i);
        		int yValue = p.getY()-((Settings.GAME_H/imgHeight)/2-j);
        		
        		if(xValue >= 0 && xValue < Render.X_FIELD && yValue >= 0 && yValue < Render.Y_FIELD) {
        			Visible v = gameField[yValue][xValue];
        			
        			/* write function that creates this array with error handling
        			 * Visible[] visibles = {
        				gameField[yValue+1][xValue],
        				gameField[yValue-1][xValue],
        				gameField[yValue][xValue],
        				gameField[yValue][xValue+1],
        				gameField[yValue][xValue-1]
        			};*/
        			
        			// draw objects
        			if(visited[yValue][xValue])    { drawDarkObject(v, g2d, i, j); }
        			if(activeRoom[yValue][xValue]) { drawObject    (v, g2d, i, j); }
        			
        			
        			Visible vv = gameField[p.getY()][p.getX()];
        			if(vv.getType() == "D") {
        	        	if(p.getX() == 4 && p.getY() == 8) {
        	        		resetActiveRoom();
        	        		// Entrance
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
        	        			// find the rooms that hold both the Door the player is at
	    	        			for (Room room : Generate.rooms) {
	    							for(int k=0; k < room.neidoorx.size(); k++) {
	    								int xx = room.neidoorx.get(k);
	    								int yy = room.neidoory.get(k);
	    								if(xx == p.getX() && yy == p.getY()) {
	    									System.out.println("Tür gefunden " + p.getX() + " , " + yy);
	    									// set for to a end
	    									k = room.neidoorx.size();
	    									
	    									// this could be optimized, so once we entered here, breakout
	    									// (only possible, if we want make both rooms visible and not one
	    									if(neighbour1 == null) {
	    										System.out.println("Room1 ini");
	    										neighbour1 = room;
	    									} else {
	    										neighbour2 = room;
	    										System.out.println("Room2 ini");
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
        g2d.fillRect((Settings.GAME_W/2), (Settings.GAME_H/2)-40, imgWidth, imgHeight);
        
    }
    
    public Visible getFieldPosition(int x, int y) {
    	return gameField[y][x];
    }
    
    public void setFieldPosition(int x, int y, Visible e) {
    	gameField[y][x] = e;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
