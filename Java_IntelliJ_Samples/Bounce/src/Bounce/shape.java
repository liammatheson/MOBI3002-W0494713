package Bounce;

import java.awt.Color;
import java.awt.Graphics;

public abstract class shape extends Thread  {

	/**
	 * theCat will start writing on the page (g) using the text in the given
	 * buffer. The delay will determine how fast it writes and the xLocation
	 * will determine where to start.
	 */
	volatile boolean shape_alive = false;
	volatile boolean shape_active = false;
	protected String shapeName;
	protected int delay;
	protected int xLocation;
	protected int yLocation = 40;

	protected int xVel = 0;
	protected int yVel = 0;

	protected int xMin = 0;
	protected int xMax = 600;
	protected int yMin = 0;
	protected int yMax = 700;

	protected Color shapeColor;
	final int increment = 1;
	final int shapeSize = 40;
	// Declare array of colors (range is 0 to thing.length)
	protected int maxSize = 400; // roll-over cat location to zero when they reach
								// maxSize

	public shape(int xStart, int yStart, int xVel, int yVel, Color shapeColor,
				 String theText) {
		this.delay = 10;
		this.xLocation = xStart;
		this.yLocation = yStart;
		this.xVel = xVel;
		this.yVel = yVel;

		this.shapeColor = shapeColor;
		this.shapeName = theText;
		
		System.out.println("Constructor for theCat: " + this.shapeName);
	}

	public void killShape() {
		if (shape_alive) {
			shape_alive = false;
		}
	}

	public void activateCat() {
		if (!shape_active) {
			shape_active = true;
		}
	}

	public void sleepCat() {
		if (shape_active) {
			shape_active = false;
		}
	}

	public void resetCat(String s) {
		yLocation = (int) (Math.random() * maxSize);
		xLocation = (int) (Math.random() * maxSize);
		shapeName = s;
	}

	@Override
	public void run() {

		System.out.println("Start Shape: " + this.shapeName);

		// Initial settings
		shape_alive = true;
		shape_active = true;

		while (shape_alive == true) {
			while (shape_alive == true && shape_active == true) {

				if (justBounced > 0) {
					justBounced = justBounced - 1;
				}

				// move based on increment
				// xLocation = (xLocation + increment) % maxSize;
				xLocation = xLocation + xVel;
				yLocation = yLocation + yVel;

				if (xLocation > xMax) {
					xVel = -xVel;
				}
				if (xLocation < xMin) {
					xVel = -xVel;
				}

				if (yLocation > yMax) {
					yVel = -yVel;
				}
				if (yLocation < yMin) {
					yVel = -yVel;
				}

				// If thread loops too fast, the cat leaves a "trail"
				sleepAlittle(delay);
			}

			System.out.println(shapeName + " sleeping");
			sleepAlittle(500);
		}

		System.out.println("Shape " + shapeName + " is no longer alive");
	}

	private void sleepAlittle(int s) {
		try {
			sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	abstract void Paint(Graphics g);
//
//	void Paint(Graphics g) {
//		// Draw new graphic
//		g.setColor(catColor);
//		g.fillOval(xLocation - shapeSize / 2, yLocation - shapeSize / 2,
//				shapeSize, shapeSize);
////		g.drawChars(catName.toCharArray(), 0, catName.toCharArray().length,
////				xLocation - shapeSize / 2, yLocation - shapeSize / 2);
//	}


	final int bounceSetting = 10;       // # of times to ignore next bounces
	final int closeVal = shapeSize / 2; // How close to trigger collision
	int justBounced = bounceSetting;    // Count down var for bounce

	protected boolean close(shape a, shape b) {
		boolean isClose = false;

		// Don't bounce multiple times in one collision
		if ((justBounced == 0)
				&& (Math.abs(a.xLocation - b.xLocation) < closeVal)
				&& (Math.abs(a.yLocation - b.yLocation) < closeVal)) {
			isClose = true;
			justBounced = bounceSetting;
			// System.out.println("Collide="+a.xLocation +" "+ b.xLocation
			// +" "+a.yLocation +" "+ b.yLocation);
		}
		return isClose;
	}

	public void bounce() {
		xVel = -xVel;
		yVel = -yVel;
	}

	public void speedUp() {
		this.xVel = 2* this.xVel;
		this.yVel = 2 * this.yVel;
	}

	abstract int updateScore();
}