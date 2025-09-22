package Bounce;

import java.awt.*;

public class ball extends shape {
    public ball(int xStart, int yStart, int xVel, int yVel, Color shapeColor, String theText) {
        super(xStart, yStart, xVel, yVel, shapeColor, theText);
    }


    void Paint(Graphics g) {
        // Draw new graphic
        g.setColor(shapeColor);
        g.fillOval(xLocation - shapeSize / 2, yLocation - shapeSize / 2,
                shapeSize, shapeSize);
		g.drawChars(shapeName.toCharArray(), 0, shapeName.toCharArray().length,
				xLocation - shapeSize / 2, yLocation - shapeSize / 2);
    }

    @Override
    int updateScore() {
        return 0;
    }
}
