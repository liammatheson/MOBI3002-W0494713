package Bounce;

//import java.util.Timer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * 
 * Motion and Sound:
 *   Motion of many dots with collision!
 *
 *Try:
 */
public class Main {

	// A timer to refresh the screen
	static Timer timer;
	static DrawHere d = new DrawHere();

	public static void main(String[] args) {
		System.out.println("A");

		// Set up jFrame window for drawing
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 750);
		frame.setVisible(true);
		frame.setContentPane(d);

		frame.getRootPane().setBackground(Color.LIGHT_GRAY);

		System.out.println("B");

		// Set up Timer
		timer = new Timer(5, d); // Set time, and this object gets event
		timer.setInitialDelay(100); //
		timer.setCoalesce(true); // Don't stack up events
		timer.start(); // Start the timer object

	}

}