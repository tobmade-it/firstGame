package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import chars.Creatures;
import chars.Mobs;
import chars.Player;
import game.Main;
import game.Settings;
import game.Visible;
import objects.Floor_bloody;

/**
 * 
 * General Window Class handling all window settings
 * and also listen to events and handle them.
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	private String name = "Our first game";
	private GameArea gameArea = null;
	
	public Window(Visible[][] field) {
		gameArea = new GameArea(field);
        initUI();
    }
	
    private void initUI() {      
        add(this.gameArea);
        
        addKeyListener(new KeyListener() {
        	
        	// TODO: to the same key events, when the key is typed
        	//       that the player can hold the keys down
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				int dis = 1;
				
				Player p = gameArea.p;
				
				if(key == 37 || key == KeyEvent.VK_A) {
					// left
					p.setViewdirection(1);
					if(!(gameArea.getFieldPosition(p.getX()-1, p.getY()).getIsSolid())) {
						gameArea.lastPlayerX = p.getX();
						gameArea.p.setX(p.getX()-dis);
						gameArea.setFieldPosition(p.getX(),p.getY(), Main.change(gameArea.getFieldPosition(p.getX(),p.getY())));
					}
				} else if(key == 38 || key == KeyEvent.VK_W) {
					// top
					p.setViewdirection(0);
					if(!(gameArea.getFieldPosition(p.getX(), p.getY()-1).getIsSolid())) {
						gameArea.lastPlayerY = p.getY();
						gameArea.p.setY(p.getY()-dis);
						gameArea.setFieldPosition(p.getX(),p.getY(), Main.change(gameArea.getFieldPosition(p.getX(),p.getY())));
					}
				} else if(key == 39 || key == KeyEvent.VK_D) {
					// right
					p.setViewdirection(3);
					if(!(gameArea.getFieldPosition(p.getX()+1, p.getY()).getIsSolid())) {
						gameArea.lastPlayerX = p.getX();
						gameArea.p.setX(p.getX()+dis);
						gameArea.setFieldPosition(p.getX(),p.getY(), Main.change(gameArea.getFieldPosition(p.getX(),p.getY())));
					}
				} else if(key == 40 || key == KeyEvent.VK_S) {
					// bottom
					p.setViewdirection(2);
					if(!(gameArea.getFieldPosition(p.getX(), p.getY()+1).getIsSolid())) {
						gameArea.lastPlayerY = p.getY();
						gameArea.p.setY(p.getY()+dis);
						gameArea.setFieldPosition(p.getX(),p.getY(),Main.change(gameArea.getFieldPosition(p.getX(),p.getY())));
					}
				} else if(key == KeyEvent.VK_F) {
					// f to use
					Visible useOn = gameArea.getFieldPosition((int) (p.getX()+(p.getViewdirection())%2*Math.pow(-1, (p.getViewdirection())%3)), (int) (p.getY()+(p.getViewdirection()+1)%2*Math.pow(-1, (p.getViewdirection()+1)%3)));
					if(useOn.getType() == "M"){
						System.out.println(p.mainweapon.use(p, null, 0, null, (Creatures) useOn));
						Main.playsound("hit0");
						if(((Creatures) useOn).getHP() == 0){
							gameArea.setFieldPosition((int) (p.getX()+(p.getViewdirection())%2*Math.pow(-1, (p.getViewdirection())%3)), (int) (p.getY()+(p.getViewdirection()+1)%2*Math.pow(-1, (p.getViewdirection()+1)%3)) , new Floor_bloody());
						}else{
							System.out.println(((Mobs) useOn).attack(p));//p.mainweapon.use((Creatures) useOn, null, 0, null, p));
							Main.playsound("hit1");
							if(p.getHP() == 0){
								Main.playsound("lost");
							}
						}
					}
				}
				// Play hitsound
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D || 
						  key == 37 || key == 38 || key == 39 ||key == 40) {
					Main.hitsound(gameArea.getFieldPosition(p.getX(), p.getY()),p);
				}
			}
		});
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = gameArea.getTimer();
                timer.stop();
            }
        });
        
        // window settings
        setTitle(this.name);
        setSize(Settings.WINDOW_W, Settings.WINDOW_H);
        //setLocationRelativeTo(null); // Window in the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
