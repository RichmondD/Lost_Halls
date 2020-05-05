package model;
import java.io.BufferedReader;
import java.io.File;
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
	
	private Random r;
	private boolean[] used;
	private int len;
	private int[][][] in;
	private int[][][] t;
	private int[][] m, mainpath;
	private int mainlength = 10;
	private boolean mainloop, potloop, forceloop;
	
	public Maps() throws IOException {
		r = new Random();
		String[] b;
		
		List<String> l2 = new ArrayList<String>();
		
		InputStream in2 = Main.class.getResourceAsStream("/resources/TutorialMaps.txt");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		while(br2.ready()) {
			for (int i = 0; i < 9; i++) {
				l2.add(br2.readLine());
			}
			br2.readLine();
			br2.readLine();
		}
		
		br2.close();
		
		int len3 = (int) l2.size()/9;
		t = new int[len3][9][9];
		for (int i = 0; i < len3; i++) {
			for(int j = 0; j < 9; j++) {
				b = l2.get(i*9+j).split(",");
				for(int k = 0; k < 9; k++) {
					t[i][j][k] = Integer.parseInt(b[k]);
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
		
		len = (int) l.size()/9;
		in = new int[len][9][9];
		used = new boolean[len];
		String[] b;
		
		for (int i = 0; i < len; i++) {
			for(int j = 0; j < 9; j++) {
				b = l.get(i*9+j).split(",");
				for(int k = 0; k < 9; k++) {
					in[i][j][k] = Integer.parseInt(b[k]);
				}
			}
		}
	}
	
	public Room[][] getMap(){
		Room[][] retMap = new Room[9][9];
		m = createMap();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				Room r = convertRoom(m[i][j], i, j);
				retMap[i][j] = r;
			}
		}
		return retMap;
	}
	
	public Room[][] getLoadedMap(){
		
		Room[][] retMap = new Room[9][9];
		for (int k = 0; k < 100; k++) {
			if(!used[k]) {
				for (int i = 0; i < in[k].length; i++) {
					for (int j = 0; j < in[k][i].length; j++) {
						Room r = convertRoom(in[k][i][j], i, j);
						retMap[i][j] = r;
					}
				}
				used[k] = true;
				System.out.println(k);
				return retMap;
			}
		}
		return retMap;
	}
	
	public Room[][] getTutorialMap(int n) {
		Room[][] retMap = new Room[9][9];
		for (int i = 0; i < t[n].length; i++) {
			for (int j = 0; j < t[n][i].length; j++) {
				Room r = convertRoom(t[n][i][j], i, j);
				retMap[i][j] = r;
			}
		}
		return retMap;
	}
	
	private Room convertRoom(int num, int i, int j) {
		Room r = new Room();
		r.x = i;
		r.y = j;
		//System.out.println(i+" "+j+" "+num);
		if (num == 1) {
			r.border = true;
		}
		if (num < 500 && num > 250) { //start room
			r.start = true;
			//r.seen = true;
			Paint.pos[0] = i;
			Paint.pos[1] = j;
		}
		else if (num > 500 && num < 750) { //pot room
			r.pot = true;
		}
		else if (num > 750 && num < 999) { //defender
			r.defender = true;
		}
		else if (num == 126) {
			r.troom = true;
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
	
	private int[][] createMap() {
		m = new int[9][9];
		mainpath = new int[9][9];
		mainloop = false;
		potloop = false;
		m[4][4] = 420;
		
		createNextRoom(4, 4, 0, 0, mainlength, false);
		findMainPath(4, 4, 1);
		createPots();
		cleanMap();
		
		return m;
	}
	
	private void createPots() {
		while(!createNewPot(4, 4, 0, true)) {
			
		}
		int potsplaced = 0;
		boolean potspaceavailable = true;
		int tries = 0;
		while(potspaceavailable && potsplaced < 5) {
			if(createNewPot(4, 4, 0, false)) { potsplaced ++;}
			if(tries > 30) {
				if(createNewPot(4, 4, 4, false)) { potsplaced ++;}
				else if(createNewPot(4, 4, 3, false)) { potsplaced ++;}
				else if(createNewPot(4, 4, 2, false)) { potsplaced ++;}
				else {potspaceavailable = false;}
			}
			tries ++;
		}
	}
	
	private boolean createNewPot(int x, int y, int forcepots, boolean troom) {
		boolean works;
		if((r.nextFloat() < 1.0/mainlength) || (forcepots > 0)) {
			if(troom) { works = createNextRoom(x, y, 1, 0, r.nextInt(3)+2, false);}
			else if(forcepots > 0) { works = createNextRoom(x, y, 2, 0, r.nextInt(forcepots-1)+2, false);}
			else { works = createNextRoom(x, y, 2, 0, r.nextInt(3)+2, false);}
			if(works) { return true;}
		}
		
		if(m[y][x]%2 == 1) {
	        if(mainpath[y-1][x] == mainpath[y][x]+1) {
	            works = createNewPot(x, y-1, forcepots, troom);
	            if(works) { return true;}
	        }
		}
		if(m[y][x]%3 == 1) {
	        if(mainpath[y][x+1] == mainpath[y][x]+1) {
	            works = createNewPot(x+1, y, forcepots, troom);
	            if(works) { return true;}
	        }
		}
		if(m[y][x]%5 == 1) {
	        if(mainpath[y+1][x] == mainpath[y][x]+1) {
	            works = createNewPot(x, y+1, forcepots, troom);
	            if(works) { return true;}
	        }
		}
		if(m[y][x]%7 == 1) {
	        if(mainpath[y][x-1] == mainpath[y][x]+1) {
	            works = createNewPot(x-1, y, forcepots, troom);
	            if(works) { return true;}
	        }
		}
		
		return false;
	}
	
	private boolean createNextRoom(int x, int y, int pathtype, int depth, int length, boolean isloop) {
		//System.out.println(x+","+y+"  "+depth);
		if(depth == length) {
			if(pathtype == 0) {
				boolean test = placeMBC(x, y);
				return test;
			}
			if(pathtype == 1) {
				if(m[y][x] == 126) {
					return true;
				}
				return false;
			}
			if(pathtype == 2) {
				while(m[y][x] < 500) {
					m[y][x] += 210;
				}
				return true;
			}
			return false;
		}
		
		boolean[] available;
		List<Integer> options;
		int pick;
		boolean works;
		if(isloop) {
			available = new boolean[]{true, true, true, true};
			int count = 0;
			while(available[0] || available[1] || available[2] || available[3]) {
				options = new ArrayList<>();
				for(int i = 0; i < 4; i++) {
					if(available[i]) { 
						count = ((y+((int) (i/2)) > 0 && (m[y+((int) (i/2))-1][x+i%2] == 0)) ? 1:0) + ((y+((int) (i/2)) < 8 && (m[y+((int) (i/2))+1][x+i%2] == 0)) ? 1:0) + ((x+i%2 > 0 && (m[y+((int) (i/2))][x+i%2-1] == 0)) ? 1:0) + ((x+i%2 < 8 && (m[y+((int) (i/2))][x+i%2+1] == 0)) ? 1:0);
						//System.out.println(i+","+count);
						while(count > 0) {
							options.add(i);
							count--;
						}
					}
				}
				if(options.size() < 1) { break;}
				pick = options.get(r.nextInt(options.size()));
				
				if(pick == 0) {
	                works = createNextRoom(x, y, pathtype, depth, length, false);
	                if(!works) { available[0] = false;}
	                else { return true;}
				}
				if(pick == 1) {
	                works = createNextRoom(x+1, y, pathtype, depth, length, false);
	                if(!works) { available[1] = false;}
	                else { return true;}
				}
				if(pick == 2) {
	                works = createNextRoom(x+1, y+1, pathtype, depth, length, false);
	                if(!works) { available[2] = false;}
	                else { return true;}
				}
				if(pick == 3) {
	                works = createNextRoom(x, y+1, pathtype, depth, length, false);
	                if(!works) { available[3] = false;}
	                else { return true;}
				}
			}
			return false;
		}
		
		if(((((!mainloop) && pathtype == 0) || ((!potloop) && (pathtype > 0) && (depth < length-1))) && (r.nextFloat() <= 1.0/mainlength)) || (forceloop && (!mainloop))) {
			if(pathtype == 0) { mainloop = true;}
			if(pathtype > 0) { potloop = true;}
			available = loopSpace(x, y);
			
			while(available[0] || available[1] || available[2] || available[3] || available[4] || available[5] || available[6] || available[7]) {
				options = new ArrayList<>();
				for(int i = 0; i < 8; i++) {
					if(available[i]) { options.add(i);}
				}
				pick = options.get(r.nextInt(options.size()));
				
				if(pick == 0) {
	                createLoop(x-1, y-2);
	                changeRoom(x, y, 0);
	                changeRoom(x, y-1, 2);
	                works = createNextRoom(x-1, y-2, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[0] = false;
	                    createLoop(x-1, y-2);
	                    changeRoom(x, y, 0);
	                    changeRoom(x, y-1, 2);
	                }
	                else { return true;}
				}
	            if(pick == 1) {
	                createLoop(x, y-2);
	                changeRoom(x, y, 0);
	                changeRoom(x, y-1, 2);
	                works = createNextRoom(x, y-2, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[1] = false;
	                    createLoop(x, y-2);
	                    changeRoom(x, y, 0);
	                    changeRoom(x, y-1, 2);
	                }
	                else { return true;}
	            }
	            if(pick == 2) {
	                createLoop(x+1, y-1);
	                changeRoom(x, y, 1);
	                changeRoom(x+1, y, 3);
	                works = createNextRoom(x+1, y-1, pathtype, depth+1, length,true);
	                if(!works) {
	                    available[2] = false;
	                    createLoop(x+1, y-1);
	                    changeRoom(x, y, 1);
	                    changeRoom(x+1, y, 3);
	                }
	                else { return true;}
	            }
	            if(pick == 3) {
	                createLoop(x+1, y);
	                changeRoom(x, y, 1);
	                changeRoom(x+1, y, 3);
	                works = createNextRoom(x+1, y, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[3] = false;
	                    createLoop(x+1, y);
	                    changeRoom(x, y, 1);
	                    changeRoom(x+1, y, 3);
	                }
	                else { return true;}
	            }
	            if(pick == 4) {
	                createLoop(x, y+1);
	                changeRoom(x, y, 2);
	                changeRoom(x, y+1, 0);
	                works = createNextRoom(x, y+1, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[4] = false;
	                    createLoop(x, y+1);
	                    changeRoom(x, y, 2);
	                    changeRoom(x, y+1, 0);
	                }
	                else { return true;}
	            }
	            if(pick == 5) {
	                createLoop(x-1, y+1);
	                changeRoom(x, y, 2);
	                changeRoom(x, y+1, 0);
	                works = createNextRoom(x-1, y+1, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[5] = false;
	                    createLoop(x-1, y+1);
	                    changeRoom(x, y, 2);
	                    changeRoom(x, y+1, 0);
	                }
	                else { return true;}
	            }
	            if(pick == 6) {
	                createLoop(x-2, y);
	                changeRoom(x, y, 3);
	                changeRoom(x-1, y, 1);
	                works = createNextRoom(x-2, y, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[6] = false;
	                    createLoop(x-2, y);
	                    changeRoom(x, y, 3);
	                    changeRoom(x-1, y, 1);
	                }
	                else { return true;}
	            }
	            if(pick == 7) {
	                createLoop(x-2, y-1);
	                changeRoom(x, y, 3);
	                changeRoom(x-1, y, 1);
	                works = createNextRoom(x-2, y-1, pathtype, depth+1, length, true);
	                if(!works) {
	                    available[7] = false;
	                    createLoop(x-2, y-1);
	                    changeRoom(x, y, 3);
	                    changeRoom(x-1, y, 1);
	                }
	                else { return true;}
	            }
			}
			if(pathtype == 0) { mainloop = false;}
			if(pathtype > 0) { potloop = false;}
			if(forceloop) { return false;}
		}
		
		available = new boolean[]{false, false, false, false};
		if(m[y][x]%2 == 0 && y>0) {
			if(m[y-1][x] == 0) { available[0] = true;}
		}
		if(m[y][x]%3 == 0 && x<8) {
			if(m[y][x+1] == 0) { available[1] = true;}
		}
		if(m[y][x]%5 == 0 && y<8) {
			if(m[y+1][x] == 0) { available[2] = true;}
		}
		if(m[y][x]%7 == 0 && x>0) {
			if(m[y][x-1] == 0) { available[3] = true;}
		}
		
		while(available[0] || available[1] || available[2] || available[3]) {
			options = new ArrayList<>();
			for(int i = 0; i < 4; i++) {
				if(available[i]) { options.add(i);}
			}
			pick = options.get(r.nextInt(options.size()));
			
			if(pick == 0) {
	            changeRoom(x, y, 0);
	            changeRoom(x, y-1, 2);
	            works = createNextRoom(x, y-1, pathtype, depth+1, length, false);
	            if(!works) {
	                available[0] = false;
	                changeRoom(x, y, 0);
	                changeRoom(x, y-1, 2);
	            }
	            else { return true;}
			}
	        if(pick == 1) {
	            changeRoom(x, y, 1);
	            changeRoom(x+1, y, 3);
	            works = createNextRoom(x+1, y, pathtype, depth+1, length, false);
	            if(!works) {
	                available[1] = false;
	                changeRoom(x, y, 1);
	                changeRoom(x+1, y, 3);
	            }
	            else { return true;}
	        }
	        if(pick == 2) {
	            changeRoom(x, y, 2);
	            changeRoom(x, y+1, 0);
	            works = createNextRoom(x, y+1, pathtype, depth+1, length, false);
	            if(!works) {
	                available[2] = false;
	                changeRoom(x, y, 2);
	                changeRoom(x, y+1, 0);
	            }
	            else { return true;}
	           }
	        if(pick == 3) {
	            changeRoom(x, y, 3);
	            changeRoom(x-1, y, 1);
	            works = createNextRoom(x-1, y, pathtype, depth+1, length, false);
	            if(!works) {
	                available[3] = false;
	                changeRoom(x, y, 3);
	                changeRoom(x-1, y, 1);
	            }
	            else { return true;}
	        }
		}
		
		if(pathtype == 0 && (depth == mainlength - 2) && (!mainloop) && (!forceloop) && (r.nextFloat() <= .4)) {
	        forceloop = true;
	        works = createNextRoom(x, y, pathtype, depth, length, false);
	        forceloop = false;
	        return works;
		}
	    return false;
	}
	
	private void findMainPath(int x, int y, int depth) {
		if(m[y][x] > 750) { return;}
		mainpath[y][x] = depth;
		
		if(m[y][x]%2 == 1) {
	        if(m[y-1][x] < 1000 && mainpath[y-1][x] == 0) { findMainPath(x, y-1, depth+1);}
		}
	    if(m[y][x]%3 == 1) {
	        if(m[y][x+1] < 1000 && mainpath[y][x+1] == 0) { findMainPath(x+1, y, depth+1);}
	    }
	    if(m[y][x]%5 == 1) {
	        if(m[y+1][x] < 1000 && mainpath[y+1][x] == 0) { findMainPath(x, y+1, depth+1);}
	    }
	    if(m[y][x]%7 == 1) {
	        if(m[y][x-1] < 1000 && mainpath[y][x-1] == 0) { findMainPath(x-1, y, depth+1);}
	    }
	    
	    return;
	}
	
	private boolean placeMBC(int x, int y) {
		if(m[y][x]%5 == 1 && x>0 && x<8 && y>1) {
	        changeRoom(x, y, 2);
	        if(spaceForMBC(x, y-1)) {
	            for(int i = 0; i < 3; i++) {
	                for(int j = 0; j < 3; j++) { m[y+i-2][x+j-1] = -1;}
	            }
	            m[y][x] = 756;
	            return true;
	        }
	        changeRoom(x, y, 2);
		}
		if(m[y][x]%7 == 1 && x<7 && y>0 && y<8) {
	        changeRoom(x, y, 3);
	        if(spaceForMBC(x+1, y)) {
	            for(int i = 0; i < 3; i++) {
	                for(int j = 0; j < 3; j++) { m[y+i-1][x+j] = -1;}
	            }
	            m[y][x] = 960;
	            return true;
	        }
	        changeRoom(x, y, 3);
		}
		if(m[y][x]%2 == 1 && x>0 && x<8 && y<7) {
	        changeRoom(x, y, 0);
	        if(spaceForMBC(x, y+1)) {
	            for(int i = 0; i < 3; i++) {
	                for(int j = 0; j < 3; j++) { m[y+i][x+j-1] = -1;}
	            }
	            m[y][x] = 945;
	            return true;
	        }
	        changeRoom(x, y, 0);
		}
		if(m[y][x]%3 == 1 && x>1 && y>0 && y<8) {
	        changeRoom(x, y, 1);
	        if(spaceForMBC(x-1, y)) {
	            for(int i = 0; i < 3; i++) {
	                for(int j = 0; j < 3; j++) { m[y+i-1][x+j-2] = -1;}
	            }
	            m[y][x] = 910;
	            return true;
	        }
	        changeRoom(x, y, 1);
		}
		
		return false;
	}
	
	private boolean spaceForMBC(int x, int y) {
		boolean fits = true;
		for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
            	if(m[y+i-1][x+j-1] != 0) {fits = false;}
            }
		}
		return fits;
	}
	
	private boolean[] loopSpace(int x, int y) {
		boolean[] available = {false, false, false, false, false, false, false, false};
		
		if(x>0 && y>1) {
	        if(m[y-1][x-1] == 0 && m[y-2][x-1] == 0 && m[y-1][x] == 0 && m[y-2][x] == 0) { available[0] = true;}
		}
		if(x<8 && y>1) {
	        if(m[y-1][x] == 0 && m[y-2][x] == 0 && m[y-1][x+1] == 0 && m[y-2][x+1] == 0) { available[1] = true;}
		}
		if(x<7 && y>0) {
	        if(m[y-1][x+1] == 0 && m[y-1][x+2] == 0 && m[y][x+1] == 0 && m[y][x+2] == 0) { available[2] = true;}
		}
		if(x<7 && y<8) {
	        if(m[y][x+1] == 0 && m[y][x+2] == 0 && m[y+1][x+1] == 0 && m[y+1][x+2] == 0) { available[3] = true;}
		}
		if(x<8 && y<7) {
	        if(m[y+1][x] == 0 && m[y+2][x] == 0 && m[y+1][x+1] == 0 && m[y+2][x+1] == 0) { available[4] = true;}
		}
		if(x>0 && y<7) {
	        if(m[y+1][x-1] == 0 && m[y+2][x-1] == 0 && m[y+1][x] == 0 && m[y+2][x] == 0) { available[5] = true;}
		}
		if(x>1 && y<8) {
	        if(m[y][x-1] == 0 && m[y][x-2] == 0 && m[y+1][x-1] == 0 && m[y+1][x-2] == 0) { available[6] = true;}
		}
		if(x>1 && y>0) {
	        if(m[y-1][x-1] == 0 && m[y-1][x-2] == 0 && m[y][x-1] == 0 && m[y][x-2] == 0) { available[7] = true;}
		}
		
		return available;
	}
	
	private void cleanMap() {
		int[] sidespace = new int[]{0, 0, 0, 0};
		boolean rowempty = true;
		int col = 0;
		while(rowempty) {
			for(int i = 0; i < 9; i++) {
				if(m[col][i] != 0) {
					rowempty = false;
					break;
				}
			}
			if(rowempty) {
				sidespace[0]++;
				col++;
			}
		}
		rowempty = true;
		col = 8;
		while(rowempty) {
			for(int i = 0; i < 9; i++) {
				if(m[i][col] != 0) {
					rowempty = false;
					break;
				}
			}
			if(rowempty) {
				sidespace[1]++;
				col--;
			}
		}
		rowempty = true;
		col = 8;
		while(rowempty) {
			for(int i = 0; i < 9; i++) {
				if(m[col][i] != 0) {
					rowempty = false;
					break;
				}
			}
			if(rowempty) {
				sidespace[2]++;
				col--;
			}
		}
		rowempty = true;
		col = 0;
		while(rowempty) {
			for(int i = 0; i < 9; i++) {
				if(m[i][col] != 0) {
					rowempty = false;
					break;
				}
			}
			if(rowempty) {
				sidespace[3]++;
				col++;
			}
		}
		
		int[][] newm = new int[9][9];
		int shiftx = (int) Math.floor((sidespace[1] - sidespace[3])/2.0);
		int shifty = (int) Math.floor((sidespace[2] - sidespace[0])/2.0);
		for(int i = sidespace[0]; i < 9-sidespace[2]; i++) {
			for(int j = sidespace[3]; j < 9-sidespace[1]; j++) {
				newm[i+shifty][j+shiftx] = m[i][j];
			}
		}
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				m[i][j] = newm[i][j];
			}
		}
		
		if((sidespace[0] + sidespace[2])%2 == 1) {
			for(int i = 0; i < 9; i++) {
				m[8][i] = 1;
			}
		}
		if((sidespace[1] + sidespace[3])%2 == 1) {
			for(int i = 0; i < 9; i++) {
				m[i][8] = 1;
			}
		}
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(m[i][j] > 1000) {
					while(m[i][j] > 250) {
						m[i][j] -= 210;
					}
				}
				if(m[i][j] == -1) {
					m[i][j] = 0;
				}
			}
		}
	}
	
	private void createLoop(int x, int y) {
		changeRoom(x, y, 1);
	    changeRoom(x, y, 2);
	    changeRoom(x, y+1, 0);
	    changeRoom(x, y+1, 1);
	    changeRoom(x+1, y, 3);
	    changeRoom(x+1, y, 2);
	    changeRoom(x+1, y+1, 0);
	    changeRoom(x+1, y+1, 3);
	}
	
	private void changeRoom(int x, int y, int direction) {
		boolean[] sides = {false,false,false,false};
		sides[0] = (m[y][x]%2 == 1);
		sides[1] = (m[y][x]%3 == 1);
		sides[2] = (m[y][x]%5 == 1);
		sides[3] = (m[y][x]%7 == 1);
		sides[direction] = !sides[direction];
		boolean spawn = (m[y][x]>249 && m[y][x]<500);
		boolean defender = (m[y][x]>749 && m[y][x]<999);
		boolean pots = (m[y][x]>499 && m[y][x]<750);
		m[y][x] = getRoomNumber(sides[0], sides[1], sides[2], sides[3], spawn, pots, defender);
	}
	
	public int getRoomNumber(boolean up, boolean right, boolean down, boolean left, boolean spawn, boolean pots, boolean defender) {
		int startn;
		int num = 0;
		if(up) { startn = 1;}
		else {startn = 0;}
		for(int i = startn; i < 250; i += 2) {
			if(((i%2 == 1 && up) || (i%2 == 0 && (!up))) && ((i%3 == 1 && right) || (i%3 == 0 && (!right))) && ((i%5 == 1 && down) || (i%5 == 0 && (!down))) && ((i%7 == 1 && left) || (i%7 == 0 && (!left)))) {
				num = i;
				break;
			}
		}
		int limit = 0;
		if(spawn) {limit = 250;}
		if(pots) {limit = 500;}
		if(defender) {limit = 750;}
		while(num < limit) {num += 210;}
		if(num == 1) {num += 210;}
		return num;
	}
}

//Created by Nacnudd from the Pub Halls Discord server.