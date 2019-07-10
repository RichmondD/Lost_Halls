package controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Button{
	
	private int x;
	private int y;
	private int length;
	private int width;
	private int ID;
	private Color color;
	private String text;
	public JPanel frame;
	public Mouse m;
	
	public Button(int ID, int x, int y, int length, int width, Color color, String text, JPanel frame, Mouse m) {
		super();
		this.x = x;
		this.y = y;
		this.length = length;
		this.width = width;
		this.color = color;
		this.text = text;
		this.frame = frame;
		this.ID = ID;
		this.m = m;
		draw(frame.getGraphics());
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getText() {
		return text;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, length, width);
		Font f = new Font("SansSerif", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString(text, x+20, y+25+(width-30)/2);
		m.setCoords(ID, x, x+length, y, y+width);
		m.enable(ID);
	}
	
}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.