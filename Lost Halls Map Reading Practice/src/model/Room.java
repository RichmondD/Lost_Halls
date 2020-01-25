package model;

public class Room {
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public boolean pot;
	public boolean defender;
	public boolean start;
	public boolean troom;
	public boolean border;
	public boolean seen;
	public int x, y;
	public Room () {
		left = false;
		right = false;
		up = false;
		down = false;
		pot = false;
		defender = false;
		border = false;
		seen = false;
		x = 0; y = 0;
	}
	public Room (boolean l, boolean r, boolean u, boolean d, boolean po, boolean def, boolean st, boolean bor) {
		left = l;
		right = r;
		up = u;
		down = d;
		start = st;
		pot = po;
		defender = def;
		border = bor;
	}

}

//Created by Nacnudd from the Pub Halls Discord server.