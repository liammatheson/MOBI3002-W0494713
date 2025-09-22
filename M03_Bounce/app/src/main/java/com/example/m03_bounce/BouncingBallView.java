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
    private ArrayList<Square> squares = new ArrayList<Square>();
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private Ball ball_1;  // use this to reference first ball in arraylist
    private Square square_1;  // use this to reference first ball in arraylist
    private Rectangle rectangle_1;  // use this to reference first ball in arraylist
    private Box box;
    private int collision_counter = 0;

    // For touch inputs - previous touch (x, y)
    private float previousX;
    private float previousY;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v("BouncingBallView", "Constructor BouncingBallView");

        // create the box
        box = new Box(Color.rgb(84, 204, 184));  // ARGB

        //ball_1 = new Ball(Color.GREEN);
        balls.add(new Ball(Color.GREEN));
        ball_1 = balls.get(0);  // points ball_1 to the first; (zero-ith) element of list
        Log.w("BouncingBallLog", "Just added a bouncing ball");

        //ball_2 = new Ball(Color.CYAN);
        balls.add(new Ball(Color.CYAN));
        Log.w("BouncingBallLog", "Just added another bouncing ball");

        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();
        // To enable touch mode
        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {

        Log.v("BouncingBallView", "onDraw");

        // Draw the components
        box.draw(canvas);
        //canvas.drawARGB(0,25,25,25);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);



        for (Ball b : balls) {
            b.draw(canvas);  //draw each ball in the list
            b.moveWithCollisionDetection(box);  // Update the position of the ball


//                /// check if ball is in this square
//                float rect_x1 = 100;
//                float rect_x2 = 200;
//                float rect_y1 = 250;
//                float rect_y2 = 350;
//
//                //check if x1-x2 is small AND y1-y2 is small
//                if ((b.x>rect_x1) &&
//                        (b.x<rect_x2) &&
//                        (b.y>rect_y1) &&
//                        (b.y<rect_y2)) {
//                    // Hit .. double speed
//                    b.speedX = 2* b.speedX;
//                    b.speedY = 2* b.speedY;
//                    Log.w("Bounce", "HIT!");
//                }

        }

        for (Square s : squares) {
            s.draw_square(canvas);  //draw each ball in the list
            s.moveWithCollisionDetection(box);  // Update the position of the ball
        }

        for (Rectangle r : rectangles) {
            r.draw_rectangle(canvas);  //draw each ball in the list
            r.moveWithCollisionDetection(box);  // Update the position of the ball

            for (Ball b : balls) {
                if (r.isCollidingWith(b)) {
                    collision_counter++;
                    Log.w("Collision", "Rectangle hit Ball! Collisions: " + collision_counter);
                }
            }

            for (Square s : squares) {
                if (r.isCollidingWith(s)) {
                    collision_counter++;
                    Log.w("Collision", "Rectangle hit Square! " + collision_counter);
                }
            }

            for (Rectangle other : rectangles) {
                if (r != other && r.isCollidingWith(other)) {
                    collision_counter++;
                    Log.w("Collision", "Rectangle hit Rectangle! " + collision_counter);
                }
            }
        }

        // Delay on UI thread causes big problems!
        // Simulates doing busy work or waits on UI (DB connections, Network I/O, ....)
        //  I/Choreographer? Skipped 64 frames!  The application may be doing too much work on its main thread.
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }

        // Check what happens if you draw the box last
        //box.draw(canvas);

        // Check what happens if you don't call invalidate (redraw only when stopped-started)
        // Force a re-draw
        this.invalidate();
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h);
    }

    // Touch-input handler // 50 should be good diff for squares
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
                Log.w("BouncingBallLog", "x,y= " + previousX + " ," + previousY + "  Xdiff=" + deltaX + " Ydiff=" + deltaY);
                Random rand = new Random();
                int randColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

                if (Math.abs(deltaX) < 5 || Math.abs(deltaY) < 5)
                {balls.add(new Ball(randColor, previousX, previousY, deltaX, deltaY));}
                else
                {squares.add(new Square(randColor, previousX, previousY, deltaX, deltaY));}


                // A way to clear list when too many balls
                if (balls.size() > 50) {
                    // leave first ball, remove the rest
                    Log.v("BouncingBallLog", "too many balls, clear back to 1");
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
        Log.d("BouncingBallView  BUTTON", "User tapped the  button ... VIEW");

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

    public void RectangleButtonPressed() {
        Log.d("BouncingBallView  BUTTON", "User tapped the rectangle button ... VIEW");

        //get half of the width and height as we are working with a circle
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        // make random x,y, velocity
        int x = rand.nextInt(viewWidth);
        int y = rand.nextInt(viewHeight);
        int dx = rand.nextInt(50);
        int dy = rand.nextInt(20);

        rectangles.add(new Rectangle(getContext(), R.drawable.dvd, x, y, dx, dy));  // add ball at every touch event
    }

}
