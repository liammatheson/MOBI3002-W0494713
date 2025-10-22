package com.example.m03_bounce;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.graphics.Bitmap;

/**
 * Created by Russ on 08/04/2014.
 */
public class BouncingBallView extends View implements SensorEventListener {

    private ArrayList<Ball> balls = new ArrayList<Ball>(); // list of Balls
    private Ball ball_1;  // use this to reference first ball in arraylist
    private Box box;
    double ax = 0;   // Store here for logging to screen
    double ay = 0;   //
    double az = 0;   //

    private Bitmap bumper1Bitmap, bumper2Bitmap;
    private RectF bumper1Rect, bumper2Rect;

    private RectF lose_hole;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.v("BouncingBallView", "Constructor BouncingBallView");

        // create the box
        box = new Box(Color.BLACK, context, R.drawable.pinball_background);  // ARGB

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

        // adding losing_hole
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(lose_hole, paint);

        // Draw the components
        box.draw(canvas);



        for (Ball b : balls) {
            b.draw(canvas);  //draw each ball in the list
            b.moveWithCollisionDetection(box);  // Update the position of the ball

            int px1 = (int)((b.x - bumper1Rect.left) * bumper1Bitmap.getWidth() / bumper1Rect.width());
            int py1 = (int)((b.y - bumper1Rect.top) * bumper1Bitmap.getHeight() / bumper1Rect.height());
            if (px1 >= 0 && px1 < bumper1Bitmap.getWidth() && py1 >= 0 && py1 < bumper1Bitmap.getHeight()) {
                int pixel1 = bumper1Bitmap.getPixel(px1, py1);
                if ((pixel1 >>> 24) != 0) {  // non-transparent
                    b.speedY = -b.speedY;
                    if (b.speedY > 0) {
                        b.y = bumper1Rect.bottom + b.radius + 1;
                    } else {
                        b.y = bumper1Rect.top - b.radius - 1;
                    }
                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bump);
                    mp.start();

                    canvas.drawBitmap(bumper1Bitmap, null, bumper1Rect, null);
                    postDelayed(() -> invalidate(), 2000);
                }
            }

            int px2 = (int)((b.x - bumper2Rect.left) * bumper2Bitmap.getWidth() / bumper2Rect.width());
            int py2 = (int)((b.y - bumper2Rect.top) * bumper2Bitmap.getHeight() / bumper2Rect.height());
            if (px2 >= 0 && px2 < bumper2Bitmap.getWidth() && py2 >= 0 && py2 < bumper2Bitmap.getHeight()) {
                int pixel2 = bumper2Bitmap.getPixel(px2, py2);
                if ((pixel2 >>> 24) != 0) {  // non-transparent
                    b.speedY = -b.speedY;
                    if (b.speedY > 0) {
                        b.y = bumper2Rect.bottom + b.radius + 1;
                    } else {
                        b.y = bumper2Rect.top - b.radius - 1;
                    }
                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bump);
                    mp.start();

                    canvas.drawBitmap(bumper2Bitmap, null, bumper2Rect, null);
                    postDelayed(() -> invalidate(), 2000);
                }
            }

            if (RectF.intersects(b.getBounds(), lose_hole)) {
                balls.clear();

                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.lose);
                mp.start();

                postDelayed(() -> { // so new ball doesnt instantly appear. give it a second.
                    int viewWidth = getMeasuredWidth();
                    int viewHeight = getMeasuredHeight();
                    int x = rand.nextInt(viewWidth);
                    int y = rand.nextInt(viewHeight);
                    int dx = rand.nextInt(50);
                    int dy = rand.nextInt(20);
                    balls.add(new Ball(Color.RED, x, y, dx, dy, getContext(), R.drawable.ball));
                }, 2000);
            }

        }

        this.invalidate();
    }

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h);
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h);

        // losing hole. if the ball falls in you lose.
        float holeWidth = 200;
        float holeHeight = 50;
        lose_hole = new RectF(
                w/2 - holeWidth/2,   // left
                h - holeHeight,      // top
                w/2 + holeWidth/2,   // right
                h                     // bottom
        );


        bumper1Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bumper1);
        bumper2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bumper2);

        float bumperWidth = w * 0.04f;
        float bumperHeight = h * 0.05f;

        bumper1Rect = new RectF(
                w * 0.22f,
                h * 0.74f,
                w * 0.29f + bumperWidth,
                h * 0.82f + bumperHeight);

        bumper2Rect = new RectF(
                w * 0.66f,
                h * 0.74f,
                w * 0.74f + bumperWidth,
                h * 0.82f + bumperHeight);

        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();

        // make random x,y, velocity
        int x = rand.nextInt(viewWidth);
        int y = rand.nextInt(viewHeight);
        int dx = rand.nextInt(50);
        int dy = rand.nextInt(20);

        balls.add(new Ball(Color.RED, x, y, dx, dy, getContext(), R.drawable.ball));  // add ball at every touch event

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

        balls.add(new Ball(Color.RED, x, y, dx, dy, getContext(), R.drawable.ball));  // add ball at every touch event
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        //Log.v("onSensorChanged", "event=" + event.toString());

        // Lots of sensor types...get which one, unpack accordingly
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ax = -event.values[0];  // turns out x is backwards...on my screen?
            ay = event.values[1];   // y component of Accelerometer
            az = event.values[2];   // z component of Accelerometer

            float scale = .4f;

            for (Ball b : balls) {
                b.setAcc(ax * scale, ay * scale, az * scale);  //draw each ball in the list
            }

            Log.v("onSensorChanged", "ax=" + ax + " ay=" + ay + " az=" + az);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.v("onAccuracyChanged", "event=" + sensor.toString());
    }

}
