package model;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.Paint;

public class Window implements WindowListener {

	public Window () {
		super();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		Paint.repaint();

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		Paint.repaint();

	}
	
	public void windowStateChanged(WindowEvent arg0) {
		Paint.repaint();

	}

}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.