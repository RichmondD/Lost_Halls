import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	
	public Keyboard() {
		super();
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP) {
			Main.moveUp();
		}
		else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.moveDown();
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.moveRight();
		}
		else if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.moveLeft();
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