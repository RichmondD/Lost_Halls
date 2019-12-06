package controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Button{
	
	private int x;
	private int y;
	private int length;
	private int width;
	private int ID;
	private Color color;
	private String text;
	public Image i;
	public Mouse m;
	
	public Button(int ID, int x, int y, int length, int width, Color color, String text, Image i, Mouse m) {
		super();
		this.x = x;
		this.y = y;
		this.length = length;
		this.width = width;
		this.color = color;
		this.text = text;
		this.i = i;
		this.ID = ID;
		this.m = m;
		draw(i.getGraphics());
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

//Created by Nacnudd from the Pub Halls Discord server.