package com.russ.spaceship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public abstract class GameItem {

    float radius = 50;      // Item radius, general size
    float x;                // center (x,y)
    float y;
    float dx;           // speed
    float dy;
    Color c = new Color();
    protected RectF bounds;   // Needed for Canvas.drawOval
    protected Paint paint;    // The paint style, color used for drawing

    // Add accelerometer
    // Add ... implements SensorEventListener
    protected double ax;
    protected double ay = 0; // acceleration from different axis

    public void setAcc(double ax, double ay, double az) {
        this.ax = ax;
        this.ay = ay;
    }

    Random r = new Random();  // seed random number generator

    // Constructor
    public GameItem(int color) {
        bounds = new RectF();
        paint = new Paint();
        this.c = Color.valueOf(color);
        paint.setColor(color);

        // random position and speed
        x = radius + r.nextInt(800);
        y = radius + r.nextInt(800);
        dx = r.nextInt(10) - 5;
        dy = r.nextInt(10) - 5;

        setBoundsfromxy();

    }

    // Constructor
    public GameItem(int color, float x, float y, float dx, float dY) {
        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

        // use parameter position and speed
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dY;

        setBoundsfromxy();
    }

    public void setBoundsfromxy(){
        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += dx;
        y += dy;

        // Add acceleration to speed
        dx += ax;
        dy += ay;

        // Detect collision and react
        if (x + radius > box.xMax) {
            dx = -dx;
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            dx = -dx;
            x = box.xMin + radius;
        }
        if (y + radius > box.yMax) {
            dy = -dy;
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            dy = -dy;
            y = box.yMin + radius;
        }

        setBoundsfromxy();
    }

    protected boolean collides(GameItem gameItem) {
        if (this==gameItem){
            // Don't collide with yourself
            return false;};
        if (RectF.intersects(gameItem.bounds, this.bounds)) {
            return true;
        } else {
            return false;
        }
    }


    public abstract void draw(Canvas canvas);



    private int collisionCounter = 0;

    public  boolean checkHit(GameItem g2){
        
            // decrement counter to allow ball to get away
            // otherwise velocity flips and ball "sticks"
            if (collisionCounter > 0) {
                collisionCounter = (collisionCounter - 1);
            }

            // Does x,y of ball fall into bounds of p1, p2 rect
            if ((collisionCounter == 0) && this.collides(g2)) {
                collisionCounter = (25);
                // increment score
//                this.score++;   // done with OOP
                return true;
            }

            return false;
        }   

    public abstract void reverse(GameItem g2);

    public abstract void inc_score();
}
