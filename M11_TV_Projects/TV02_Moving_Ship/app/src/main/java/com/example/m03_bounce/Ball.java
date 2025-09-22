package com.example.m03_bounce;

import android.graphics.Canvas;


/**
 * Created by Russ on 08/04/2014.
 */
public class Ball extends GameItem {
    public Ball(int color) {
        super(color);
    }

    public Ball(int color, float x, float y, float speedX, float speedY) {
        super(color, x, y, speedX, speedY);
    }

    public void draw(Canvas canvas) {
//        bounds.set(x - radius, y - radius, x + radius, y + radius);
        // bounds should already be accurate for this ball
        canvas.drawOval(bounds, paint);

    }


    public void reverse() {
        dX = -dX;     // reverse x direction
        x = x + 3* dX;       // get it started outta here or it sticks
        dY = (r.nextFloat() *5f) ; // Random rebound

        setBoundsfromxy();  // correct the boundary rectangle per new x,y

    }
}
