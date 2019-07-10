package controller;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import view.Paint;

public class Mouse implements MouseListener {
	
	private int len = 9;
	private int[][] xy = new int[len][4];
	public boolean[] b = new boolean[len];
	
	
	public Mouse() {
		super();
	}
	
	public void setCoords(int ID, int x1, int x2, int y1, int y2) {
		this.xy[ID-1][0] = x1;
		this.xy[ID-1][1] = x2;
		this.xy[ID-1][2] = y1;
		this.xy[ID-1][3] = y2;
	}
	
	public void enable(int ID) {
		b[ID-1] = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() > xy[0][0] && e.getX() < xy[0][1] && e.getY() > xy[0][2] && e.getY() < xy[0][3] && b[0]) {
			//System.out.println("1");
			b = new boolean[len];
			Paint.startGame(false);
		}
		else if(e.getX() > xy[1][0] && e.getX() < xy[1][1] && e.getY() > xy[1][2] && e.getY() < xy[1][3] && b[1]) {
			//System.out.println("2");
			b = new boolean[len];
			Paint.paintControls();
		}
		else if(e.getX() > xy[2][0] && e.getX() < xy[2][1] && e.getY() > xy[2][2] && e.getY() < xy[2][3] && b[2]) {
			//System.out.println("3");
			b = new boolean[len];
			Paint.paintHome();
		}
		else if(e.getX() > xy[3][0] && e.getX() < xy[3][1] && e.getY() > xy[3][2] && e.getY() < xy[3][3] && b[3]) {
			//System.out.println("4");
			b = new boolean[len];
			Paint.paintTutorial(0);
		}
		else if(e.getX() > xy[4][0] && e.getX() < xy[4][1] && e.getY() > xy[4][2] && e.getY() < xy[4][3] && b[4]) {
			b = new boolean[len];
			//System.out.println("5");
			JFileChooser jfc = new JFileChooser();
			jfc.showDialog(null,"Select");
			jfc.setVisible(true);
			File f1 = jfc.getSelectedFile();
			if(f1 != null) {
				try {
					Paint.map.importMap(f1);
				} catch (IOException e1) {
				}
			}
			
			Paint.startGame(true);
		}
		else if(e.getX() > xy[5][0] && e.getX() < xy[5][1] && e.getY() > xy[5][2] && e.getY() < xy[5][3] && b[5]) {
			//System.out.println("6");
			b = new boolean[len];
			Paint.paintTutorial(1);
		}
		else if(e.getX() > xy[6][0] && e.getX() < xy[6][1] && e.getY() > xy[6][2] && e.getY() < xy[6][3] && b[6]) {
			//System.out.println("7");
			b = new boolean[len];
			Paint.paintTutorial(-1);
		}
		else if(e.getX() > xy[7][0] && e.getX() < xy[7][1] && e.getY() > xy[7][2] && e.getY() < xy[7][3] && b[7]) {
			//System.out.println("7");
			b = new boolean[len];
			Paint.credit();
		}
		else if(e.getX() > xy[8][0] && e.getX() < xy[8][1] && e.getY() > xy[8][2] && e.getY() < xy[8][3] && b[8]) {
			//System.out.println("1");
			b = new boolean[len];
			Paint.startGame(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.