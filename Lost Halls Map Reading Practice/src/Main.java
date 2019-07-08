//This class will manage the home screen and communicate with all other classes
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	
	private static JPanel c1;
	private static BufferedImage Iback, Ipot, Idef;
	private static Mouse mouse1;
	public static Maps map;
	private static Keyboard key;
	private static int[][] m;
	public static int[] pos = {0,0};
	public static boolean[][] seen = new boolean[9][9];
	private static int tutor;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(960,988);
		frame.setTitle("Map Reading Practice");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c1 = new JPanel();
		frame.add(c1);
		frame.setVisible(true);
		mouse1 = new Mouse();
		key = new Keyboard();
		c1.addMouseListener(mouse1);
		frame.addKeyListener(key);
		try {
			map = new Maps();
		} catch (IOException e1) {
		}
		
		try {
			URL url1 = Main.class.getResource("Background Map.jpg");
		    Iback = ImageIO.read(url1);
		} catch (IOException e) {
		}
		try {
			URL url2 = Main.class.getResource("Pot.png");
		    Ipot = ImageIO.read(url2);
		} catch (IOException e) {
		}
		try {
			URL url3 = Main.class.getResource("Defender.png");
		    Idef = ImageIO.read(url3);
		} catch (IOException e) {
		}
		
		paintHome();
		
	}
	
	public static void paintHome() {
		Graphics g = c1.getGraphics();
		g.drawImage(Iback,0,0,null);
		Font f1 = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Lost Halls Map Reading Practice App", 130, 270);
		g.setColor(Color.getHSBColor(0,0,(float) 0.5));
		new Button(1,355,380,250,50,Color.GRAY,"Give me a map", c1, mouse1);
		new Button(5,357,480,246,50,Color.GRAY,"Load up a map", c1, mouse1);
		new Button(2,400,580,160,50,Color.GRAY,"Controls", c1, mouse1);
		new Button(4,313,680,334,50,Color.GRAY,"Tutorial (Incomplete)", c1, mouse1);
		new Button(8,408,780,144,50,Color.GRAY,"Credits", c1, mouse1);
	}
	
	public static void paintControls() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 30);
		g.setFont(f);
		g.drawString("Controls are very simple.", 295, 150);
		g.drawString("You start in the spawn room.", 265, 240);
		g.drawString("Use your arrow keys to navigate the halls.", 170, 330);
		g.drawString("You can start a new map at any time by selecting \"New Map\". ", 35, 420);
		g.drawString("Good Luck.", 385, 510);
		new Button(3, 345, 650, 236, 50, Color.GRAY, "Back to home", c1, mouse1);
	}
	
	public static void paintTutorial(int n) {
		if (n == 0) {
			tutor = 1;
			paintTutorial1();
		}
		else{
			tutor += n;
			if (tutor == 1) {
				tutor = 1;
				paintTutorial1();
			}
			else if(tutor > 1 && tutor < 11) {
				paintTutorial2(tutor);
			}
			else if(tutor == 11) {
				paintTutorialEnd();
			}
		}
	}
	
	public static void paintTutorial1() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f1 = new Font("SansSerif", Font.BOLD, 30);
		g.setFont(f1);
		g.drawString("Welcome to the Lost Halls map reading tutorial.", 120, 100);
		g.drawString("The tutorial is split into several sections:", 170, 200);
		g.drawString("Part 1: Map Structure", 100, 380);
		g.drawString("Part 2: Map Reading Using Space", 100, 450);
		g.drawString("Part 3: Map Reading Using Splits", 100, 520);
		g.drawString("Part 4: Map Reading Using both Space and Splits", 100, 590);
		g.drawString("This tutorial has a lot of information, so make sure you", 80, 720);
		g.drawString("take your time, and don't be afraid to hit pots sometimes.", 70, 790);
		new Button(3,350,870,236,50,Color.GRAY,"Back to home", c1, mouse1);
		new Button(6,720,870,110,50,Color.GRAY,"Next", c1, mouse1);
	}
	
	public static void paintTutorial2(int n) {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f1 = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f1);
		
		try {
			BufferedImage Im;
			String s = "Tutorial " + Integer.toString(n-1) + ".PNG";
			URL url = Main.class.getResource(s);
		    Im = ImageIO.read(url);
		    if(n == 2) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 885, 889, null);
		    	g.drawString("Part 1: Map Structure", 275, 90);
		    }
		    else if(n == 3) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 890, 886, null);
		    }
		    else if(n == 4) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 886, 882, null);
		    }
		    else if(n == 5) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 895, 900, null);
		    }
		    else if(n == 6) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 897, 898, null);
		    }
		    else if(n == 7) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 897, 903, null);
		    }
		    else if(n == 8) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 901, 898, null);
		    }
		    else if(n == 9) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 953, 944, null);
		    }
		    else if(n == 10) {
		    	g.drawImage(Im, 0, 0, 960, 850, 0, 0, 957, 947, null);
		    }
		} catch (IOException e) {
		}
		new Button(7,80,870,110,50,Color.GRAY,"Back", c1, mouse1);
		new Button(3,350,870,236,50,Color.GRAY,"Back to home", c1, mouse1);
		new Button(6,720,870,110,50,Color.GRAY,"Next", c1, mouse1);
	}
	
	public static void paintTutorialEnd() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 70);
		g.setFont(f);
		g.drawString("That's it so far!", 230, 440);
		
		new Button(7,80,870,110,50,Color.GRAY,"Back", c1, mouse1);
		new Button(3,350,870,236,50,Color.GRAY,"Back to home", c1, mouse1);
	}
	
	public static void paintTutorialn() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 20);
		g.setFont(f);
	}
	
	public static void startGame(boolean loaded) {
		Graphics g = c1.getGraphics();
		g.fillRect(0, 0, 1000, 1000);
		
		new Button(3, 100, 870, 236, 50, Color.GRAY, "Back to home", c1, mouse1);
		
		if(loaded) {
			m = map.getLoadedMap();
			new Button(9, 700, 870, 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
		else {
			m = map.getMap();
			new Button(1, 700, 870, 172, 50, Color.GRAY, "New Map", c1, mouse1);
		}
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				seen[i][k] = false;
				if(m[i][k] > 250 && m[i][k] < 500) {
					pos[0] = i;
					pos[1] = k;
				}
			}
		}
		drawRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
		seen[pos[0]][pos[1]] = true;
	}
	
	public static void drawRoom(int y, int x, int n) {
		Graphics g = c1.getGraphics();
		if(!seen[pos[0]][pos[1]]) {
			g.setColor(Color.getHSBColor(0, 0, (float) .2));
			g.fillRect(52+96*x, 22+96*y, 72, 72);
			
			if(n%2 == 1) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect(72+96*x, 96*y-6, 32, 32);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(76+96*x, 96*y-6, 24, 32);
			}
			if(n%3 == 1) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect(120+96*x, 42+96*y, 32, 32);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(120+96*x, 46+96*y, 32, 24);
			}
			if(n%5 == 1) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect(72+96*x, 90+96*y, 32, 32);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(76+96*x, 90+96*y, 24, 32);
			}
			if(n%7 == 1) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect(24+96*x, 42+96*y, 32, 32);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(24+96*x, 46+96*y, 32, 24);
			}
			
			g.setColor(Color.GRAY);
			g.fillRect(56+96*x, 26+96*y, 64, 64);
			if(n > 500 && n < 750) {
				g.drawImage(Ipot, 57+96*x, 27+96*y,null);
			}
			if(n > 750 && n < 999) {
				g.drawImage(Idef, 57+96*x, 27+96*y, 121+96*x, 91+96*y, 0, 0, 131, 131, null);
			}
		}
		g.setColor(Color.BLUE);
		g.fillOval(82+96*x, 52+96*y, 12, 12);
	}
	
	public static void cleanRoom(int y, int x, int n) {
		Graphics g = c1.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(56+96*x, 26+96*y, 64, 64);
		if(n > 500 && n < 750) {
			g.drawImage(Ipot, 57+96*x, 27+96*y,null);
		}
		if(n > 750 && n < 999) {
			g.drawImage(Idef, 57+96*x, 27+96*y, 121+96*x, 91+96*y, 0, 0, 131, 131, null);
		}
	}
	
	public static void moveUp() {
		if(m[pos[0]][pos[1]]%2 == 1) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[0]--;
			drawRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			seen[pos[0]][pos[1]] = true;
			//System.out.println("Up");
		}
	}
	
	public static void moveDown() {
		if(m[pos[0]][pos[1]]%5 == 1) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[0]++;
			drawRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			seen[pos[0]][pos[1]] = true;
			//System.out.println("Down");
		}
	}
	
	public static void moveRight() {
		if(m[pos[0]][pos[1]]%3 == 1) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[1]++;
			drawRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			seen[pos[0]][pos[1]] = true;
			//System.out.println("Right");
		}
	}
	
	public static void moveLeft() {
		if(m[pos[0]][pos[1]]%7 == 1) {
			cleanRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			pos[1]--;
			drawRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			seen[pos[0]][pos[1]] = true;
			//System.out.println("Left");
		}
	}
	public static void credit() {
		Graphics g = c1.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 18);
		g.setFont(f);
		g.drawString("Creator: Nacnudd", 20, 50);
		g.drawString("Programming Assistance: Goalaso", 20, 100);
		g.drawString("Assisting Ideas: Salcosa, NGL, RealmGold, Goalaso, LOLFAILZ", 20, 150);
		g.drawString("Tutorial Content: Creator of LH_map_lessons", 20, 200);
		g.drawString("Maps: Rushers of pub halls runs, RL's from all discords, several other sources.", 20, 250);
		
		new Button(3,350,870,236,50,Color.GRAY,"Back to home", c1, mouse1);
	}
}

//Created by Nacnudd from the Pub Halls Discord server.