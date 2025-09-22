package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Russ on 08/04/2014.
 */
public class BouncingBallView extends View {

    private ArrayList<Ball> balls = new ArrayList<Ball>(); // list of Balls
    private Ball ball_1;  // use this to reference first ball in arraylist
    private Box box;
    Paddle p1, p2;
    // For touch inputs - previous touch (x, y)
    private float previousX;
    private float previousY;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v("BouncingBallView", "Constructor BouncingBallView");

        // create the box
        box = new Box(Color.BLACK);  // ARGB

        //ball_1 = new Ball(Color.GREEN);
        balls.add(new Ball(Color.GREEN, 100, 100, 1, 0));
        ball_1 = balls.get(0);  // points ball_1 to the first; (zero-ith) element of list
        Log.w("BouncingBallLog", "Just added a bouncing ball");

        //ball_2 = new Ball(Color.CYAN);
//        balls.add(new Ball(Color.CYAN));
//        Log.w("BouncingBallLog", "Just added another bouncing ball");

        p1 = new Paddle( Color.WHITE, 300, 800, 0, 0);
//        p2 = new Paddle( Color.WHITE, 1600, 400, 0, 0);



        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();
        // To enable touch mode
        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {

//        Log.v("BouncingBallView", "onDraw");

        // Draw the components
        box.draw(canvas);
        //canvas.drawARGB(0,25,25,25);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for (Ball b : balls) {
            b.draw(canvas);  //draw each ball in the list
            b.moveWithCollisionDetection(box);  // Update the position of the ball
//            b.moveWithCollisionPaddle(p1);
            if (p1.checkHit(b)) {
                b.reverse();
            }
        }

        p1.draw(canvas);
//        p2.draw(canvas);

        this.invalidate();
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h);
    }

    // Touch-input handler
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                ball_1.speedX += deltaX * scalingFactor;
                ball_1.speedY += deltaY * scalingFactor;
                //Log.w("BouncingBallLog", " Xspeed=" + ball_1.speedX + " Yspeed=" + ball_1.speedY);
//                Log.w("BouncingBallLog", "x,y= " + previousX + " ," + previousY + "  Xdiff=" + deltaX + " Ydiff=" + deltaY);
                balls.add(new Ball(Color.BLUE, previousX, previousY, deltaX, deltaY));  // add ball at every touch event

                // A way to clear list when too many balls
                if (balls.size() > 20) {
                    // leave first ball, remove the rest
//                    Log.v("BouncingBallLog", "too many balls, clear back to 1");
                    balls.clear();
                    balls.add(new Ball(Color.RED));
                    ball_1 = balls.get(0);  // points ball_1 to the first (zero-ith) element of list
                }

        }
        // Save current x, y
        previousX = currentX;
        previousY = currentY;

        // Try this (remove other invalidate(); )
        //this.invalidate();

        return true;  // Event handled
    }

    Random rand = new Random();
    // called when button is pressed
    public void RussButtonPressed() {
        //get half of the width and height as we are working with a circle
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        // make random x,y, velocity
        int x = rand.nextInt(viewWidth);
        int y = rand.nextInt(viewHeight);
        int dx = rand.nextInt(50);
        int dy = rand.nextInt(20);

        balls.add(new Ball(Color.RED, x, y, dx, dy));  // add ball at every touch event
    }

    public void upPressed() {
        p1.pUp();

//        //get half of the width and height as we are working with a circle
//        int viewWidth = this.getMeasuredWidth();
//        int viewHeight = this.getMeasuredHeight();
//
//        // middle, upwards
//        int x = viewWidth/2;
//        int y = viewHeight/2;
//        int dx = rand.nextInt(5);
//        int dy = -rand.nextInt(20);
//
//        balls.add(new Ball(Color.CYAN, x, y, dx, dy));  // add ball at every touch event
    }

    public void downPressed() {
        p1.pDown();

//        //get half of the width and height as we are working with a circle
//        int viewWidth = this.getMeasuredWidth();
//        int viewHeight = this.getMeasuredHeight();
//
//        // middle, downwards
//        int x = viewWidth/2;
//        int y = viewHeight/2;
//        int dx = rand.nextInt(5);
//        int dy = rand.nextInt(20);
//
//        balls.add(new Ball(Color.YELLOW, x, y, dx, dy));  // add ball at every touch event
    }

    public void leftPressed() {
        //get half of the width and height as we are working with a circle
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        // middle, leftwards
        int x = viewWidth/2;
        int y = viewHeight/2;
        int dx = -rand.nextInt(50);
        int dy = rand.nextInt(5);

        balls.add(new Ball(Color.MAGENTA, x, y, dx, dy));  // add ball at every touch event
    }

    public void rightPressed() {
        //get half of the width and height as we are working with a circle
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        // middle, upwards
        int x = viewWidth/2;
        int y = viewHeight/2;
        int dx = rand.nextInt(50);
        int dy = rand.nextInt(5);

        balls.add(new Ball(Color.WHITE, x, y, dx, dy));  // add ball at every touch event
    }

    public void clearAll() {
        balls.clear();
    }
}
