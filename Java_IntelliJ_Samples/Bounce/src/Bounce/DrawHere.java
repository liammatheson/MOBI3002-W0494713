package Bounce;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DrawHere extends JPanel implements ActionListener , KeyListener {

	private ArrayList<shape> l = new ArrayList<shape>();;

	private square important_square;

	public DrawHere() {

		System.out.println("");

		l.add(new ball(39, 20, 2, 3, Color.RED, "Sarah"));
		l.add(new ball(10, 25, 1, 4, Color.ORANGE, "John"));
		l.add(new ball(10, 25, 2, -1, Color.BLUE, "Bob"));
		l.add(new ball(10, 25, -2, 1, Color.CYAN, "Sam"));
		l.add(new ball(10, 25, 3, 1, Color.GREEN, "Mary"));

		l.add(new square(10, 25, 3, 1, Color.magenta, "Square-1"));
		l.add(new square(10, 25, 3, 1, Color.GRAY, "Square-2"));

		important_square = new square(300,400, 0, 0, Color.black, "IMPORTANT");

		for (shape c : l) {
			c.start(); // Start each thread
			System.out.println("starting..." + c.getName());
		}

		this.addKeyListener(this);  // handle keystrokes in this object

		this.setFocusable(true);
		this.requestFocusInWindow();

	}

	public void paintComponent(Graphics g) {
		// System.out.println("width/Height=" + this.getWidth() + "/"+
		// this.getHeight());

		// Setup and clear the new buffer
		BufferedImage bufferedImage = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill

		// Draw all shapes onto the buffer
		if (l != null) {
			for (shape c : l) {
				c.Paint(g2d); // Start each thread
			}
		}

		// Don't forget to draw the important square
		important_square.Paint(g2d);

		// Set the buffer to be visible
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
	}

	/**
	 * This method is called when the timer fires The Timer sets off an
	 * "actionPerformed" event every so many milliseconds.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < l.size(); i++) {

			// collide with important_square?
			if (l.get(i).close(l.get(i), important_square)) {
				l.get(i).bounce();
				System.out.println("IMPORTANT BOUNCE: i=" + i );
				l.get(i).speedUp();
			}


			for (int j = i + 1; j < l.size(); j++) {

				// check if 2 shapes are "close" to each other
				if (l.get(i).close(l.get(i), l.get(j))) {
					l.get(i).bounce();
					System.out.println("BOUNCE: i=" + i + "  j=" +j );
				}
			}
		}

		this.repaint();
		
		//Take focus if we don't have it
		if (!this.isFocusOwner()) {
			this.requestFocusInWindow();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyReleased(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Keycode=" + e.getKeyCode());
	}

}
