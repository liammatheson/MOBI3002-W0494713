package com.russ.spaceship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Random;

/**created by Russ on 08/04/201**/
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


    public void reverse(GameItem that) {
//        dx = -dx;     // reverse x direction
//        x = x + 3 * dx;       // get it started outta here or it sticks
//        dy = (r.nextFloat() * 5f); // Random rebound

        // swap this velocity for that's
        // load temp
        float tempdx = this.dx;
        float tempdy = this.dy;

        // set this using that]=

        this.dx = this.dx + that.dx * 0.2f;
        this.dy = this.dx + that.dy * 0.2f;

        // set that using temp
//        that.dx = tempdx;
//        that.dy = tempdy;

        // try to be outside scope of influence for 2nd collisionIO'[P
        this.y = this.y + this.dy*2;

        this.setBoundsfromxy();  // correct the boundary rectangle per new x,y
//        that.setBoundsfromxy(); // and also changed other game item
    }

    @Override
    public void inc_score() {
        // do nothing ...balls don't get a score
    }


    public void moveWithCollisionDetection(Box box) {
        super.moveWithCollisionDetection(box);

        // If a Ball, reduce speed slightly
        ax = ax/1.05;
        ay = ay/1.05;

        dx = dx/1.005f;
        dy = dy/1.005f;

    }
}