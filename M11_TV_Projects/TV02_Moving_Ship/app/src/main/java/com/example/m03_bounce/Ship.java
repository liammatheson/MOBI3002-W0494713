package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Ship extends GameItem {

    private RectF rect = new RectF(0, 0, 40, 70);

    /**
     * Create a ship whose center is x,y
     *
     * @param color
     * @param x
     * @param y
     * @param speedX
     * @param speedY
     */
    public Ship(int color, float x, float y, float speedX, float speedY) {
        super(color, x, y, speedX, speedY);
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
    }

    private double incAcc = 0.01;

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

    public void pLeft() {
        decAx();
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
    }

    public void pRight() {
        incAx();
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
    }

    public void pUp() {
        decAy();
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
    }

    public void pDown() {
        incAy();
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
    }

   protected boolean collides(GameItem gameItem) {
        if (RectF.intersects(gameItem.bounds, this.getRect())) {
            return true;
        } else {
            return false;
        }
    }


    public void draw(Canvas canvas) {
        getRect().offsetTo(x - getRect().width() / 2, y - getRect().height() / 2);  // to center of paddle
        canvas.drawRect(getRect(), paint);
    }

    private int collisionCounter = 0;

    public boolean checkHit(Ball b) {

        // decrement counter to allow ball to get away
        // otherwise velocity flips and ball "sticks"
        if (getCollisionCounter() > 0) {
            setCollisionCounter(getCollisionCounter() - 1);
        }

        // Does x,y of ball fall into bounds of p1, p2 rect
        if ((getCollisionCounter() == 0) && this.collides(b)) {
            setCollisionCounter(25);
            return true;
        }

        return false;
    }

    /**
     * shape of ship
     */
    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public double getIncAcc() {
        return incAcc;
    }

    public void setIncAcc(double incAcc) {
        this.incAcc = incAcc;
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }

    public void setCollisionCounter(int collisionCounter) {
        this.collisionCounter = collisionCounter;
    }
}
