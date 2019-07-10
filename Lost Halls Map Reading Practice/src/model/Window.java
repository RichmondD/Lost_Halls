package model;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import view.Paint;

public class Window implements WindowStateListener {

	@Override
	public void windowStateChanged(WindowEvent e) {
		System.out.println("state changed");
		Paint.repaintDelay();
	}
	
}

//Created by Nacnudd and Goalaso from the Pub Halls Discord server.