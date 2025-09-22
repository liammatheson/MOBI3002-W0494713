package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public abstract class GameItem {


    float radius = 50;      // Item radius, general size
    float x;                // center (x,y)
    float y;
    float speedX;           // speed
    float speedY;
    protected RectF bounds;   // Needed for Canvas.drawOval
    protected Paint paint;    // The paint style, color used for drawing

    // Add accelerometer
    // Add ... implements SensorEventListener
    private double ax, ay, az = 0; // acceleration from different axis

    public void setAcc(double ax, double ay, double az) {
        this.ax = ax;
        this.ay = ay;
        this.az = az;
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
        speedX = r.nextInt(10) - 5;
        speedY = r.nextInt(10) - 5;

        setBoundsfromxy();

//        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    // Constructor
    public GameItem(int color, float x, float y, float speedX, float speedY) {
        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

        // use parameter position and speed
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

        setBoundsfromxy();
//        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setBoundsfromxy(){
        bounds.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += speedX;
        y += speedY;

        // Add acceleration to speed
        speedX += ax;
        speedY += ay;

        // Detect collision and react
        if (x + radius > box.xMax) {
            speedX = -speedX;
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            speedX = -speedX;
            x = box.xMin + radius;
        }
        if (y + radius > box.yMax) {
            speedY = -speedY;
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            speedY = -speedY;
            y = box.yMin + radius;
        }
        setBoundsfromxy();
    }


    int colisionCounter = 0;
    public void moveWithCollisionPaddle(Paddle p) {

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
