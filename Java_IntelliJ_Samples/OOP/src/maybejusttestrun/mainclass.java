package maybejusttestrun;

public class mainclass {

	public static void main(String[] args) {
		
		Butterfly b = new Butterfly();	
		Star s = new Star();
		b.moveDown();
		b.moveLeft();
		s.moveRight();
		s.moveUp();
		s.twinkle();
		b.display();
		s.display();
		
		// Does s collide with b?
		
		boolean collide = b.doesItCollide(s);
		System.out.print("collide="+ collide);
		
		
	}

}
