package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Button;
import controller.Keyboard;
import controller.Mouse;
import model.Main;
import model.Maps;
import model.Room;
import model.Window;

public class Paint {
	private static JPanel c1;
	public static BufferedImage Iback, Ipot, Idef, Itroom;
	private static Mouse mouse1;
	public static Maps map;
	private static Keyboard key;
	private static Window win;
	private static Room[][] m = new Room[9][9];
	public static int[] pos = {0,0};
	public static int tutor;
	public static int potsHit, defendersHit;
	private static JFrame frame;
	public static boolean imported;
	private static int shiftx, shifty, shiftroomx, shiftroomy;
	private static double scale;
	public static Tutorial tut;
	public enum CurrentFrame {
		HOME, MAP, TUTORIAL, CONTROLS, CREDITS
	}
	public static CurrentFrame current = CurrentFrame.HOME;
	
	public static void paintMain() throws IOException {
		frame = new JFrame();
		frame.setSize(960,996);
		frame.setTitle("Map Reading Practice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		c1 = new JPanel();
		frame.add(c1);
		frame.setVisible(true);
		mouse1 = new Mouse();
		key = new Keyboard();
		win = new Window();
		c1.addMouseListener(mouse1);
		frame.addWindowStateListener(win); 
		frame.addKeyListener(key);
		tut = new Tutorial(c1, frame, mouse1);
		tutor = 1;
		potsHit = 0;
		defendersHit = 0;
		
		frame.addComponentListener( new ComponentAdapter() {
            public void componentResized( ComponentEvent e ) {
            	repaint();
            }
            public void componentMoved( ComponentEvent e ) {
                repaint();
            }
            public void componentShown( ComponentEvent e ) {
            	repaint();
            }
            public void componentHidden( ComponentEvent e ) {
            	repaint();
            }
        } );
		
		try {
			map = new Maps();
		} catch (IOException e1) {
		}
		
		try {
			URL url1 = Main.class.getResource("/resources/Background Map.jpg");
		    Iback = ImageIO.read(url1);
		} catch (IOException e) {
		}
		try {
			URL url2 = Main.class.getResource("/resources/Pot.png");
		    Ipot = ImageIO.read(url2);
		} catch (IOException e) {
		}
		try {
			URL url3 = Main.class.getResource("/resources/Defender.png");
		    Idef = ImageIO.read(url3);
		} catch (IOException e) {
		}
		try {
			URL url4 = Main.class.getResource("/resources/Troom.png");
		    Itroom = ImageIO.read(url4);
		} catch (IOException e) {
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				m[i][j] = new Room();
			}
		}
		paintHome();
	}
	public static void paintHome() {
		current = CurrentFrame.HOME;
		Graphics g = c1.getGraphics();
		g.drawImage(Iback, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, 960, 988, null);
		Font f1 = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Lost Halls Map Reading Practice App", (int) (frame.getWidth()/2 -364), (int)(frame.getHeight()*.23));
		g.setColor(Color.getHSBColor(0,0,(float) 0.5));
		new Button(1,(int)(frame.getWidth()/2-125),(int)(frame.getHeight()*.3800),250,50,Color.GRAY,"Give me a map", c1, mouse1);
		new Button(5,(int)(frame.getWidth()/2-113),(int)(frame.getHeight()*.4800),226,50,Color.GRAY,"Import a map", c1, mouse1);
		new Button(2,(int)(frame.getWidth()/2-80),(int)(frame.getHeight()*.5800),160,50,Color.GRAY,"Controls", c1, mouse1);
		new Button(4,(int)(frame.getWidth()/2-76),(int)(frame.getHeight()*.6800),152,50,Color.GRAY,"Tutorial", c1, mouse1);
		new Button(8,(int)(frame.getWidth()/2-72),(int)(frame.getHeight()*.7800),144,50,Color.GRAY,"Credits", c1, mouse1);
	}
	public static void paintControls() {
		current = CurrentFrame.CONTROLS;
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 30);
		g.setFont(f);
		g.drawString("Controls are very simple.", (int)(frame.getWidth()/2-185), (int)(frame.getHeight()*.1500));
		g.drawString("You start in the spawn room.", (int)(frame.getWidth()/2-215), (int)(frame.getHeight()*.2420));
		g.drawString("Use your arrow keys to navigate the halls.", (int)(frame.getWidth()/2-310), (int)(frame.getHeight()*.3340));
		g.drawString("You can start a new map at any time by selecting \"New Map\". ", (int)(frame.getWidth()/2-445), (int)(frame.getHeight()*.4260));
		g.drawString("Good Luck.", (int)(frame.getWidth()/2-95), (int)(frame.getHeight()*.5180));
		new Button(3, (int)(frame.getWidth()/2-134), (int)(frame.getHeight()*.7000), 236, 50, Color.GRAY, "Back to home", c1, mouse1);
	}
	
	public static void paintTutorial(int n) {
		Paint.current = CurrentFrame.TUTORIAL;
		Tutorial.shiftx = (int)(shiftx + scale*345.6);
		Tutorial.shifty = (int)(shifty + scale*345.6);
		Tutorial.scale = scale*.6;
		tutor += n;
		Method method;
		try {
			method = Tutorial.class.getDeclaredMethod("paintTutorial"+tutor);
			method.invoke(frame);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startGame(boolean loaded) {
		imported = loaded;
		current = CurrentFrame.MAP;
		Graphics g = c1.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(shiftx-1, shifty-1, (int)(866*scale), (int)(866*scale));
		g.setColor(Color.BLACK);
		g.fillRect(shiftx, shifty, (int)(866*scale)-2, (int)(866*scale)-2);
		shiftroomx = 0;
		shiftroomy = 0;
		
		new Button(3, (int)(frame.getWidth()*.25-118), (int)(frame.getHeight()-115), 236, 50, Color.GRAY, "Back to home", c1, mouse1);
		
		if(imported) {
			m = map.getLoadedMap();
			new Button(9, (int)(frame.getWidth()*.75-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
		else {
			m = map.getMap();
			new Button(1, (int)(frame.getWidth()*.75-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
		
		if(m[0][8].border) {
			shiftroomx = 48;
		}
		if(m[8][0].border) {
			shiftroomy = 48;
		}
		paintHits();
		/*   								This code seems to be redundant, spawn is set already.
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				if(m[i][k].start) {
					pos[0] = m[i][k].x;
					pos[1] = m[i][k].y;
				}
			}
		}
		*/
		paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
	}
	
	public static void paintRoom(int y, int x, Room n) {
		current = CurrentFrame.MAP;
		Graphics g = c1.getGraphics();
			g.setColor(Color.getHSBColor(0, 0, (float) .2));
			g.fillRect((int)(shiftx+scale*(12+shiftroomx+96*x)), (int)(shifty+scale*(12+96*y+shiftroomy)), (int)(scale*(72)), (int)(scale*(72)));
			
			if(n.up) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*x)), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(24)), (int)(scale*(32)));
			}
			if(n.right) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(32+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(36+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(24)));
			}
			if(n.down) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*x)), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(24)), (int)(scale*(32)));
			}
			if(n.left) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(32+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
				g.setColor(Color.DARK_GRAY);
				g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(36+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(24)));
			}
			
			g.setColor(Color.GRAY);
			g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*x)), (int)(shifty+scale*(16+96*y+shiftroomy)), (int)(scale*(64)), (int)(scale*(64)));
			if(n.pot) {
				g.drawImage(Ipot, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+96*y+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+96*y+shiftroomy)), 0, 0, 64, 64, null);
				if(!n.seen) {
					potsHit++;
					paintHits();
				}
			}
			if(n.defender) {
				g.drawImage(Idef, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+96*y+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+96*y+shiftroomy)), 0, 0, 131, 131, null);
				if(!n.seen) {
					defendersHit++;
					paintHits();
				}
			}
			if(n.start && !n.seen) {
				paintSpawnPeeks(y, x);
			}
		n.seen = true;
		g.setColor(Color.BLUE);
		g.fillOval((int)(shiftx+scale*(42+shiftroomx+96*pos[1])), (int)(shifty+scale*(42+96*pos[0]+shiftroomy)), (int)(scale*(12)), (int)(scale*(12)));
	}
	
	public static void paintHits() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect((int)(frame.getWidth()*.5-80), (int)(frame.getHeight()-120), 170, 70);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("Defender: "+defendersHit, (int)(frame.getWidth()*.5-60), (int)(frame.getHeight()-100));
		g.drawString("Pots: "+potsHit, (int)(frame.getWidth()*.5-40), (int)(frame.getHeight()-70));
	}
	
	public static void paintSpawnPeeks(int x, int y) {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		if(m[x][y].up && !m[x-1][y].seen) {
			paintRoom(x-1, y, m[x-1][y]);
			g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
			m[x-1][y].seen = false;
		}
		if(m[x][y].down && !m[x+1][y].seen) {
			paintRoom(x+1, y, m[x+1][y]);
			g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x+1)+48+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
			m[x+1][y].seen = false;
		}
		if(m[x][y].right && !m[x][y+1].seen) {
			paintRoom(x, y+1, m[x][y+1]);
			g.fillRect((int)(shiftx+scale*(96*(y+1)+48+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
			m[x][y+1].seen = false;
		}
		if(m[x][y].left && !m[x][y-1].seen) {
			paintRoom(x, y-1, m[x][y-1]);
			g.fillRect((int)(shiftx+scale*(96*(y-1)-17+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
			m[x][y-1].seen = false;
		}
	}
	
	public static void cleanRoom(int y, int x, Room n) {
		Graphics g = c1.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*x)), (int)(shifty+scale*(16+shiftroomy+96*y)), (int)(scale*(64)), (int)(scale*(64)));
		if(n.pot) {
			g.drawImage(Ipot, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+shiftroomy+96*y)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+shiftroomy+96*y)), 0, 0, 64, 64, null);
		}
		if(n.defender) {
			g.drawImage(Idef, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+shiftroomy+96*y)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+shiftroomy+96*y)), 0, 0, 131, 131, null);
		}
	}
	
	public static void moveUp() {
		if(m[pos[0]][pos[1]].up) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[0]--;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			//System.out.println("Up");
		}
	}
	
	public static void moveDown() {
		if(m[pos[0]][pos[1]].down) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[0]++;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			//System.out.println("Down");
		}
	}
	
	public static void moveRight() {
		if(m[pos[0]][pos[1]].right) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[1]++;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			//System.out.println("Right");
		}
	}
	
	public static void moveLeft() {
		if(m[pos[0]][pos[1]].left) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[1]--;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			//System.out.println("Left");
		}
	}
	public static void paintCredit() {
		current = CurrentFrame.CREDITS;
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		Font f = new Font("SansSerif", Font.BOLD, 22);
		g.setFont(f);
		g.drawString("Creator: Nacnudd", 50, (int)(frame.getHeight()*.1));
		g.drawString("Tutorial Creator: Nacnudd", 50, (int)(frame.getHeight()*.2));
		g.drawString("Assisting Ideas: Salcosa, NGL, RealmGold, LOLFAILZ, Goalaso", 50, (int)(frame.getHeight()*.3));
		g.drawString("Maps: Rushers of pub halls runs, RL's from all discords, several other sources.", 50,(int)(frame.getHeight()*.4));
		g.drawString("Rushers with Significant Contributions: TreePuns, Beregue, DrunkDevil, Evilfan,", 50,(int)(frame.getHeight()*.5));
		g.drawString("Maldir, Semicar, xRomeWolvx, Frus", 100, (int)(frame.getHeight()*.54));
		g.drawString("Assistance Encrypting Maps: FoxLoli, Xbyxbd", 50, (int)(frame.getHeight()*.64));
		
		new Button(3,(int)(frame.getWidth()*.5-118),(int)(frame.getHeight()-115),236,50,Color.GRAY,"Back to home", c1, mouse1);
	}
	
	public static void repaintDelay() {
		Timer t = new Timer(10, e -> repaint());
		t.setRepeats(false);
		t.start();
	}
	
	public static void repaint() {
		scale = ((float)frame.getHeight()/1040);
		shiftx = (int)((frame.getWidth()-scale*864)/2);
		shifty = 10;
		if(shiftx < 0) {
			scale = ((float)frame.getWidth()/960);
			shiftx = 25;
			shifty = (int)((frame.getHeight()-scale*864)/2);
		}
		switch (current) {
			case TUTORIAL:
				paintTutorial(0);
				break;
			case MAP:
				repaintMap();
				break;
			case HOME:
				paintHome();
				break;
			case CREDITS:
				paintCredit();
				break;
			case CONTROLS:
				paintControls();
				break;
			default:
				break;
		}
	}
	public static void repaintMap() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(shiftx-1, shifty-1, (int)(866*scale), (int)(866*scale));
		g.setColor(Color.BLACK);
		g.fillRect(shiftx, shifty, (int)(866*scale)-2, (int)(866*scale)-2);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (m[i][j].seen) {
					paintRoom(i, j, m[i][j]);
					if(m[i][j].start) {
						paintSpawnPeeks(i,j);
					}
				}
			}
		}
		paintHits();
		new Button(3, (int)(frame.getWidth()*.25-118), (int)(frame.getHeight()-115), 236, 50, Color.GRAY, "Back to home", c1, mouse1);
		if(imported) {
			new Button(9, (int)(frame.getWidth()*.75-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
		else {
			new Button(1, (int)(frame.getWidth()*.75-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
	}
}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.
