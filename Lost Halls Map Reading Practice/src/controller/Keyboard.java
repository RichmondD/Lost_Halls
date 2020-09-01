package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Paint;

public class Keyboard implements KeyListener {
	
	
	public Keyboard() {
		super();
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP) {
			Paint.moveUp();
		}
		else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			Paint.moveDown();
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			Paint.moveRight();
		}
		else if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			Paint.moveLeft();
		}
		else if(k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			Paint.undo();
			//System.out.println("undo");
		}
		else if(k.getKeyCode() == KeyEvent.VK_R) {
			Paint.startGame(false);	
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		
	}

}

//Created by Nacnudd from the Pub Halls Discord server.
