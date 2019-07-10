package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.Paint;

//this class will store, read, and provide random maps to Main


public class Maps {
	
	private int[][][] a;
	private Random r;
	private boolean[] used, used2;
	private int len, len2;
	private int[][][] in;
	
	public Maps() throws IOException {
		r = new Random();
		List<String> l = new ArrayList<String>();
		
		InputStream in = Maps.class.getResourceAsStream("../resources/Maps.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		while(br.ready()) {
			for (int i = 0; i < 9; i++) {
				l.add(br.readLine());
			}
			br.readLine();
			br.readLine();
		}
		
		br.close();
		
		len = (int) l.size()/9;
		a = new int[len][9][9];
		used = new boolean[len];
		String[] b;
		
		for (int i = 0; i < len; i++) {
			for(int j = 0; j < 9; j++) {
				b = l.get(i*9+j).split(",");
				for(int k = 0; k < 9; k++) {
					a[i][j][k] = Integer.parseInt(b[k]);
				}
			}
		}
	}
	
	public void importMap(File f1) throws IOException {
		List<String> l = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(f1));
		
		while(br.ready()) {
			for (int i = 0; i < 9; i++) {
				l.add(br.readLine());
			}
			br.readLine();
			br.readLine();
		}
		
		br.close();
		
		len2 = (int) l.size()/9;
		in = new int[len2][9][9];
		used2 = new boolean[len2];
		String[] b;
		
		for (int i = 0; i < len2; i++) {
			for(int j = 0; j < 9; j++) {
				b = l.get(i*9+j).split(",");
				for(int k = 0; k < 9; k++) {
					in[i][j][k] = Integer.parseInt(b[k]);
				}
			}
		}
	}
	
	public Room[][] getMap(){
		/*
		for (int i = 0; i < len; i++) {
			if(!used[i]) {
				used[i] = true;
				return a[i];
			}
		}
		return a[0];
		*/
		
		boolean available = false;
		int pick;
		Room[][] retMap = new Room[9][9];
		for(int i = 0; i < len; i++) {
			if(!used[i]) {
				available = true;
			}
		}		
		if(available) {
			pick = r.nextInt(len);
			while(used[pick]) {
				pick = r.nextInt(len);
			}
			used[pick] = true;
			for (int i = 0; i < a[pick].length; i++) {
				for (int j = 0; j < a[pick][i].length; j++) {
					int num = a[pick][i][j];
					Room r = convertRoom(num, i, j);
					retMap[i][j] = r;
				}
			}
			return retMap;
		}
		else {
			for(int i = 0; i < len; i++) {
				used[i] = false;
			}
			pick = r.nextInt(len);
			used[pick] = true;
			for (int i = 0; i < a[pick].length; i++) {
				for (int j = 0; j < a[pick][i].length; j++) {
					int num = a[pick][i][j];
					Room r = convertRoom(num, i, j);
					retMap[i][j] = r;
				}
			}
			return retMap;
		}
		
	}
	
	public Room[][] getLoadedMap(){
		boolean available = false;
		int pick;
		Room[][] retMap = new Room[9][9];
		for(int i = 0; i < len2; i++) {
			if(!used2[i]) {
				available = true;
			}
		}		
		if(available) {
			pick = r.nextInt(len2);
			while(used2[pick]) {
				pick = r.nextInt(len2);
			}
			used2[pick] = true;
			for (int i = 0; i < a[pick].length; i++) {
				for (int j = 0; j < a[pick][i].length; j++) {
					int num = a[pick][i][j];
					Room r = convertRoom(num, i, j);
					retMap[i][j] = r;
				}
			}
			return retMap;
		}
		else {
			for(int i = 0; i < len2; i++) {
				used2[i] = false;
			}
			pick = r.nextInt(len2);
			used2[pick] = true;
			for (int i = 0; i < a[pick].length; i++) {
				for (int j = 0; j < a[pick][i].length; j++) {
					int num = a[pick][i][j];
					Room r = convertRoom(num, i, j);
					retMap[i][j] = r;
				}
			}
			return retMap;
		}
	}
	
	public Room convertRoom(int num, int i, int j) {
		Room r = new Room();
		r.x = i;
		r.y = j;
		if (num < 500 && num > 250) { //start room
			r.start = true;
			r.seen = true;
			Paint.pos[0] = i;
			Paint.pos[1] = j;
		}
		if (num > 500 && num < 750) { //pot room
			r.pot = true;
		}
		if (num > 750 && num < 999) { //defender
			r.defender = true;
		}
		if (num % 2 == 1) {
			r.up = true;
		}
		if (num % 3 == 1) {
			r.right = true;
		}
		if (num % 5 == 1) {
			r.down = true;
		}
		if (num % 7 == 1) {
			r.left = true;
		}
		return r;
	}
}

//Created by Nacnudd from the Pub Halls Discord server.