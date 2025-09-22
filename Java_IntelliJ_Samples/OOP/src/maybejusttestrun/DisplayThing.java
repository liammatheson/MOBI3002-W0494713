package maybejusttestrun;

public abstract  class DisplayThing {

	// protected int x, y;
	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public DisplayThing() {
		this.x = 0;
		this.y = 0;
	}

	public void moveUp() {
		this.y--;
	}

	public void moveDown() {
		this.y++;
	}

	public void moveLeft() {
		this.x--;
	}

	public void moveRight() {
		this.x++;
	}

	public abstract void display();

	public boolean doesItCollide(DisplayThing other) {

		System.out.println("x=" + this.x + "y=" + this.y + " other x = " + other.x + "other y" + other.y);

		return false;
	}

}
