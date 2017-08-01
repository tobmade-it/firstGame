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
	private GUIMain main = null;
	private DungeonArea dungeon = null;
	
	public Window(Visible[][] field) {
		main = new GUIMain(field);
		dungeon = main.dungeon;
        initUI();
    }
	
    private void initUI() {      
        add(this.main);
        
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
				int key = e.getKeyCode();
				int dis = 1;
				int step = 2;
				int correct = 1;
				int velo = 4;
				
				Player p = main.p;
				
				if(key == 37 || key == KeyEvent.VK_A) {
					// left
					p.setViewdirection(1);
					for(int i=0; i < velo; i++) {
						if(GUIMain.p.getExactX() != 0) {
							correct = 1;
						} else {
							correct = 0;
						}
						
						if( !(dungeon.getFieldPosition(p.getX()-1+correct, p.getY()).getIsSolid()) ) {
							if(GUIMain.p.getExactX() == 0) {
								GUIMain.lastPlayerX = p.getX();
								GUIMain.p.setX(p.getX()-dis);
								GUIMain.p.setExactX(80-step);
								dungeon.setFieldPosition(p.getX(),p.getY(), Main.change(dungeon.getFieldPosition(p.getX(),p.getY())));
							} else if(GUIMain.p.getExactX() < -(80-step)) {
								GUIMain.p.setX(p.getX()-dis);
								GUIMain.p.setExactX(0);
							} else {
								GUIMain.p.setExactX(GUIMain.p.getExactX()-step);
							}
						}
					}
				} else if(key == 38 || key == KeyEvent.VK_W) {
					// top
					p.setViewdirection(0);
					for(int i=0; i < velo; i++) {
					if(GUIMain.p.getExactY() != 0) {
						correct = 1;
					} else {
						correct = 0;
					}
					if(!(dungeon.getFieldPosition(p.getX(), p.getY()-1+correct).getIsSolid())) {
						if(GUIMain.p.getExactY() == 0) {
							GUIMain.lastPlayerY = p.getY();
							GUIMain.p.setY(p.getY()-dis);
							GUIMain.p.setExactY(80-step);
							dungeon.setFieldPosition(p.getX(),p.getY(), Main.change(dungeon.getFieldPosition(p.getX(),p.getY())));
						} else if(GUIMain.p.getExactY() < -(60-step-1)) {
							GUIMain.p.setY(p.getY()-dis);
							GUIMain.p.setExactY(20);
						} else {
							GUIMain.p.setExactY(GUIMain.p.getExactY()-step);
						}
						}
					}
				} else if(key == 39 || key == KeyEvent.VK_D) {
					// right
					p.setViewdirection(3);
					for(int i=0; i < velo; i++) {
					if(GUIMain.p.getExactX() == 40) {
						correct = 0;
					} else {
						correct = 1;
					}
					if( !(dungeon.getFieldPosition(p.getX()+1-correct, p.getY()).getIsSolid()) ) {
						
						if(GUIMain.p.getExactX() == 40) {
							GUIMain.lastPlayerX = p.getX();
							GUIMain.p.setX(p.getX()+dis);
							GUIMain.p.setExactX(-(40-step));
							dungeon.setFieldPosition(p.getX(),p.getY(), Main.change(dungeon.getFieldPosition(p.getX(),p.getY())));
						} else if(GUIMain.p.getExactX() > (80-step)) {
							GUIMain.p.setX(p.getX()+dis);
							GUIMain.p.setExactX(0);
						} else {
							GUIMain.p.setExactX(GUIMain.p.getExactX()+step);
						}
					}
					}
					
				} else if(key == 40 || key == KeyEvent.VK_S) {
					// bottom
					p.setViewdirection(2);
					for(int i=0; i < velo; i++) {
					if(GUIMain.p.getExactY() != 20) {
						correct = 1;
					} else {
						correct = 0;
					}
					if(!(dungeon.getFieldPosition(p.getX(), p.getY()+1-correct).getIsSolid())) {
						if(GUIMain.p.getExactY() == 20) {
							GUIMain.lastPlayerY = p.getY();
							GUIMain.p.setY(p.getY()+dis);
							GUIMain.p.setExactY(-(60-step));
						} else if(GUIMain.p.getExactY() > (80-step)) {
							GUIMain.p.setY(p.getY()+dis);
							GUIMain.p.setExactY(0);
						} else {
							GUIMain.p.setExactY(GUIMain.p.getExactY()+step);
						}
						dungeon.setFieldPosition(p.getX(),p.getY(),Main.change(dungeon.getFieldPosition(p.getX(),p.getY())));
					}
					}
				} else if(key == KeyEvent.VK_F) {
					// f to use
					Visible useOn = dungeon.getFieldPosition((int) (p.getX()+(p.getViewdirection())%2*Math.pow(-1, (p.getViewdirection())%3)), (int) (p.getY()+(p.getViewdirection()+1)%2*Math.pow(-1, (p.getViewdirection()+1)%3)));
					if(useOn.getType() == "M"){
						System.out.println(p.mainweapon.use(p, null, 0, null, (Creatures) useOn));
						Main.playsound("hit0");
						if(((Creatures) useOn).getHP() == 0){
							dungeon.setFieldPosition((int) (p.getX()+(p.getViewdirection())%2*Math.pow(-1, (p.getViewdirection())%3)), (int) (p.getY()+(p.getViewdirection()+1)%2*Math.pow(-1, (p.getViewdirection()+1)%3)) , new Floor_bloody());
						}else{
							System.out.println(((Mobs) useOn).attack(p));//p.mainweapon.use((Creatures) useOn, null, 0, null, p));
							Main.playsound("hit1");
							if(p.getHP() == 0){
								Main.playsound("lost");
							}
						}
					}
				} else if(key == KeyEvent.VK_I) {
					// open inv
					if(main.invOpen) {
						main.invOpen = false;
					} else {
						main.invOpen = true;
					}
				}
				// Play hitsound
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D || 
						  key == 37 || key == 38 || key == 39 ||key == 40) {
					Main.hitsound(dungeon.getFieldPosition(p.getX(), p.getY()),p);
				}
			}
		});
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = main.getTimer();
                timer.stop();
            }
        });
        
        // window settings
        setTitle(this.name);
        setSize(Settings.WINDOW_W, Settings.WINDOW_H);
        setLocationRelativeTo(null); // Window in the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
