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
		
		InputStream in = Maps.class.getResourceAsStream("/Maps.txt");
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
	
	public int[][] getMap(){
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
			return a[pick];
		}
		else {
			for(int i = 0; i < len; i++) {
				used[i] = false;
			}
			pick = r.nextInt(len);
			used[pick] = true;
			return a[pick];
		}
		
	}
	
	public int[][] getLoadedMap(){
		boolean available = false;
		int pick;
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
			return in[pick];
		}
		else {
			for(int i = 0; i < len2; i++) {
				used2[i] = false;
			}
			pick = r.nextInt(len2);
			used2[pick] = true;
			return in[pick];
		}
	}
}

//Created by Nacnudd from the Pub Halls Discord server.