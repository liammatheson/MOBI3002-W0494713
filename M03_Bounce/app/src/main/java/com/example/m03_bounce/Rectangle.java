package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;


/**
 * Created by Russ on 08/04/2014.
 */
public class Rectangle {

    float height = 75;
    float width = 100;
    float x;                // Ball's center (x,y)
    float y;
    float speedX;           // Ball's speed
    float speedY;
    private RectF bounds;   // Needed for Canvas.drawOval
    private Paint paint;    // The paint style, color used for drawing

    // Add accelerometer
    // Add ... implements SensorEventListener
    private double ax, ay, az = 0; // acceration from different axis

    public void setAcc(double ax, double ay, double az){
        this.ax = ax;
        this.ay = ay;
        this.az = az;
    }

    Random r = new Random();  // seed random number generator

    // Constructor
    public Rectangle(int color) {
        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

        // random position and speed
        x = height + width + r.nextInt(800);
        y = height + width + r.nextInt(800);
        speedX = r.nextInt(10) - 5;
        speedY = r.nextInt(10) - 5;
    }

    // Constructor
    public Rectangle(int color, float x, float y, float speedX, float speedY) {
        bounds = new RectF();
        paint = new Paint();
        paint.setColor(color);

        // use parameter position and speed
        this.x = x;
        this.y = y;
        this.speedX = speedX +10; // super fast super slow
        this.speedY = speedY +10;
    }

    public void moveWithCollisionDetection(Box box) {
        // Get new (x,y) position
        x += speedX;
        y += speedY;

        // Add acceleration to speed
        speedX += ax;
        speedY += ay;

        // Detect collision and react
        if (x + width/2 > box.xMax) {
            speedX = -speedX;
            x = box.xMax - width/2;
        } else if (x - width/2 < box.xMin) {
            speedX = -speedX;
            x = box.xMin + width/2;
        }
        if (y + height/2 > box.yMax) {
            speedY = -speedY;
            y = box.yMax - height/2;
        } else if (y - height/2 < box.yMin) {
            speedY = -speedY;
            y = box.yMin + height/2;
        }
    }

    public boolean isCollidingWith(Rectangle other) { // rectangle on rectangle
        return !(this.x + this.width/2 < other.x - other.width/2 ||
                this.x - this.width/2 > other.x + other.width/2 ||
                this.y + this.height/2 < other.y - other.height/2 ||
                this.y - this.height/2 > other.y + other.height/2);
    }

    public boolean isCollidingWith(Square other) { // rectangle on square
        return !(this.x + this.width/2 < other.x - other.radius ||
                this.x - this.width/2 > other.x + other.radius ||
                this.y + this.height/2 < other.y - other.radius ||
                this.y - this.height/2 > other.y + other.radius);
    }

    public boolean isCollidingWith(Ball other) { // rectangle on circle
        return !(this.x + this.width/2 < other.x - other.radius ||
                this.x - this.width/2 > other.x + other.radius ||
                this.y + this.height/2 < other.y - other.radius ||
                this.y - this.height/2 > other.y + other.radius);
    }
//    public void draw_rectangle(Canvas canvas) {
//        bounds.set(x - width/2, y - height/2, x + width/2, y + height/2);
//        canvas.drawRect(bounds, paint);
//    }

    private Bitmap bitmap;

    public Rectangle(Context context, int resId, float x, float y, float speedX, float speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dvd);
    }


    public void draw_rectangle(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - bitmap.getWidth()/2, y - bitmap.getHeight()/2, null);
    }


}
