package com.russ.spaceship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Random;

public class Ship extends GameItem {

    private int deviceID;

    private int score = 0;

    /**
     * Create a ship whose center is x,y
     */
    public Ship(int deviceID, int color, float x, float y, float speedX, float speedY) {
        super(color, x, y, speedX, speedY);
        this.bounds.offsetTo(x - this.bounds.width() / 2, y - this.bounds.height() / 2);  // to center of paddle
    }

    private double incAcc = 0.3;

    private Random r = new Random();

    public Ship(int deviceID) {
        super(0);
        this.deviceID = deviceID;
        this.x = 200 + r.nextFloat() * 500;
        this.y = 200 + r.nextFloat() * 500;
        this.dx = 10.0f;
        this.dy = 10.0f;
        this.ax = 10.0f;
        this.ay = 10.0f;

        newShipColor();
    }

    public void newShipColor() {
        // Random Color, set the paint with this color
        int color = ((int) (Math.random() * 16777215)) | (0xFF << 24);
        this.c = Color.valueOf(color);
        this.paint.setColor(color);
    }

    @Override
    public void moveWithCollisionDetection(Box box) {
        super.moveWithCollisionDetection(box);

        // If a Ship, reduce speed
        ax = ax / 1.1;
        ay = ay / 1.1;

        dx = dx / 1.04f;
        dy = dy / 1.04f;
    }

    public void draw(Canvas canvas) {
        this.bounds.offsetTo(x - this.bounds.width() / 2, y - this.bounds.height() / 2);  // to center of paddle
        canvas.drawRect(this.bounds, paint);
    }

    @Override   public void reverse(GameItem that) {

//        float tempdx = this.dx;
//        float tempdy = this.dy;
//
//        // ship not as much effected
//        this.dx = this.dx + that.dx * 0.1f;
//        this.dy = this.dx + that.dy * 0.1f;
//
//        // set that using temp
//        that.dx = tempdx;
//        that.dy = tempdy;
//
//        // try to be outside scope of influence for 2nd collision
//        this.x = this.x + this.dx*2;
//        this.y = this.y + this.dy*2;
//
//        this.setBoundsfromxy();  // correct the boundary rectangle per new x,y
//        that.setBoundsfromxy(); // and also changed other game item
    }

    @Override
    public void inc_score() {
        this.score++;
    }

    public String getScore() {
        return this.deviceID + "=>" + this.score;
    }

    public int getDeviceID() {
        return this.deviceID;
    }

    public void shipControl(int keyCode) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                this.decAy();
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                this.incAy();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                this.incAx();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                this.decAx();
                break;
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_ESCAPE:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                this.newShipColor();
                break;
            default:
        }
    }

    public void incAx() {
        this.ax = this.ax + getIncAcc();
    }

    public void decAx() {
        this.ax = this.ax - getIncAcc();
    }

    public void incAy() {
        this.ay = this.ay + getIncAcc();
    }

    public void decAy() {
        this.ay = this.ay - getIncAcc();
    }

    public double getIncAcc() {
        return incAcc;
    }

}
