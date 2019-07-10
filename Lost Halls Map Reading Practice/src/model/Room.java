package model;

public class Room {
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public boolean seen;
	public boolean pot;
	public boolean defender;
	public boolean start;
	public int x, y;
	public Room () {
		left = false;
		right = false;
		up = false;
		down = false;
		seen = false;
		pot = false;
		defender = false;
		x = 0; y = 0;
	}
	public Room (boolean l, boolean r, boolean u, boolean d, boolean po, boolean def, boolean st) {
		left = l;
		right = r;
		up = u;
		down = d;
		seen = st;
		start = st;
		pot = po;
		defender = def;
	}

}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.