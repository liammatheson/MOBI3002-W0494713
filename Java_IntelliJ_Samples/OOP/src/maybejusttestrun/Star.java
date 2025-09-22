package maybejusttestrun;

public class Star extends DisplayThing {

	@Override
	public void display() {
//		System.out.print("x=" + this.x + "y=" + this.y);
		System.out.println("  **STAR**");
	}

	public void twinkle() {
		System.out.println("**");
	}

}
