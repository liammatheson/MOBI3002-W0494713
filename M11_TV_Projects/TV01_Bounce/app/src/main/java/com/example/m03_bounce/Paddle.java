package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Paddle extends GameItem {

    /**
     * shape of paddle
     */
    RectF rect = new RectF(0, 0, 50, 220);

    /**
     * Create a paddle whose center is x,y
     *
     * @param color
     * @param x
     * @param y
     * @param speedX
     * @param speedY
     */
    public Paddle(int color, float x, float y, float speedX, float speedY) {
        super(color, x, y, speedX, speedY);
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);  // to center of paddle
    }

    public void pUp() {
        y = y - 25;
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);  // to center of paddle
    }

    public void pDown() {
        y = y + 25;
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);  // to center of paddle
    }


    protected boolean collides(GameItem gameItem) {
        if (RectF.intersects(gameItem.bounds, this.rect)) {
            return true;
        } else {
            return false;
        }
    }


    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    int collisionCounter = 0;

    public boolean checkHit(Ball b) {

        // decrement counter to allow ball to get away from paddle
        // otherwise velocity flips and ball "sticks"
        if (collisionCounter > 0) {
            collisionCounter--;
        }

        // Does x,y of ball fall into bounds of p1, p2 rect
        if ((collisionCounter == 0) && this.collides(b)) {
            collisionCounter = 25;
            return true;
        }

        return false;
    }
}
