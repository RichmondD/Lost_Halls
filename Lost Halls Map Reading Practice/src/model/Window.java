package model;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.Paint;

public class Window implements WindowListener {

	public Window () {
		super();
		System.out.println("zzz");
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
		System.out.println("code reached");
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

}
