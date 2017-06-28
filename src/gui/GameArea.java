package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import Generator.Generate;
import Generator.Room;
import chars.Player;
import game.Settings;
import game.Visible;

public class GameArea extends JPanel implements ActionListener {
	private final int DELAY = 1000/Settings.FPS; // FPS of the game
    private Timer timer;
    private Visible[][] gameField = null;
    //private List<Room> rooms = new ArrayList<Room>();
    private boolean[][] visited = new boolean[Render.Y_FIELD][Render.X_FIELD];
    private boolean[][] activeRoom = new boolean[Render.Y_FIELD][Render.X_FIELD];
    public Player p = new Player(20);
    
    public int lastPlayerX;
    public int lastPlayerY;
    
    public GameArea(Visible[][] gameField) {   	
    	this.setBackground(Settings.BACKGROUND);
    	this.gameField = gameField;
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
    
    private void drawObject(Visible v, Graphics2D g2d, int i, int j) {
    	Color useColor = Color.gray;
    	
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
    	g2d.setPaint(useColor);
    	g2d.fillRect(i*35, j*30, 35, 30);
    }
    
    private void drawDarkObject(Visible v, Graphics2D g2d, int i, int j) {
    	Color useColor = Color.lightGray;
    	
    	if(v.getType() == "w") {
			useColor = Color.lightGray;
		} else  {
			useColor = Color.black;
		}
    	
    	g2d.setPaint(useColor);
    	g2d.fillRect(i*35, j*30, 35, 30);
    }
    
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

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        int w = getWidth();
        int h = getHeight();
        
        g2d.setPaint(Color.white);
        
        g2d.drawRect(0, 0, Settings.GAME_W, Settings.GAME_H);
        
        g2d.setPaint(Color.red);
        g2d.fillRect(0, 600, p.getMaxHP()*7, 60);
        g2d.setPaint(Color.green);
        g2d.fillRect(0, 600, p.getHP()*7, 60);  
        
        int x = Render.X_FIELD;
        int y = Render.Y_FIELD;
        
        g2d.drawString("x: " + Integer.toString(p.getX()), 800, 20);
        g2d.drawString("y: " + Integer.toString(p.getY()), 800, 40);
        g2d.drawString("x: " + Integer.toString(this.lastPlayerX), 800, 60);
        g2d.drawString("y: " + Integer.toString(this.lastPlayerY), 800, 80);
        
        boolean search = true;
        boolean count = false;
        
        Room neighbour1 = null;
		Room neighbour2 = null;
        
        for(int i = 0; i < 20; i++) {
        	for(int j = 0; j < 20; j++) {
        		int xValue = p.getX()-(10-i);
        		int yValue = p.getY()-(10-j);
        		if(xValue >= 0 && xValue < Render.X_FIELD && yValue >= 0 && yValue < Render.Y_FIELD) {
        			Visible v = gameField[yValue][xValue];
        			//System.out.println(yValue + " " + xValue);
        			//System.out.println(activeRoom[yValue][xValue]);
        			if(visited[yValue][xValue]) {
        				drawDarkObject(v, g2d, i, j);
        			}
        			
        			if(activeRoom[yValue][xValue]) {
        				drawObject(v, g2d, i, j);
        			}
        			
        			
        			Visible vv = gameField[p.getY()][p.getX()];
        			if(vv.getType() == "D") {
        	        	if(p.getX() == 4 && p.getY() == 8) {
        	        		resetActiveRoom();
        	        		// Entrance
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
        	    
        	        		
        	        		BREAKOUT:
        	        		if(search) {
	    	        			for (Room room : Generate.rooms) {
		        	        		//
	    							for(int k=0; k < room.neidoorx.size(); k++) {
	    								int xx = room.neidoorx.get(k);
	    								int yy = room.neidoory.get(k);
	    								if(xx == p.getX() && yy == p.getY()) {
	    									System.out.println("Tür gefunden " + p.getX() + " , " + yy);
	    									// set for to a end, maybe 'break' possible?
	    									k = room.neidoorx.size();
	    									
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
        	        		if(neighbour1 != null) {
	        	        		Room room = neighbour1;
	        	        		
	        	        		// move room up and down
	        	        		if(lastPlayerY < neighbour1.starty) {
	        	        			room = neighbour1;
								} else if(lastPlayerY < neighbour2.starty) {
									room = neighbour2;
								}
	        	        		// move room left and right
	        	        		if(lastPlayerX < neighbour1.startx) {
	        	        			room = neighbour1;
								} else if(lastPlayerX < neighbour2.startx) {
									room = neighbour2;
								}
	        	        		
	        	        		resetActiveRoom();
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
        /*
        for(int i = 0; i < y; i++){
			for(int j = 0; j < x; j++){
				Visible v = gameField[i][j];
				if(v != null){
					// draw wall
					int off = 200;
					if(v.getType() == "w") {
						g2d.setPaint(Color.white);
						g2d.fillRect(off+j*30, off+i*35, 30, 35);
					}
					if(v.getType() == " ") {
						g2d.setPaint(Color.blue);
						g2d.fillRect(off+j*30, off+i*35, 30, 35);
					}
				}else{
					System.out.print("! ");
				}
			}
        } */
        
        // draw Player
        g2d.setPaint(Color.red);
        g2d.fillRect((Settings.GAME_W/2), (Settings.GAME_H/2), 35, 30);
        
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
