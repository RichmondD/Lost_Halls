package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JFrame;

import controller.Button;
import controller.Mouse;
import model.Room;

public class Tutorial {
	private static Image i;
	private static JFrame frame;
	private static Mouse mouse1;
	public static int shiftx, shifty, shiftroomx, shiftroomy;
	public static double scale;
	
	public Tutorial(Image im, JFrame f, Mouse ms) throws IOException {
		i = im;
		frame = f;
		mouse1 = ms;
	}
	
	public void setImage(Image im) {
		i = im;
	}
	
	public static void tutorialMap(int n) {
		Room[][] map = Paint.map.getTutorialMap(n);
		
		Graphics g = i.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(shiftx-1, shifty-1, (int)(866*scale), (int)(866*scale));
		g.setColor(Color.BLACK);
		g.fillRect(shiftx, shifty, (int)(866*scale)-2, (int)(866*scale)-2);
		shiftroomx = 0;
		shiftroomy = 0;
		
		if(map[0][8].border) {
			shiftroomx = 48;
		}
		if(map[8][0].border) {
			shiftroomy = 48;
		}
		
		for(int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				if((map[i][k].up || map[i][k].right || map[i][k].down || map[i][k].left) && !map[i][k].border) {
					paintRoom(i, k, map[i][k]);
				}
			}
		}
	}
	
	public static void paintRoom(int y, int x, Room n) {
		Graphics g = i.getGraphics();
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
				g.drawImage(Paint.Ipot, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+96*y+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+96*y+shiftroomy)), 0, 0, 64, 64, null);
			}
			if(n.defender) {
				g.drawImage(Paint.Idef, (int)(shiftx+scale*(17+shiftroomx+96*x)), (int)(shifty+scale*(17+96*y+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*x)), (int)(shifty+scale*(79+96*y+shiftroomy)), 0, 0, 131, 131, null);
			}
			if(n.start) {
				Font f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
				g.setFont(f);
				g.setColor(Color.BLUE);
				g.drawString("S", (int)(shiftx+scale*(29+shiftroomx+96*x)), (int)(shifty+scale*(66+96*y+shiftroomy)));
				//paintSpawnS(y, x);
			}
		}
	
	public static void paintButtons() {
		Graphics g = i.getGraphics();
		if(Paint.tutor != 1) {
			new Button(7,(int)(frame.getWidth()*.25-55),(int)(frame.getHeight()-115),110,50,Color.GRAY,"Back", i, mouse1);
		}
		new Button(3, (int)(frame.getWidth()/2-25), (int)(frame.getHeight()-115), 50, 50, Color.GRAY, "", i, mouse1);
		g.drawImage(Paint.Ihome,  (int)(frame.getWidth()/2-25), (int)(frame.getHeight()-115+2), (int)(frame.getWidth()/2+25), (int)(frame.getHeight()-115+52), 0,0,360,360, null);
		if(Paint.tutor != 60) {
			new Button(6,(int)(frame.getWidth()*.75-55),(int)(frame.getHeight()-115),110,50,Color.GRAY,"Next", i, mouse1);
		}
	}
	
	public static void paintTutorial1() {
		if(i == null) {
			System.out.println("test");
		}
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 30);
		g.setFont(f);
		
		g.drawString("Welcome to the Lost Halls Map Reading Tutorial", (int)(frame.getWidth()/2-360), (int)(frame.getHeight()*.1000));
		g.drawString("The tutorial is split into several sections:", (int)(frame.getWidth()/2-310), (int)(frame.getHeight()*.2000));
		g.drawString("Chapter 1: Map Structure", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.3300));
		g.drawString("Chapter 2: Spawn", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.3800));
		g.drawString("Chapter 3: Map Reading Using Space", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.4300));
		g.drawString("Chapter 4: Map Reading Using Splits", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.4800));
		g.drawString("Chapter 5: Rules of Thumb", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.530));
		g.drawString("Chapter 6: Putting it All Together", (int)(frame.getWidth()/2-340), (int)(frame.getHeight()*.5800));
		g.setColor(Color.RED);
		g.drawString("The tutorial is out of date! Most of the information is still", (int)(frame.getWidth()/2-400), (int)(frame.getHeight()*.6900));
		g.drawString("correct, but main path is 9 rooms long not 11! Please keep", (int)(frame.getWidth()/2-417), (int)(frame.getHeight()*.7600));
		g.drawString("in mind while reading! Hopefully update will be out soon!", (int)(frame.getWidth()/2-405), (int)(frame.getHeight()*.8300));
		
		paintButtons();
	}
	
	public static void paintTutorial2() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(0);
		g.drawString("Spawn", (int)(frame.getWidth()/2-80), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("As soon as you enter a new map, the first thing you see will be spawn room.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("Spawn can have 1, 2, 3, or 4 splits off of it, and only one will lead to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("defender. The rest of the splits will only go to pots. Spawn also tends to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("be near the middle of the map, and will never be all the way on the edge.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("In this map, spawn is top left of the middle and has 3 splits.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		paintButtons();
	}

	public static void paintTutorial3() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(1);
		g.drawString("Main Path", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Next, every map has one path from spawn to marble defender, which we will", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("call main path. Main path is exactly 11 rooms long not including spawn or", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("defender. Troom and every pot room split off of either spawn or main path,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("meaning you will never have a pot room split off of the path to another pot", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("room.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial4() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(1);
		g.drawString("MBC & Defender", (int)(frame.getWidth()/2-170), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("MBC room is always right after defender and occupies a space 3 rooms wide", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("and 2 rooms deep. Defender itself is the same size as every average room, ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("but there are never rooms on either side of defender, so it essentially", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("takes up a 1x3 room space. Together, MBC and defender take up a whole 3x3", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("chunk in which no other rooms can exist.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		g.setColor(Color.WHITE);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(288)), (int)(scale*(288)));
		g.drawLine((int)(shiftx+scale*(shiftroomx+96*2)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(shiftx+scale*(shiftroomx+96*2)), (int)(shifty+scale*(96*8+shiftroomy)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial5() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(1);
		g.drawString("Pot Splits", (int)(frame.getWidth()/2-105), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("After main path is generated, up to 6 'splits' will be generated off of main", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("path. A split is the commonly used term for one of two or more diverging", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("paths. Since the main goal of map reading is to get to defender without", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("hitting any pot rooms, your job is to identify which paths go to defender", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("and which go to pot rooms/troom. Each pot room will have 1, 2, or 3 normal", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("rooms and then a room full", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("of breakable pots.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		g.setColor(Color.WHITE);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(288)), (int)(scale*(288)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial6() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(2);
		g.drawString("Pot Splits", (int)(frame.getWidth()/2-105), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Of the 6 splits off of main path, 1 will always be to troom. The others will", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("all be pot rooms. Sometimes, you will get a map with less than 6 splits, or", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("equivalently less than 5 pot rooms. If you zoom your minimap out all the way", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("you'll sometimes be able to see a small pink dot for about 5 seconds after", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("the first person enters the dungeon. If you see this dot, that dot marks the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("location of troom, and means", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("that there are less than 5 pot", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("rooms in that halls map. It is", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("important to note that troom", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		g.drawString("can only be entered from", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5100));
		g.drawString("below.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5500));
		
		g.setColor(Color.WHITE);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(288)), (int)(scale*(288)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		g.setColor(Color.GRAY);
		g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*2)), (int)(shifty+scale*(16+96*1+shiftroomy)), (int)(scale*(64)), (int)(scale*(64)));
		g.drawImage(Paint.Itroom, (int)(shiftx+scale*(17+shiftroomx+96*2)), (int)(shifty+scale*(17+96*1+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*2)), (int)(shifty+scale*(79+96*1+shiftroomy)), 0, 0, 128, 128, null);
		
		paintButtons();
	}
	
	public static void paintTutorial7() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(3);
		g.drawString("Loops", (int)(frame.getWidth()/2-75), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Loops are a feature of halls maps that make them unique, since no dungeon", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("except halls intentionally has loops. In halls, loops are 4 rooms in a 2x2", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("square all connected, but they count as 1 room when counting rooms, so", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("think of them as one big fat room. There can be a maximum of 2 loops in a", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("map, one on main path and one on a path to one of the pots or troom. Also,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("the maximum recorded splits", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("from a single loop is 5, but it", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("may or may not be possible to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("have more.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		
		g.setColor(Color.GRAY);
		g.fillRect((int)(shiftx+scale*(16+shiftroomx+96*2)), (int)(shifty+scale*(16+96*2+shiftroomy)), (int)(scale*(64)), (int)(scale*(64)));
		g.drawImage(Paint.Itroom, (int)(shiftx+scale*(17+shiftroomx+96*2)), (int)(shifty+scale*(17+96*2+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*2)), (int)(shifty+scale*(79+96*2+shiftroomy)), 0, 0, 128, 128, null);
		
		g.setColor(Color.YELLOW);
		g.drawRect((int)(shiftx+scale*(48+shiftroomx+96*1)), (int)(shifty+scale*(48+96*0+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.drawRect((int)(shiftx+scale*(48+shiftroomx+96*4)), (int)(shifty+scale*(48+96*2+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.drawRect((int)(shiftx+scale*(48+shiftroomx+96*1))-1, (int)(shifty+scale*(48+96*0+shiftroomy))-1, (int)(scale*(96)+2), (int)(scale*(96)+2));
		g.drawRect((int)(shiftx+scale*(48+shiftroomx+96*4))-1, (int)(shifty+scale*(48+96*2+shiftroomy))-1, (int)(scale*(96)+2), (int)(scale*(96)+2));
		
		paintButtons();
	}
	
	public static void paintTutorial8() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(4);
		g.drawString("Dimensions", (int)(frame.getWidth()/2-120), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("All lost halls maps are grids, and can go up to 9x9. However, maps can also", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("be on 9x8, 8x9, or 8x8 grids. The map below is 9x8. It is only 6 rooms wide,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("but we consider it to be 8 wide because there is enough space on either side", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("for another room, it is just unused space. Vertically, it is clearly 9 rooms", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("tall. Sidenote, from here on out I will draw troom as a regular pot because", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("they are functionally", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("equivalent", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		g.setColor(Color.WHITE);
		for(int i = 0; i < 9; i++) {
			g.drawLine((int)(shiftx+scale*(shiftroomx+96*0)), (int)(shifty+scale*(96*i+shiftroomy)), (int)(shiftx+scale*(shiftroomx+96*8)), (int)(shifty+scale*(96*i+shiftroomy)));
			g.drawLine((int)(shiftx+scale*(shiftroomx+96*i)), (int)(shifty+scale*(96*0+shiftroomy)), (int)(shiftx+scale*(shiftroomx+96*i)), (int)(shifty+scale*(96*9+shiftroomy)));
		}
		g.drawLine((int)(shiftx+scale*(shiftroomx+96*0)), (int)(shifty+scale*(96*9+shiftroomy)), (int)(shiftx+scale*(shiftroomx+96*8)), (int)(shifty+scale*(96*9+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial9() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Generation", (int)(frame.getWidth()/2-115), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Though this is not necessary for map reading, it's interesting nonetheless.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("When A halls map is generated, MBC + Defender are created first. Second,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("main path is created extending 11 rooms out and spawn is added onto the end.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("While being created, main path is not allowed to deviate more than a certain", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("distance from defender's location. Once that is done, pot splits are added ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("one at a time and are generally added closer to spawn. While these steps are", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("carried out, each room created has a chance to be a loop unless there is too", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("little space or a loop has already been created for that specific context.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("Together, these rules create all our halls maps.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		
		paintButtons();
	}
	
	public static void paintTutorial10() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(5);
		g.drawString("Perfect Split", (int)(frame.getWidth()/2-120), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("When you are navigating through halls, there is one split that guarantees you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("are on the correct path. If you see a T split as shown in red, where you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("enter from below and there is no possibility of a loop on either the right", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("or left side, you are guaranteed to be on main path. The other formation that", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("provides 100% confidence you are on main path is going 4 rooms with no pots", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("or splits, as shown in yellow.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		g.setColor(Color.RED);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*6)), (int)(shifty+scale*(96*2+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.setColor(Color.YELLOW);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*4))-1, (int)(shifty+scale*(96*1+shiftroomy)), (int)(scale*(192)), (int)(scale*(192)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial11() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(6);
		g.drawString("Semi-Perfect Splits", (int)(frame.getWidth()/2-185), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("There are several splits that mean you are likely to be on main path, but do", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("not guarantee it. The first example is the imperfect T-split. If you arrive", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("at a T split like either one below, you are probably on main path, as the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("green one is, but it is possible to be going to pots if the T-split is part", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("of a loop, like the red T-split is part of the yellow loop.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		
		g.setColor(Color.YELLOW);
		g.drawRect((int)(shiftx+scale*(48+shiftroomx+96*1)), (int)(shifty+scale*(48+96*4+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.setColor(Color.RED);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*1)), (int)(shifty+scale*(96*4+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.setColor(Color.GREEN);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*6)), (int)(shifty+scale*(96*4+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		
		paintButtons();
	}
	
	public static void paintTutorial12() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(6);
		g.drawString("Semi-Perfect Splits", (int)(frame.getWidth()/2-185), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		
		g.drawString("To be more general, if you enter a room that has more than one split, you're", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("probably on the main path. Examples are all the green boxed rooms below.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("When you see any split like the ones below, you can be fairly confident you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("are on main path. But once again, it is slightly possible you're going to a pots", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("with a loop, as shown in red.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		g.setColor(Color.RED);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*2))-1, (int)(shifty+scale*(96*4+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.setColor(Color.GREEN);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*6)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*7)), (int)(shifty+scale*(96*3+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3)), (int)(shifty+scale*(96*4+shiftroomy)), (int)(scale*(96)), (int)(scale*(96)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial13() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(7);
		g.drawString("More Loops", (int)(frame.getWidth()/2-120), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("There are many cases, such as the one below, where you see a formation that", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("could be a loop. The left side of the map is main path so far, and it looks", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("likely that there is at least one loop here. However, when you see splits of any", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("kind you should NEVER assume that there is a loop. It is more likely for each", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("split to be a single path than for any loops to exist. This is one of the most", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("important map reading rules", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("so DO NOT forget it!!", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial14() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(8);
		g.drawString("More Loops", (int)(frame.getWidth()/2-120), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("As you can see, the 5 tightly packed splits on that map ended up being main", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("path and 4 independent pots, no loops involved. A simpler way to understand", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("this rule is that in every map you expect 6 splits off of main path from pots,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("but you can only have a maximum of 2 splits off of main path due to a loop,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("and a map is much more likely to have no main path loop than it is to have", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("less than 5 flames.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*2)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*2)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial15() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("U, W, and S Maps", (int)(frame.getWidth()/2-175), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("The three most common map types make up about 95% of all the maps out", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("there, and they are U maps, W Maps, and S maps. If you can identify what map", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("type you are dealing with, you will be able to make most decisions much", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("more easily.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		
		paintButtons();
	}
	
	public static void paintTutorial16() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(9);
		g.drawString("U Maps", (int)(frame.getWidth()/2-80), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("The most common type of map you'll see is the U map. Below you can see an", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("example of a U map, and you can see that main path starts in the middle,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("loops around part of the outside, and then ends up back near the middle. But", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the most important feature is that all of the pot splits are on the same side", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("of main path; the outside. You can tell this map will probably be a U map", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("because of all the early", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("left splits when going up,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("from spawn, and because it", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("goes very far up before", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		g.drawString("turning around. Once you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5100));
		g.drawString("see this, all you have to do", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5500));
		g.drawString("is see what side the splits", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5900));
		g.drawString("are on and hug the other wall.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.6300));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*6)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial17() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(10);
		g.drawString("W Maps", (int)(frame.getWidth()/2-85), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Next we have the W map, which is extremely similar in style to the U map,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("except that it has a single pot room on the inside. This is the second most", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("common map type, and often is the one that causes the most pots. In this map,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("we can tell this is a W not a U because the inward split at 4 is too early", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("in the map for main path to cut in. Telling these apart will become easier", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("once you get further through", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("the tutorial.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial18() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(11);
		g.drawString("S Maps", (int)(frame.getWidth()/2-80), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Third, we have the S map. This one is less common that U and W, but still", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("worth mentioning. You get an S map like the one below when a map starts out", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("with all pot splits on one side and at some point they switch to the other", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("side. You can see that splits 1-5 are on the left side of main path, but 6-7", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("are on the right side and main path changes it's curving direction. S maps are", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("most common when main path", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("spends a long time near", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("spawn.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("Also, there can only be up", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5400));
		g.drawString("to 6 pot/troom splits, so", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5800));
		g.drawString("can you explain why it's", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.6200));
		g.drawString("ok for this map to have 7", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.6600));
		g.drawString("splits off main path?", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.7000));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial19() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Chapter 2: Spawn", (int)(frame.getWidth()/2-180), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Next I'll go over the decisions you should make when picking a direction to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("go from spawn. Of all map reading facets, spawn is the most straghtforward", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("because it follows clear, sequential rules, and because it is the only room", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("where you can always peek all directions. For each step, see if your map's", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("spawn has what that step dictates and if it does, do it. If that step doesn't", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("provide a clear option, move to the next step. ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("Step 1. If you see a T-split take it", (int)(frame.getWidth()/2-400), (int)(frame.getHeight()*.4600));
		g.drawString("Step 2. Take the direction with the most splits", (int)(frame.getWidth()/2-400), (int)(frame.getHeight()*.5000));
		g.drawString("Step 3. Take the direction from spawn with the most space", (int)(frame.getWidth()/2-400), (int)(frame.getHeight()*.5400));
		g.drawString("Step 4. Take the direction with a side split over straight", (int)(frame.getWidth()/2-400), (int) (frame.getHeight()*.5800));
		
		paintButtons();
	}
	
	public static void paintTutorial20() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(12);
		g.drawString("Step 1. T-Split", (int)(frame.getWidth()/2-150), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("When you load into spawn, pillars have to be broken to open each direction.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("Use this time to peek each direction to see the side splits from each path.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("If you see no side splits, you know 100% that the room has a split on the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("far side and goes straight. For step 1, if you peek a direction and see a ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("split on both sides, like the up split here, go that way. It will be right ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("99.9% of the time. The", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("only other possibility", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("is a pot that has a loop", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("right off spawn, and we", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		g.drawString("never assume a loop.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5100));
		
		int x = 4;
		int y = 4;
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x+1)+48+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(y+1)+48+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		g.fillRect((int)(shiftx+scale*(96*(y-1)-17+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial21() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(13);
		g.drawString("Step 2. Splits", (int)(frame.getWidth()/2-140), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("If you don't see a T-split off of spawn, and you have the ability to see the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("far end of a room (or know someone who does), then you can use this step.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("If you see a room that has both a side split and a straight split, take that", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("split. Again, the only chance of it being wrong is a loop right off of spawn,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("and we never assume loops exist unless proven wrong. If you can't peek the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("far end of each room, go to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("the next step.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		paintButtons();
	}
	
	public static void paintTutorial22() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(14);
		g.drawString("Step 3. Space", (int)(frame.getWidth()/2-140), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("This is the most commonly used step because T-splits are uncommon and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("most of the time you can't see the far side of a room. Once you are here you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("go the direction from spawn that goes towards the most space. Here, you can", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("see spawn is bottom right, so you want to go either up or left. You'd pick up", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("because the up split continues up while the left split turns down into less", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("space. If there were no up", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("split, you would go left.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		int x = 5;
		int y = 5;
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x+1)+48+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(y+1)+48+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		g.fillRect((int)(shiftx+scale*(96*(y-1)-17+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial23() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(15);
		g.drawString("Step 4. Last Resort", (int)(frame.getWidth()/2-195), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("If you are ever in a situation like this, where there is equal space on both", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("sides and you can't peek the far end of each room, always go the direction", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("with a side split. If there is a straight split too, it's the correct way to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("go, and if there isn't one then it is still slightly more likely for reasons", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("I will discuss later. If spawn has only 1 split, use these rules on the first", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("room with splits, and if none", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("of these rules work you'll", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("have to guess.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		int x = 4;
		int y = 4;
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x+1)+48+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(y+1)+48+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		g.fillRect((int)(shiftx+scale*(96*(y-1)-17+shiftroomx)), (int)(shifty+scale*(96*x+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial24() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Chapter 3. Reading with Space", (int)(frame.getWidth()/2-295), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("This is going to be a really short chapter because this is both a really", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("simple and not very effective way of map reading. However, it is useful to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("practice this because it will come in handy later. The entire premise of", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("reading based on space is just go towards whatever has the most space. This", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("technique is mostly employed in spawn and room 1 of main path, and in rooms", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("10-11 right before defender. I've already discussed how to use this tactic", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("in spawn, so here I'll talk about how to use it near defender.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		paintButtons();
	}
	
	public static void paintTutorial25() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(16);
		g.drawString("Defender", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("In the last 2 rooms or so before defender, your goal is to get to the closest", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("3x3 open area. For example, look at this split in room 10. On the left there", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("is enough space to have the rest of main path, but there is absolutely no 3x3", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("area so right must be the correct decision here.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial26() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(17);
		g.drawString("Defender", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now that you've gone right, you get to this split. First of all we are 12 rooms", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("into the halls and we haven't seen defender yet so there must have been a", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("loop earlier. since we can only have 1 loop and it has to be at room 2, we", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("know defender must be next room. If we go to the right, there is not enough", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("space to have defender so we must go down, where there is exactly a 3x3 area", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("free of rooms.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*4)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial27() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(18);
		g.drawString("Defender", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("And sure enough, down is defender. This strat is usually encompassed in more", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("complicated strategies, but is great if you are doing something like a fullskip", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("where you need to make quick decisions and don't have time to think things", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("through. Also, if you decide to only read with space and not anything better,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("you focus on ending at room 11 next to an open 3x3 area, and not getting", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("trapped anywhere small.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("12", (int)(shiftx+scale*(15+shiftroomx+96*5)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial28() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(19);
		g.drawString("Defender", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("A very common example of this is when you are in one corner near the end of", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("the map (rooms 8+) and most of the space available is in another corner. In", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("this case you pretty much want to run straight towards the open corner and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("only take detours when necessary. This map is a good example because in", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("room 8 you are top right and you need to get top left. The best decision is to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("go straight left until you hit", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("defender or have to turn.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*4)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial29() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Chapter 4: Reading with Splits", (int)(frame.getWidth()/2-295), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Here we are finally getting to the good stuff. Pretty much all good map readers", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("call directions based off of the splits already seen in the map. This is also", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("the most common tool used for analysis of maps outside of the game. When", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("you navigate a map, this is your primary tool. The basic premise is every", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("pot split you see off of main path takes up space, and your job is to pick", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("the splits that leave you in as open of an area as possible at room 11. This", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("is also the part of map reading that takes the most practice, which is what", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("this program is for, so if you want to get good then practice practice practice.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		paintButtons();
	}
	
	public static void paintTutorial30() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(20);
		g.drawString("Occupied Space", (int)(frame.getWidth()/2-160), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("The first thing to address is how much space splits occupy. When you see a", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("split off main path, you should assume that it sticks out 2 rooms in the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("direction of the split (true average 2.049), and it takes up 3 rooms of space", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("total. Below you can see from each pot split I labeled the space we assume", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("the splits occupy. Always assume 3 rooms total, and assume that the first", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("2 rooms are straight out when", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("possible. The third room is", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("then any direction from the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("second. Usually pots will not", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		g.drawString("look exactly like this, but", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5100));
		g.drawString("this way of guessing gives", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5500));
		g.drawString("the best approximation of", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5900));
		g.drawString("space used.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.6300));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*8)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*8)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*5)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*3)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.setColor(Color.PINK);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*8)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*8)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*7)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*8)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.setColor(Color.YELLOW);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*8)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*8)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*7)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*8)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.setColor(Color.CYAN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.setColor(Color.MAGENTA);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*2)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*3)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.setColor(Color.BLUE);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*1+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*0+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*2)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*4)), (int)(shifty+scale*(67+96*0+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial31() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(21);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("For example, take this split at room 9. It looks like the decision is 50/50,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("but let's see if we can explain why one choice is better. First, try to figure", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("out what the correct decision is yourself. Do this by imagining what space", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the left split and down split would take up as pots, and seeing if you can", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("fit defender anywhere.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial32() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(21);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now let's examine what the left split would look like first. We want it to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("be 3 rooms long, so it cannot fit in the purple space because that would force", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("it to be only 2 long. We end up with the red path below. Now the down split", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("has to be defender, and defender+MBC take up a 3x3. Down down left won't", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("work because it will hit room 3 of the pots, and down left down goes off the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("map as shown, so defender", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("doesn't fit anywhere!", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.setColor(Color.MAGENTA);
		g.drawString("X", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.setColor(Color.CYAN);
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*6)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawImage(Paint.Idef, (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(17+96*6+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*5)), (int)(shifty+scale*(79+96*6+shiftroomy)), 0, 0, 131, 131, null);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*4)), (int)(shifty+scale*(96*6+shiftroomy)), (int)(scale*(288)), (int)(scale*(288)));
		
		paintButtons();
	}
	
	public static void paintTutorial33() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(21);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now, imagine that down is the pot split. Since there is a lot of open space", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("we assume the pots goes straight 2 rooms and the 3rd can be anywhere off of ", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("room 2, as shown in red. The left split has to be defender now, and the only", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("way to fit it is as shown in light blue. While this overlaps one possible", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("ending of our pot guess, it doesn't overlap all of them and we know that left", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("is the correct decision.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*6)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("?", (int)(shiftx+scale*(29+shiftroomx+96*7)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.setColor(Color.CYAN);
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawImage(Paint.Idef, (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(17+96*5+shiftroomy)), (int)(shiftx+scale*(79+shiftroomx+96*4)), (int)(shifty+scale*(79+96*5+shiftroomy)), 0, 0, 131, 131, null);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3)), (int)(shifty+scale*(96*5+shiftroomy)), (int)(scale*(288)), (int)(scale*(288)));
		
		paintButtons();
	}
	
	public static void paintTutorial34() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(22);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("And as you can see, left was correct and the down split pots even resembled", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("one of our guesses. Without taking into account the area that pots take up", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("the down split seems more likely, but by analyzing the situation left becomes", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the clearly better option.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*2+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*1+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial35() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(23);
		g.drawString("Example 2", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Here's another common scenario where predicting directions based on used", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("space is helpful. Take a look at this map. If we just look at the black area", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("above main path and below, it looks roughly equal, so this appear to be a", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("50/50 choice. But if we look at the splits so far, 2 go up and 1 goes left.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("The top of the map has 2 splits already taking up space, while the bottom is", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("actually empty. With this in", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("mind, going down is clearly", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("correct.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*(3-1)-17+shiftroomx)), (int)(shifty+scale*(96*3+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial36() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(24);
		g.drawString("Example 2", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("And as you can see, down was the correct decision. This is a perfect example", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("of a very common strategy, where one watches to see where the pot splits are", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("goes the other way. It works well because the pot splits take up space and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("all one has to do is go toward the most space while keeping in mind what map", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("space is actually empty and what map space just looks empty.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*6)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial37() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(25);
		g.drawString("Segmenting the Map", (int)(frame.getWidth()/2-200), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Another way of utilizing the same technique in a slightly different way is", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("done by segmenting the map into 9 regions as shown below. Then, whetever", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("you see a pot split go into one of those regions you can mentally mark the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("region as occupied and therefore impassable. A perfect example is the last", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("map, where you mark off the top center when you see the up splits and know", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("you have to go down before", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("you even get all the way", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("to the right.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		g.setColor(Color.GREEN);
		for (int i = 0; i < 9; i++) {
			g.drawRect((int)(shiftx+scale*(shiftroomx+96*((int)(i/3))*3+4)), (int)(shifty+scale*(96*(i%3)*3+shiftroomy+4)), (int)(scale*(279)), (int)(scale*(279)));
		}
		
		paintButtons();
	}
	
	public static void paintTutorial38() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(26);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now lets look at this map. The red areas are currently occupied, and we have", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("4 more rooms until defender. We are deciding between going right at room 7", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("or going down. Also notice the bottom area is slightly larger than the area", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("on the right, being 4 rooms tall instead of 3 rooms wide. Let's take a look", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("at what each split will cut us off from should it be pots.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(279)), (int)(scale*(375)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(183)), (int)(scale*(375)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(279)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(279)), (int)(scale*(183)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(279)), (int)(scale*(375)));
		g.setColor(Color.RED);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(279)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(279)), (int)(scale*(183)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(183)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(183)), (int)(scale*(183)));
		
		paintButtons();
	}
	
	public static void paintTutorial39() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(26);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("If right is pots, you cut off the right and top right from main path, so it", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("is restricted to the bottom 3 areas. If down is pots, you cut off the bottom", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("and bottom left areas and main path is restricted to be on the 3 rightmost", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("areas. Since the bottom areas are bigger that the ones on the right, we would", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("rather cut off the right ones so main path is left with more space. The", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("correct decision would", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("therefore be down.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(279)), (int)(scale*(375)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(279)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(279)), (int)(scale*(375)));
		g.setColor(Color.RED);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(279)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*0+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(279)), (int)(scale*(183)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*0+4)), (int)(scale*(183)), (int)(scale*(279)));
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(183)), (int)(scale*(183)));
		g.setColor(Color.CYAN);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*5+4)), (int)(shifty+scale*(96*3+4)), (int)(scale*(279)), (int)(scale*(183)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*6)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.setColor(Color.MAGENTA);
		g.drawRect((int)(shiftx+scale*(shiftroomx+96*3+4)), (int)(shifty+scale*(96*5+4)), (int)(scale*(183)), (int)(scale*(375)));
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial40() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(27);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("And as you can see, down was the correct decision, leading to defender.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("This strategy is most useful when you are forced to map read quickly, since", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("it is easy to think about quickly but doesn't have the same precision that", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the previous method does. Either way, I recommend practicing both to find", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("what works for you.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial41() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Peeking", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Still on the topic of splits, peeking while using splits is a powerful tool,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("because it allows us to refine our guesstimation of what space a split would", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("take up if it was pots. When navigating a real halls, you won't need to peek", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("for most splits because most splits are easy to predict without peeking, and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("peeking takes time away from actually clearing the halls. But any splits that", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("are not clear and give you the opportunity to peek, you should peek. If you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("are only interested in learning how to map read during fullskips, go ahead", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("and skip past this section to chapter 5", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		paintButtons();
	}
	
	public static void paintTutorial42() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(31);
		g.drawString("Basic Strategy", (int)(frame.getWidth()/2-155), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("When peeking, you combine the rules of spawn decisions and split-based", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("decisions. If you see a T-split take it. If you see a peeked room has more", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("than 1 split take it. Otherwise go the direction that you would go based on", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the standard split based process. The most common example is peeking splits", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("and seeing that 2 splits go one way, left for example, and one split goes", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("right. You'd go right because", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("you are less cramped without", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("a pot split next to main path.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*2-17+shiftroomx)), (int)(shifty+scale*(96*(3-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(2+1)+48+shiftroomx)), (int)(shifty+scale*(96*3+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(125)));
		g.fillRect((int)(shiftx+scale*(96*(2-1)-17+shiftroomx)), (int)(shifty+scale*(96*3+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial43() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(28);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("As an example, this map has no immediate best path, Most people would go", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("right because it goes towards open space, but the location of spawn forces", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("the right split to go up 1 room if it goes to defender. Really it's about 60/40", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("in favor of the right split. For this case we make our lives easier and instead", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("of guessing we will go ahead and peek each room.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		
		g.setColor(Color.BLACK);
		//g.fillRect((int)(shiftx+scale*(96*y-17+shiftroomx)), (int)(shifty+scale*(96*(x+1)+48+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(4+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial44() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(29);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now that we can see where the right split goes we can be 100% sure that up", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("is the correct decision. The right split could only be defender if the 10th", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("room is a loop, and we always assume there are no loops until proven wrong.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("In addition, the standard guess for the right pots split follows the red", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("numbers as shown, and allows space for defender+MBC if up is main path.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		//g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		//g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*(0+1)+48+shiftroomx)), (int)(shifty+scale*(96*3+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		g.fillRect((int)(shiftx+scale*(96*0+3+shiftroomx)), (int)(shifty+scale*(96*(3-1)-17+shiftroomy)), (int)(scale*(110)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(4+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		g.setColor(Color.RED);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial45() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(30);
		g.drawString("Example 1", (int)(frame.getWidth()/2-110), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Sure enough, the up split goes to defender. and the pot split follows our", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("guess exactly. Additionally, you can see there was 1 more split off of main", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("path before defender. If you were counting splits, then that could help you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("decide to go up instead of right because there is less space for a last split", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("if you go right than if you go up.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*1)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*0)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*0)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*0)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*1)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		
		g.setColor(Color.BLACK);
		
		paintButtons();
	}
	
	public static void paintTutorial46() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Chapter 5: Rules of Thumb", (int)(frame.getWidth()/2-260), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Along with all the other methods discussed so far, there are several rules", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("of thumb you can use while reading maps. These aren't exact or analyzeable", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("like what I've discussed so far, but they tend to hold true on most maps.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("You can use these primarily when you need to make a quick decision and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("don't have time to think stuff through all the way (like during a fullskip).", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.setColor(Color.RED);
		g.drawString("If on the other hand you get a chance to use a more accurate method, you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("should use that method instead.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		
		paintButtons();
	}
	
	public static void paintTutorial47() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(32);
		g.drawString("Don't linger in Mid", (int)(frame.getWidth()/2-175), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Just about every map will at some point leave the middle of the map for a", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("time, and this usually happens right around the middle (rooms 4-8). To deal", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("with this, once you get to room 3 or 4, you want to be leaving the middle of", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("the map and go 1-2 rooms away from the edge for some time. In this map, the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("up split would spend too much time hugging middle, so down is the correct", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("decision.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial48() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(33);
		g.drawString("Cut in Later", (int)(frame.getWidth()/2-120), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("On the contrary of the last point, it is best to go towards middle near the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("end (rooms 8-11). This is because the defender+MBC 3x3 is much more likely", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("to have the defender side facing inwards than outwards. To get to defender,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("you therefore want to run towards the middle IF you don't have a better", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("reason for another direction. You also don't want to cut in too early unless", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("forced to. In this map, you'd", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("go left because you are at", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("room 8 so it's late enough to", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("start going middle.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*6)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*7)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*7)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial49() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(34);
		g.drawString("More Early Splits", (int)(frame.getWidth()/2-174), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Another trend in maps is most pot splits happen earlier in the map, which is", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("caused by whatever algorithm DECA uses to add pot splits. To put it into", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("numbers, when you are halfway through the map (room 6 of main path), you", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("will have seen 4/6 of the pot splits on average. This map is a great example,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("with a split off of the first 4 rooms and then the last to splits on rooms", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("7 and 11. For map reading, this", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("implies that most decisions", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("are made early on.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(66+96*2+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*3+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(67+96*3+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*2)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*4+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*3)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*5)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial50() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Have Confidence", (int)(frame.getWidth()/2-160), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("In a real halls, many people newer to map reading are afraid of hitting pots", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("or making a mistake. I'd like to point out that 1. even the best map readers,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("making all the right decisions, hit a pots every 2nd or 3rd run, and 2. if", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("you hit a pot it's no a big deal. Since most people do halls in large groups,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("the group will probably get all up on whoever made the calls, but it's all", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("fun and games. What's important is that you are confident in your decisions", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("and are willing to adjust to the map as you uncover it. At a split, if you can", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("find 1 or 2 good reasons to go in a particular direction, it's probably right", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("and there's no use overthinking all the other possibilities.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		
		paintButtons();
	}
	
	public static void paintTutorial51() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("Chapter 6: Putting it all Together", (int)(frame.getWidth()/2-310), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("At this point, We've covered all of the individual rules, patterns, and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("strategies needed to successfully map read. So for this chapter, I will be", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("going through an example map and talking about my thought process from", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("start to finish. However, as I've stated before, everyone map reads differently,", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("so you should use what makes the most sense to you and not just follow what", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("makes the most sense to me.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		
		paintButtons();
	}
	
	public static void paintTutorial52() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(35);
		g.drawString("Spawn", (int)(frame.getWidth()/2-70), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("At spawn, I peek all rooms and see there is a T-split on the left so I decide", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("to go left. As a bonus, it's also going towards the most space.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		g.fillRect((int)(shiftx+scale*(96*(5-1)-17+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		paintButtons();
	}
	
	public static void paintTutorial53() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(35);
		g.drawString("Room 1", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Here, I see both the up split and down split near spawn. The up split is much", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("closer to room 1 than the down split is, and will go further upward, so I", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("decide to go down from room 1 where there is more available space.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial54() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(36);
		g.drawString("Room 4", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Although I don't want to cut in early, having down as main path would be", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("way too cramped by a left pot split. I decide to go left so that main path", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("doesn't get trapped in the bottom of the map.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial55() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(37);
		g.drawString("Room 7", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("The down split is obviously wrong, but up and left both look plausible.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("Since I don't have a good reason against either, I'll go left because it's too", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("early to start cutting in towards middle at room 7.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial56() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(38);
		g.drawString("Room 8", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Now at room 8 I'm getting late enough in the map to start going towards mid.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("Also, if main path went left, having 2 pot splits pointing up would leave", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("very little room for defender and MBC.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial57() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(39);
		g.drawString("Room 8", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("At this point I see that the past 3 rooms were all part of a loop and still room", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("8. I continue upwards because I am only a few rooms from defender and no", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("other choice will allow me near a 3x3 area.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial58() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(40);
		g.drawString("Room 10", (int)(frame.getWidth()/2-90), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("I expected it to continue up straight to defender, but it's not a problem.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("I have exactly 1 more room before defender, so I know it must be right and", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("up to get there and have enough room.", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*1)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial59() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(41);
		g.drawString("Defender", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("We made it! Now that the hard part is done all we have to do is fight two", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("of hardest bosses in the game back to back! Then come back and do it again", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("and again....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("and again....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("and again....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("and again....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("and....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("....", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(shiftx+scale*(96*5-17+shiftroomx)), (int)(shifty+scale*(96*(4-1)-17+shiftroomy)), (int)(scale*(130)), (int)(scale*(66)));
		g.fillRect((int)(shiftx+scale*(96*(5+1)+48+shiftroomx)), (int)(shifty+scale*(96*4+shiftroomy-17)), (int)(scale*(66)), (int)(scale*(130)));
		
		f = new Font("SansSerif", Font.BOLD, (int)(56*scale));
		g.setFont(f);
		g.setColor(Color.GREEN);
		g.drawString("1", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("2", (int)(shiftx+scale*(32+shiftroomx+96*4)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("3", (int)(shiftx+scale*(32+shiftroomx+96*5)), (int)(shifty+scale*(67+96*5+shiftroomy)));
		g.drawString("4", (int)(shiftx+scale*(30+shiftroomx+96*5)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("5", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*6+shiftroomy)));
		g.drawString("6", (int)(shiftx+scale*(30+shiftroomx+96*4)), (int)(shifty+scale*(67+96*7+shiftroomy)));
		g.drawString("7", (int)(shiftx+scale*(32+shiftroomx+96*3)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*7+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*2)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("8", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*6+shiftroomy)));
		g.drawString("9", (int)(shiftx+scale*(32+shiftroomx+96*1)), (int)(shifty+scale*(66+96*5+shiftroomy)));
		g.drawString("10", (int)(shiftx+scale*(15+shiftroomx+96*1)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		g.drawString("11", (int)(shiftx+scale*(17+shiftroomx+96*2)), (int)(shifty+scale*(66+96*4+shiftroomy)));
		
		paintButtons();
	}
	
	public static void paintTutorial60() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		g.drawString("The End", (int)(frame.getWidth()/2-100), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("That's it! That's just about everything you need to know to be able to map", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("read well. Now all you need is practice practice practice. If you want to learn", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("more or want to have someone watch you practice a few maps, go find any", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("any raid leader on any halls discord server and ask them for lessons, tips, or", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("clarification. Most of us love to talk about map reading and will gladly answer", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("your questions, and those who don't should refer you to someone who", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("does. As a starter, feel free to find me on discord @Nacnud#3400 and ask", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("anything map reading related. Also if you think anything is unclear in the", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		g.drawString("tutorial, or you have a suggestion for something to add to the program, please", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4700));
		g.drawString("tell me! Otherwise, good luck with your practice!", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.5100));
		
		paintButtons();
	}
	
	public static void paintTutorialEnd() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 70);
		g.setFont(f);
		g.drawString("That's it so far!", (int)(frame.getWidth()*.5-250), (int)(frame.getHeight()*.460));
		
		paintButtons();
	}
	
	public static void paintTutorialn() {
		Graphics g = i.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		Font f = new Font("SansSerif", Font.BOLD, 40);
		g.setFont(f);
		
		tutorialMap(0);
		g.drawString("", (int)(frame.getWidth()/2-50), (int)(frame.getHeight()*.0700));
		f = new Font("SansSerif", Font.BOLD, 24);
		g.setFont(f);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1500));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.1900));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2300));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.2700));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3100));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3500));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.3900));
		g.drawString("", (int)(frame.getWidth()/2-450), (int)(frame.getHeight()*.4300));
		
		paintButtons();
	}
}

//Created by Nacnudd from the Pub Halls Discord server.