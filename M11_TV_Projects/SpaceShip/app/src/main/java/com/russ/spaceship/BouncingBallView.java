package com.russ.spaceship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Russ on 08/04/2014.
 */
public class BouncingBallView extends View {

    private ArrayList<GameItem> game_items = new ArrayList<GameItem>(); // list of Balls

    //    private ArrayList<Ball> balls = new ArrayList<Ball>(); // list of Balls
//
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    private Box box;
    //    Ship ship_01, ship_02;
    // For touch inputs - previous touch (x, y)
    private float previousX;
    private float previousY;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // create the box
        box = new Box(Color.BLACK);  // ARGB

        game_items.add(new Ball(Color.RED, 250, 600, 20, -10));
        game_items.add(new Ball(Color.BLUE, 200, 200, 10, 1));
        game_items.add(new Ball(Color.GREEN, 100, 100, 10, 0));

        // To enable keypad
        this.setFocusable(true);
        this.requestFocus();
        // To enable touch mode
        //this.setFocusableInTouchMode(true);
    }

    // Called back to draw the view. Also called after invalidate().
    @Override
    protected void onDraw(Canvas canvas) {

        // Draw the components
        box.draw(canvas);
        //canvas.drawARGB(0,25,25,25);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // Balls
        for (GameItem g1 : game_items) {

            g1.moveWithCollisionDetection(box);  // Update the position
            // check collision with each ship
            for (GameItem g2 : game_items) {
                if (g1.checkHit(g2)) {
                    g1.inc_score();
                    g1.reverse(g2);
                    dtext.setText("Boing");
                }
            }
            g1.draw(canvas);  //draw each ball in the list
        }
//
//        // Ships
//        for (Ship s : ships) {
//            s.draw(canvas);  //draw each ball in the list
//            s.moveWithCollisionDetection(box);  // Update the position of the ball
//        }

        score_update++;
        if (score_update % 100 == 0) {
            score_update = 0;
            // Ship scores
            String scores = "";
            for (Ship s : ships) {
                scores = scores + s.getScore();
            }
            dtext.setText(scores);
        }

        this.invalidate();
    }

    int score_update = 0;

    public void newShip(int deviceID) {
        Ship s = new Ship(deviceID);
        ships.add(s);
        game_items.add(s);   // also add to full list of game items
    }


    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h);
    }


    public void shipEvent(int deviceID, int keyCode) {
        // get which ship
        Ship s = getShip(ships, deviceID);
        s.shipControl(keyCode);
    }

    private Ship getShip(ArrayList<Ship> ships, int deviceID) {
        for (Ship s : ships) {
            if (s.getDeviceID() == deviceID) {
                // this ship has this deviceID
                return s;
            }
        }

        // if we got here, didn't find the ship...return first ship
        return ships.get(0);
    }

    private EditText dtext;

    public void setTextArea(EditText dtext) {
        // point to screen text
        this.dtext = dtext;
    }
}
