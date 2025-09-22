package sampleKaPing;

import java.util.Random;

public class MainLine {

	public static void main(String[] args) {

		Random rnd = new Random();

		for (int count = 0; count < 10; count++) {

			Ball ball = new Ball(rnd.nextInt(30));
			Paddle paddle = new Paddle(rnd.nextInt(30), rnd.nextInt(5) + 5);

			System.out.println("=========================");

			ball.printit();
			paddle.printit();

			if (paddle.doesItCollide(ball)) {
				System.out.println("Ka-Ping");  // Collided
			}
		}
	}

}
