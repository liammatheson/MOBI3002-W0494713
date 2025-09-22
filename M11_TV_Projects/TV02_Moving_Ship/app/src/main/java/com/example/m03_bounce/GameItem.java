package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public abstract class GameItem {


    float radius = 50;      // Item radius, general size
    float x;                // center (x,y)
    float y;
    float dX;           // speed
    float dY;
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
        paint.setColor(color);

        // random position and speed
        x = radius + r.nextInt(800);
        y = radius + r.nextInt(800);
        dX = r.nextInt(10) - 5;
        dY = r.nextInt(10) - 5;

        setBoundsfromxy();

//        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    // Constructor
    public GameItem(int color, float x, float y, float dX, float dY) {
        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

        // use parameter position and speed
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;

        setBoundsfromxy();
//        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setBoundsfromxy(){
        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += dX;
        y += dY;

        // Add acceleration to speed
        dX += ax;
        dY += ay;

        // Detect collision and react
        if (x + radius > box.xMax) {
            dX = -dX;
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            dX = -dX;
            x = box.xMin + radius;
        }
        if (y + radius > box.yMax) {
            dY = -dY;
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            dY = -dY;
            y = box.yMin + radius;
        }
        setBoundsfromxy();
    }


    int colisionCounter = 0;
    public void moveWithCollisionPaddle(Ship p) {

        // decrement counter to allow ball to get away from paddle
        // otherwise velocity flips and ball "sticks"
        if (colisionCounter>0){
            colisionCounter--;
        }

        // Does x,y of ball fall into bounds of p1, p2 rect
        if ((colisionCounter==0) && p.collides(this) ) {
//            speedX = -speedX;
//            speedY = -speedY;
            colisionCounter = 5;
        }
    }

    public abstract void draw(Canvas canvas);

}
