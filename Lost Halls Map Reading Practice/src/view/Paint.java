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
import java.util.ArrayList;
import java.util.List;

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

public class Paint{
	private static JPanel c1;
	public static BufferedImage Iback, Ipot, Idef, Itroom, Ihome, i;
	private static Mouse mouse1;
	public static Maps map;
	private static Keyboard key;
	private static Room[][] m = new Room[9][9];
	private static boolean[][] seen;
	private static int[][] selected;
	public static int[] pos = {0,0};
	public static int tutor, highlightSetting;
	public static int potsHit, defendersHit;
	private static JFrame frame;
	public static boolean imported, showSpawn, showHits, peekSpawn, selectEnabled, markTroom, saved;
	private static int shiftx, shifty, shiftroomx, shiftroomy, historyPosition;
	private static double scale;
	private static List<boolean[][]> history = new ArrayList<boolean[][]>();
	private static List<int[]> posHistory = new ArrayList<int[]>();
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
		c1.addMouseListener(mouse1);
		c1.addMouseMotionListener(mouse1);
		frame.addKeyListener(key);
		i = new BufferedImage(960, 996, BufferedImage.TYPE_INT_ARGB);
		tut = new Tutorial(i, frame, mouse1);
		tutor = 1;
		potsHit = 0;
		defendersHit = 0;
		showSpawn = true;
		showHits = true;
		peekSpawn = true;
		selectEnabled = true;
		markTroom = true;
		
		frame.addComponentListener( new ComponentAdapter() {
            public void componentResized( ComponentEvent e ) {
            	i = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
            	repaintDelay(10);
            	//System.out.println("resized " + c1.getWidth());
            }
            public void componentMoved( ComponentEvent e ) {
                rePaint();
            }
            public void componentShown( ComponentEvent e ) {
            	rePaint();
            }
            public void componentHidden( ComponentEvent e ) {
            	rePaint();
            }
        } );
		
		try {
			map = new Maps();
		} catch (IOException e1) {}
		
		try {
			URL url1 = Main.class.getResource("/resources/Background Map.jpg");
		    Iback = ImageIO.read(url1);
		} catch (IOException e) {}
		try {
			URL url2 = Main.class.getResource("/resources/Pot.png");
		    Ipot = ImageIO.read(url2);
		} catch (IOException e) {}
		try {
			URL url3 = Main.class.getResource("/resources/Defender.png");
		    Idef = ImageIO.read(url3);
		} catch (IOException e) {}
		try {
			URL url4 = Main.class.getResource("/resources/Troom.png");
		    Itroom = ImageIO.read(url4);
		} catch (IOException e) {}
		try {
			URL url5 = Main.class.getResource("/resources/Home.png");
		    Ihome = ImageIO.read(url5);
		} catch (IOException e) {}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				m[i][j] = new Room();
			}
		}
		paintHome();
	}
	public static void paintHome() {
		current = CurrentFrame.HOME;
		Graphics g = i.getGraphics();
		g.drawImage(Iback, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, 960, 988, null);
		Font f1 = new Font("SansSerif", Font.BOLD, (int)(40*scale));
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Lost Halls Map Reading Practice App", (int) (frame.getWidth()/2 -(int)(364*scale)), (int)(frame.getHeight()*.23));
		g.setColor(Color.getHSBColor(0,0,(float) 0.5));
		new Button(1,(int)(frame.getWidth()/2-125),(int)(frame.getHeight()*.3800),250,50,Color.GRAY,"Give me a map", i, mouse1);
		new Button(5,(int)(frame.getWidth()/2-113),(int)(frame.getHeight()*.4800),226,50,Color.GRAY,"Import a map", i, mouse1);
		new Button(4,(int)(frame.getWidth()/2-76),(int)(frame.getHeight()*.5800),152,50,Color.GRAY,"Tutorial", i, mouse1);
		new Button(2,(int)(frame.getWidth()/2-76),(int)(frame.getHeight()*.6800),152,50,Color.GRAY,"Settings", i, mouse1);
		new Button(8,(int)(frame.getWidth()/2-72),(int)(frame.getHeight()*.7800),144,50,Color.GRAY,"Credits", i, mouse1);
		update();
	}
	
	public static void paintSettings() {
		current = CurrentFrame.CONTROLS;
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, (int)(30*scale));
		g.setFont(f);
		g.drawString("Count Pots and Defenders hit:", (int)(frame.getWidth()/2-(int)(400*scale)), (int)(frame.getHeight()*.1500));
		if(showHits) {
			new Button(10, (int)(frame.getWidth()/2+(int)(270*scale)), (int)(frame.getHeight()*.1500-33), 80, 50, Color.GRAY, "On", i, mouse1);
		}
		else{
			new Button(10, (int)(frame.getWidth()/2+(int)(260*scale)), (int)(frame.getHeight()*.1500-33), 90, 50, Color.GRAY, "Off", i, mouse1);
		}
		g.drawString("Mark spawn location:", (int)(frame.getWidth()/2-(int)(400*scale)), (int)(frame.getHeight()*.2500));
		if(showSpawn) {
			new Button(11, (int)(frame.getWidth()/2+(int)(270*scale)), (int)(frame.getHeight()*.2500-33), 80, 50, Color.GRAY, "On", i, mouse1);
		}
		else {
			new Button(11, (int)(frame.getWidth()/2+(int)(260*scale)), (int)(frame.getHeight()*.2500-33), 90, 50, Color.GRAY, "Off", i, mouse1);
		}
		g.drawString("Peek rooms off spawn:", (int)(frame.getWidth()/2-(int)(400*scale)), (int)(frame.getHeight()*.3500));
		if(peekSpawn) {
			new Button(12, (int)(frame.getWidth()/2+(int)(270*scale)), (int)(frame.getHeight()*.3500-33), 80, 50, Color.GRAY, "On", i, mouse1);
		}
		else {
			new Button(12, (int)(frame.getWidth()/2+(int)(260*scale)), (int)(frame.getHeight()*.3500-33), 90, 50, Color.GRAY, "Off", i, mouse1);
		}
		g.drawString("Enable room highlighting:", (int)(frame.getWidth()/2-(int)(400*scale)), (int)(frame.getHeight()*.4500));
		if(selectEnabled) {
			new Button(13, (int)(frame.getWidth()/2+(int)(270*scale)), (int)(frame.getHeight()*.4500-33), 80, 50, Color.GRAY, "On", i, mouse1);
		}
		else {
			new Button(13, (int)(frame.getWidth()/2+(int)(260*scale)), (int)(frame.getHeight()*.4500-33), 90, 50, Color.GRAY, "Off", i, mouse1);
		}
		g.drawString("Show troom after hitting pots:", (int)(frame.getWidth()/2-(int)(400*scale)), (int)(frame.getHeight()*.5500));
		if(markTroom) {
			new Button(14, (int)(frame.getWidth()/2+(int)(270*scale)), (int)(frame.getHeight()*.5500-33), 80, 50, Color.GRAY, "On", i, mouse1);
		}
		else {
			new Button(14, (int)(frame.getWidth()/2+(int)(260*scale)), (int)(frame.getHeight()*.5500-33), 90, 50, Color.GRAY, "Off", i, mouse1);
		}
		Font f2 = new Font("SansSerif", Font.BOLD, (int)(24*scale));
		g.setFont(f2);
		g.drawString("Basic Controls are arrow keys to move and backspace to undo", (int)(frame.getWidth()/2-(int)(365*scale)), (int)(frame.getHeight()*.7800));
		new Button(3, (int)(frame.getWidth()/2-25), (int)(frame.getHeight()*.8500), 50, 50, Color.GRAY, "", i, mouse1);
		g.drawImage(Ihome,  (int)(frame.getWidth()/2-25), (int)(frame.getHeight()*.8500+2), (int)(frame.getWidth()/2+25), (int)(frame.getHeight()*.8500+52), 0,0,360,360, null);
		update();
	}
	
	public static void paintTutorial(int n) {
		Paint.current = CurrentFrame.TUTORIAL;
		Tutorial.shiftx = (int)(shiftx + scale*345.6);
		Tutorial.shifty = (int)(shifty + scale*345.6);
		Tutorial.scale = scale*.6;
		tutor += n;
		Method method;
		tut.setImage(i);
		try {
			method = Tutorial.class.getDeclaredMethod("paintTutorial"+tutor);
			method.invoke(frame);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		update();
	}
	
	
	public static void startGame(boolean loaded) {
		imported = loaded;
		current = CurrentFrame.MAP;
		Graphics g = i.getGraphics();
		shiftroomx = 0;
		shiftroomy = 0;
		historyPosition = 0;
		history.clear();
		posHistory.clear();
		saved = false;
		seen = new boolean[9][9];
		selected = new int[9][9];
		
		if(imported) {
			m = map.getLoadedMap();
		}
		else {
			m = map.getMap();
		}
		
		if(m[0][8].border) {
			shiftroomx = 48;
		}
		if(m[8][0].border) {
			shiftroomy = 48;
		}
		if(showHits) {
			paintHits();
		}
		
		boolean[][] z = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				if(m[i][k].start) {
					pos[0] = m[i][k].x;
					pos[1] = m[i][k].y;
				}
				z[i][k] = seen[i][k];
			}
		}
		
		z[pos[0]][pos[1]] = true;
		history.add(z);
		posHistory.add(pos.clone());
		paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
		
		int potCount = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(m[i][j].pot) {potCount++;}
			}
		}
		
		if(potCount < 5) {
			rePaint();
			g.setColor(Color.PINK);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(m[i][j].troom) {
						g.fillOval((int)(shiftx+scale*(41+shiftroomx+96*j)), (int)(shifty+scale*(41+96*i+shiftroomy)), (int)(scale*(14)), (int)(scale*(14)));
					}
				}
			}
			update();
			repaintDelay(2000);
		}
		else { rePaint();}
	}
	
	public static void paintRoom(int y, int x, Room n) {
		current = CurrentFrame.MAP;
		Graphics g = i.getGraphics();
		g.setColor(Color.getHSBColor(0, 0, (float) .2));
		g.fillRect((int)(shiftx+scale*(12+shiftroomx+96*x)), (int)(shifty+scale*(12+96*y+shiftroomy)), (int)(scale*(72)), (int)(scale*(72)));
		if(n.up) {
			g.setColor(Color.getHSBColor(0, 0, (float) .2));
			g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*x)), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(24)), (int)(scale*(32)));
		}
		if(n.down) {
			g.setColor(Color.getHSBColor(0, 0, (float) .2));
			g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*x)), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(24)), (int)(scale*(32)));
		}
		if(n.right) {
			g.setColor(Color.getHSBColor(0, 0, (float) .2));
			g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(32+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(32)));
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(36+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(24)));
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
				if(showHits) {
					paintHits();
				}
				n.seen = true;
			}
		}
		if(n.troom) {
			g.drawImage(Itroom, (int)(shiftx+scale*(19+shiftroomx+96*x)), (int)(shifty+scale*(15+96*y+shiftroomy)), (int)(shiftx+scale*(81+shiftroomx+96*x)), (int)(shifty+scale*(77+96*y+shiftroomy)), 0, 0, 128, 128, null);
			if(!n.seen) {
				potsHit++;
				if(showHits) {
					paintHits();
				}
				n.seen = true;
			}
		}
		if(n.defender) {
			g.drawImage(Idef, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+96*y+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+96*y+shiftroomy)), 0, 0, 131, 131, null);
			if(!n.seen) {
				defendersHit++;
				if(showHits) {
					paintHits();
				}
				n.seen = true;
			}
		}
		if(n.start) {
			if(!seen[pos[0]][pos[1]]) {
				paintSpawnPeeks(y, x);
			}
			if(showSpawn) {
				Font f = new Font("SansSerif", Font.BOLD, (int)(48*scale));
				g.setFont(f);
				g.setColor(Color.GREEN);
				g.drawString("S", (int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(64+96*y+shiftroomy)));
			}
		}
		seen[pos[0]][pos[1]] = true;
		g.setColor(Color.BLUE);
		g.fillOval((int)(shiftx+scale*(42+shiftroomx+96*pos[1])), (int)(shifty+scale*(42+96*pos[0]+shiftroomy)), (int)(scale*(12)), (int)(scale*(12)));
	}
	
	public static void paintHits() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect((int)(frame.getWidth()*.5-80), (int)(frame.getHeight()-120), 170, 70);
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("Defender: "+defendersHit, (int)(frame.getWidth()*.5-60), (int)(frame.getHeight()-100));
		g.drawString("Pots: "+potsHit, (int)(frame.getWidth()*.5-40), (int)(frame.getHeight()-70));
	}
	
	public static void paintSpawnPeeks(int y, int x) {
		if(peekSpawn) {
			Graphics g = i.getGraphics();
			g.setColor(Color.BLACK);
			if(m[y][x].up && !seen[y-1][x]) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(12+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(72)), (int)(scale*(36)));
				if(m[y-1][x].right) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(16)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(12)));
				}
				if(m[y-1][x].left) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(16)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(12)));
				}
				g.setColor(Color.GRAY);
				g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*x)), (int)(shifty+scale*(48+96*(y-1)+shiftroomy)), (int)(scale*(64)), (int)(scale*(32)));
			}
			if(m[y][x].down && !seen[y+1][x]) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(12+shiftroomx+96*x)), (int)(shifty+scale*(12+96*(y+1)+shiftroomy)), (int)(scale*(72)), (int)(scale*(36)));
				if(m[y+1][x].right) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(32+96*(y+1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(16)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(80+shiftroomx+96*x)), (int)(shifty+scale*(36+96*(y+1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(12)));
				}
				if(m[y+1][x].left) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(32+96*(y+1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(16)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(-16+shiftroomx+96*x)), (int)(shifty+scale*(36+96*(y+1)+shiftroomy)), (int)(scale*(32)), (int)(scale*(12)));
				}
				g.setColor(Color.GRAY);
				g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*x)), (int)(shifty+scale*(16+96*(y+1)+shiftroomy)), (int)(scale*(64)), (int)(scale*(32)));
			}
			if(m[y][x].right && !seen[y][x+1]) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(12+shiftroomx+96*(x+1))), (int)(shifty+scale*(12+96*y+shiftroomy)), (int)(scale*(36)), (int)(scale*(72)));
				if(m[y][x+1].up) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*(x+1))), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(16)), (int)(scale*(32)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*(x+1))), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(12)), (int)(scale*(32)));
				}
				if(m[y][x+1].down) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(32+shiftroomx+96*(x+1))), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(16)), (int)(scale*(32)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(36+shiftroomx+96*(x+1))), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(12)), (int)(scale*(32)));
				}
				g.setColor(Color.GRAY);
				g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*(x+1))), (int)(shifty+scale*(16+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(64)));
			}
			if(m[y][x].left && !seen[y][x-1]) {
				g.setColor(Color.getHSBColor(0, 0, (float) .2));
				g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(12+96*y+shiftroomy)), (int)(scale*(36)), (int)(scale*(72)));
				if(m[y][x-1].up) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(16)), (int)(scale*(32)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(96*y-16+shiftroomy)), (int)(scale*(12)), (int)(scale*(32)));
				}
				if(m[y][x-1].down) {
					g.setColor(Color.getHSBColor(0, 0, (float) .2));
					g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(16)), (int)(scale*(32)));
					g.setColor(Color.DARK_GRAY);
					g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(80+96*y+shiftroomy)), (int)(scale*(12)), (int)(scale*(32)));
				}
				g.setColor(Color.GRAY);
				g.fillRect((int)(shiftx+scale*(48+shiftroomx+96*(x-1))), (int)(shifty+scale*(16+96*y+shiftroomy)), (int)(scale*(32)), (int)(scale*(64)));
			}
		}
	}
	
	public static void cleanRoom(int y, int x, Room n) {
		Graphics g = i.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*x)), (int)(shifty+scale*(16+shiftroomy+96*y)), (int)(scale*(64)), (int)(scale*(64)));
		if(n.pot) {
			g.drawImage(Ipot, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+shiftroomy+96*y)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+shiftroomy+96*y)), 0, 0, 64, 64, null);
		}
		if(n.troom) {
			g.drawImage(Itroom, (int)(shiftx+scale*(19+shiftroomx+96*x)), (int)(shifty+scale*(15+96*y+shiftroomy)), (int)(shiftx+scale*(81+shiftroomx+96*x)), (int)(shifty+scale*(77+96*y+shiftroomy)), 0, 0, 128, 128, null);
		}
		if(n.defender) {
			g.drawImage(Idef, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+shiftroomy+96*y)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+shiftroomy+96*y)), 0, 0, 131, 131, null);
		}
		if(n.start && showSpawn) {
			Font f = new Font("SansSerif", Font.BOLD, (int)(48*scale));
			g.setFont(f);
			g.setColor(Color.GREEN);
			g.drawString("S", (int)(shiftx+scale*(32+shiftroomx+96*x)), (int)(shifty+scale*(64+96*y+shiftroomy)));
		}
	}
	
	public static void moveUp() {
		if(m[pos[0]][pos[1]].up) {
			pos[0]--;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			boolean[][] z = new boolean[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					z[i][j] = seen[i][j];
				}
			}
			history.add(z);
			posHistory.add(pos.clone());
			historyPosition++;
			//System.out.println("Up");
			rePaint();
			update();
		}
	}
	
	public static void moveDown() {
		if(m[pos[0]][pos[1]].down) {
			pos[0]++;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			boolean[][] z = new boolean[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					z[i][j] = seen[i][j];
				}
			}
			history.add(z);
			posHistory.add(pos.clone());
			historyPosition++;
			//System.out.println("Down");
			rePaint();
			update();
		}
	}
	
	public static void moveRight() {
		if(m[pos[0]][pos[1]].right) {
			pos[1]++;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			boolean[][] z = new boolean[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					z[i][j] = seen[i][j];
				}
			}
			history.add(z);
			posHistory.add(pos.clone());
			historyPosition++;
			//System.out.println("Right");
			rePaint();
			update();
		}
	}
	
	public static void moveLeft() {
		if(m[pos[0]][pos[1]].left) {
			pos[1]--;
			paintRoom(pos[0], pos[1], m[pos[0]][pos[1]]);
			boolean[][] z = new boolean[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					z[i][j] = seen[i][j];
				}
			}
			history.add(z);
			posHistory.add(pos.clone());
			historyPosition++;
			//System.out.println("Left");
			rePaint();
			update();
		}
	}
	
	public static void undo() {
		if(historyPosition > 0) {
			history.remove(historyPosition);
			posHistory.remove(historyPosition);
			historyPosition--;
			boolean[][] z = new boolean[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					z[i][j] = history.get(historyPosition)[i][j];
				}
			}
			seen = z;
			pos = posHistory.get(historyPosition).clone();
			repaintMap();
			update();
		}
	}
	
	public static void paintHighlights(int x, int y, int b) {
		if(x > shiftx && y > shifty && x < (shiftx+scale*96*9) && y < (shifty+scale*96*9)) {
			int xcor = (int)(((x-shiftx)/scale-shiftroomx)/96);
			int ycor = (int)(((y-shifty)/scale-shiftroomy)/96);
			if(selected[xcor][ycor] != 0 ) {selected[xcor][ycor] = 0;}
			else if(b > 2048) {selected[xcor][ycor] = 2;}
			else {selected[xcor][ycor] = 1;}
			highlightSetting = selected[xcor][ycor];
			repaintMap();
			update();
		}
	}
	
	public static void checkIfHighlight(int x, int y, int b) {
		if(x > shiftx && y > shifty && x < (shiftx+scale*96*9) && y < (shifty+scale*96*9)) {
			int xcor = (int)(((x-shiftx)/scale-shiftroomx)/96);
			int ycor = (int)(((y-shifty)/scale-shiftroomy)/96);
			if(selected[xcor][ycor] != highlightSetting) {
				paintHighlights(x, y, b);
			}
		}
	}
	
	public static void revealMap() {
		boolean[][] z = new boolean[9][9];
		int pot = potsHit;
		int def = defendersHit;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if((m[i][j].up || m[i][j].right || m[i][j].down || m[i][j].left) && !m[i][j].border) {seen[i][j] = true;}
				z[i][j] = seen[i][j];
			}
		}
		
		history.add(z);
		posHistory.add(pos.clone());
		rePaint();
		potsHit = pot;
		defendersHit = def;
	}
	
	public static void saveMap() {
		saved = true;
		try {
			map.saveMap();
		} catch (IOException e) {}
	}
	
	public static void paintCredit() {
		current = CurrentFrame.CREDITS;
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		Font f = new Font("SansSerif", Font.BOLD, 22);
		g.setFont(f);
		g.drawString("Creator: Nacnudd", 50, (int)(frame.getHeight()*.1));
		g.drawString("Tutorial Creator: Nacnudd", 50, (int)(frame.getHeight()*.2));
		g.drawString("Assisting Ideas: Salcosa, YG, RealmGold, LOLFAILZ, Goalaso, DrNinjackx, Quadblox", 50, (int)(frame.getHeight()*.3));
		g.drawString("Runix", 100, (int)(frame.getHeight()*.34));
		g.drawString("Maps: Rushers of pub halls runs, RL's from all discords, several other sources.", 50,(int)(frame.getHeight()*.44));
		g.drawString("Rushers with Significant Contributions: TreePuns, Beregue, Drunkdevil, Evilfan,", 50,(int)(frame.getHeight()*.54));
		g.drawString("Maldir, Semicar, xRomeWolvx, Frus", 100, (int)(frame.getHeight()*.58));
		g.drawString("Assistance Encrypting Maps: FoxLoli, ChardosA", 50, (int)(frame.getHeight()*.68));
		
		new Button(3, (int)(frame.getWidth()/2-25), (int)(frame.getHeight()*.8500), 50, 50, Color.GRAY, "", i, mouse1);
		g.drawImage(Ihome,  (int)(frame.getWidth()/2-25), (int)(frame.getHeight()*.8500+2), (int)(frame.getWidth()/2+25), (int)(frame.getHeight()*.8500+52), 0,0,360,360, null);
		update();
	}
	
	public static void repaintDelay(int time) {
		Timer t = new Timer(time, e -> rePaint());
		t.setRepeats(false);
		t.start();
	}
	
	
	public static void rePaint() {
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
				paintSettings();
				break;
			default:
				break;
		}
		update();
	}
	
	public static void repaintMap() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(shiftx-1, shifty-1, (int)(866*scale), (int)(866*scale));
		g.setColor(Color.BLACK);
		g.fillRect(shiftx, shifty, (int)(866*scale)-2, (int)(866*scale)-2);
		g.setColor(Color.YELLOW); //Color.getHSBColor((float)(0),(float)(0),(float)(.4))
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (selected[i][j] != 0 && !m[j][i].border) {
					if(selected[i][j] == 2) {g.setColor(Color.ORANGE);}
					else {g.setColor(Color.CYAN);}
					g.drawRect((int)(shiftx+3+scale*(96*i+shiftroomx)), (int)(shifty+3+scale*(96*j+shiftroomy)), (int)(scale*(90)), (int)(scale*(90)));
					g.drawRect((int)(shiftx+4+scale*(96*i+shiftroomx)), (int)(shifty+4+scale*(96*j+shiftroomy)), (int)(scale*(88)), (int)(scale*(88)));
				}
			}
		}
		boolean potsVisible = false;
		boolean troomVisible = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (seen[i][j]) {
					paintRoom(i, j, m[i][j]);
					if(m[i][j].start) {
						paintSpawnPeeks(i,j);
					}
					if(m[i][j].pot) {
						potsVisible = true;
					}
					if(m[i][j].troom) {
						troomVisible = true;
					}
				}
			}
		}
		
		Font f = new Font("SansSerif", Font.BOLD, ((int)(56*scale)));
		g.setFont(f);
		g.setColor(Color.PINK);
		if(potsVisible && (!troomVisible) && markTroom) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(m[i][j].troom) {
						g.fillOval((int)(shiftx+scale*(41+shiftroomx+96*j)), (int)(shifty+scale*(41+96*i+shiftroomy)), (int)(scale*(14)), (int)(scale*(14)));
					}
				}
			}
		}
		g.setColor(Color.BLUE);
		g.fillOval((int)(shiftx+scale*(42+shiftroomx+96*pos[1])), (int)(shifty+scale*(42+96*pos[0]+shiftroomy)), (int)(scale*(12)), (int)(scale*(12)));
		if(showHits) {paintHits();}
		new Button(3, (int)(frame.getWidth()*.12-25), (int)(frame.getHeight()-115), 50, 50, Color.GRAY, "", i, mouse1);
		g.drawImage(Ihome,  (int)(frame.getWidth()*.12-25), (int)(frame.getHeight()-115+2), (int)(frame.getWidth()*.12+25), (int)(frame.getHeight()-115+52), 0,0,360,360, null);
		
		f = new Font("SansSerif", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		g.setFont(f);
		if(saved) {
			g.drawString("Saved", (int)(frame.getWidth()*.25-40), (int)(frame.getHeight()-80));
		}
		else {
			new Button(16, (int)(frame.getWidth()*.25-58), (int)(frame.getHeight()-115), 116, 50, Color.GRAY, "Save", i, mouse1);
		}
		
		if(imported) {
			new Button(9, (int)(frame.getWidth()*.8-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", i, mouse1);
		}
		else {
			new Button(1, (int)(frame.getWidth()*.8-86), (int)(frame.getHeight()-115), 172, 50, Color.GRAY, "New Map", i, mouse1);
		}
	}
	
	public static void update() {
		Graphics g2 = c1.getGraphics();
		g2.drawImage(i, 0, 0, null);
	}
}

//Created by Nacnudd from the Pub Halls Discord server.