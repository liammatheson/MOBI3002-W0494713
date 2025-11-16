package com.example.m03_bounce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Russ on 08/04/2014.
 */
public class BouncingBallView extends View {

    public ArrayList<Ball> balls = new ArrayList<Ball>(); // list of Balls
    private Ball ball_1;  // use this to reference first ball in arraylist
    private Box box;

    DBClass DBtest;  // class level declaration

    // For touch inputs - previous touch (x, y)
    private float previousX;
    private float previousY;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v("BouncingBallView", "Constructor BouncingBallView");

        box = new Box(Color.BLACK);  // ARGB
        DBtest = new DBClass(context);

        List<DataModel> ALL = DBtest.findAll();


        Log.w("liams test" ,"db access log");
        for (DataModel one : ALL) {
            Log.w("DataModel", "Item => " + one.toString());
            balls.add(new Ball(one.getColor(), one.getModelX(), one.getModelY(), one.getModelDX(), one.getModelDY(), one.getName()));

        }

        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();
        // To enable touch mode
        this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {

        //Log.v("BouncingBallView", "onDraw");

        // Draw the components
        box.draw(canvas);

        for (Ball b : balls) {
            b.draw(canvas);  //draw each ball in the list
            b.moveWithCollisionDetection(box);  // Update the position of the ball
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

    // called when button is pressed
    public void RussButtonPressed(int color, float x, float y, float dx, float dy, String name) {
        Log.d("BouncingBallView  BUTTON", "User tapped the  button ... VIEW");
        balls.add(new Ball(color, x, y, dx, dy, name));
        Log.v("BouncingBallView  BUTTON", "n...add ball to DB");
        DataModel newBall = new DataModel(x, y, dx, dy, color, name);
        DBtest.save(newBall);

    }
//    public void clearBalls() {
//        balls.clear();
//        SQLiteDatabase db = DBtest.getWritableDatabase();
//        db.delete("sample_table", null, null); // deletes all rows
//        db.close();
//        invalidate();
//    }
}
