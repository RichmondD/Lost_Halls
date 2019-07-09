package model;
//This class is for executing draw statements in a ~60fps loop, for updating the current screen

import controller.Button;
import controller.Keyboard;
import controller.Mouse;
import view.Paint;

public class Main{
	
	
	private static Mouse mouse1;
	public static Maps map;
	private static Keyboard key;
	private static int[][] m;
	public static int[] pos = {0,0};
	public static boolean[][] seen = new boolean[9][9];
	private static int tutor;

	public static void main(String[] args) {
		Paint.paintMain();
	}
}

//Created by Nacnudd from the Pub Halls Discord server.