package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Russ on 08/04/2014.
 */
public class Box {

    int xMin, xMax, yMin, yMax;
    private Paint paint;  // paint style and color
    private Rect bounds;
    private Bitmap background;

    public Box(int color, Context context, int drawableResId) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
        background = BitmapFactory.decodeResource(context.getResources(), drawableResId);
    }

    public void set(int x, int y, int width, int height) {
        xMin = x;
        xMax = x + width - 1;
        yMin = y;
        yMax = y + height - 1;
        // The box's bounds do not change unless the view's size changes
        bounds.set(xMin, yMin, xMax, yMax);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, null, bounds, paint);
    }
}
