package com.example.m03_bounce

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.m03_bounce.Rectangle
import java.util.Random
import kotlin.math.abs

/**
 * Created by Russ on 08/04/2014.
 */
class BouncingBallView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val balls: ArrayList<Ball> = ArrayList<Ball>() // list of Balls
    private val squares: ArrayList<Square> = ArrayList<Square>()
    private val rectangles: ArrayList<Rectangle> = ArrayList<Rectangle>()
    private var ball_1: Ball // use this to reference first ball in arraylist
    private val square_1: Square? = null // use this to reference first ball in arraylist
    private val rectangle_1: Rectangle? = null // use this to reference first ball in arraylist
    private val box: Box
    private var collision_counter = 0

    // For touch inputs - previous touch (x, y)
    private var previousX = 0f
    private var previousY = 0f

    // Called back to draw the view. Also called after invalidate().
    override fun onDraw(canvas: Canvas?) {
        Log.v("BouncingBallView", "onDraw")

        // Draw the components
        box.draw(canvas)


        //canvas.drawARGB(0,25,25,25);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (b in balls) {
            b.draw(canvas) //draw each ball in the list
            b.moveWithCollisionDetection(box) // Update the position of the ball


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

        for (s in squares) {
            s.draw_square(canvas) //draw each ball in the list
            s.moveWithCollisionDetection(box) // Update the position of the ball
        }

        for (r in rectangles) {
            r.draw_rectangle(canvas) //draw each ball in the list
            r.moveWithCollisionDetection(box) // Update the position of the ball

            for (b in balls) {
                if (r.isCollidingWith(b)) {
                    collision_counter++
                    Log.w("Collision", "Rectangle hit Ball! Collisions: " + collision_counter)
                }
            }

            for (s in squares) {
                if (r.isCollidingWith(s)) {
                    collision_counter++
                    Log.w("Collision", "Rectangle hit Square! " + collision_counter)
                }
            }

            for (other in rectangles) {
                if (r !== other && r.isCollidingWith(other)) {
                    collision_counter++
                    Log.w("Collision", "Rectangle hit Rectangle! " + collision_counter)
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
        this.invalidate()
    }

    // Called back when the view is first created or its size changes.
    public override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        // Set the movement bounds for the ball
        box.set(0, 0, w, h)
        Log.w("BouncingBallLog", "onSizeChanged w=" + w + " h=" + h)
    }

    // Touch-input handler // 50 should be good diff for squares
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentX = event.getX()
        val currentY = event.getY()
        val deltaX: Float
        val deltaY: Float
        val scalingFactor: Float = 5.0f / (if (box.xMax > box.yMax) box.yMax else box.xMax)
        when (event.getAction()) {
            MotionEvent.ACTION_MOVE -> {
                // Modify rotational angles according to movement
                deltaX = currentX - previousX
                deltaY = currentY - previousY
                ball_1.speedX += deltaX * scalingFactor
                ball_1.speedY += deltaY * scalingFactor
                //Log.w("BouncingBallLog", " Xspeed=" + ball_1.speedX + " Yspeed=" + ball_1.speedY);
                Log.w(
                    "BouncingBallLog",
                    "x,y= " + previousX + " ," + previousY + "  Xdiff=" + deltaX + " Ydiff=" + deltaY
                )
                val rand = Random()
                val randColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))

                if (abs(deltaX) < 5 || abs(deltaY) < 5) {
                    balls.add(Ball(randColor, previousX, previousY, deltaX, deltaY))
                } else {
                    squares.add(Square(randColor, previousX, previousY, deltaX, deltaY))
                }


                // A way to clear list when too many balls
                if (balls.size > 50) {
                    // leave first ball, remove the rest
                    Log.v("BouncingBallLog", "too many balls, clear back to 1")
                    balls.clear()
                    balls.add(Ball(Color.RED))
                    ball_1 = balls.get(0) // points ball_1 to the first (zero-ith) element of list
                }
            }
        }
        // Save current x, y
        previousX = currentX
        previousY = currentY

        // Try this (remove other invalidate(); )
        //this.invalidate();
        return true // Event handled
    }

    var rand: Random = Random()

    init {
        Log.v("BouncingBallView", "Constructor BouncingBallView")

        // create the box
        box = Box(Color.rgb(84, 204, 184)) // ARGB

        //ball_1 = new Ball(Color.GREEN);
        balls.add(Ball(Color.GREEN))
        ball_1 = balls.get(0) // points ball_1 to the first; (zero-ith) element of list
        Log.w("BouncingBallLog", "Just added a bouncing ball")

        //ball_2 = new Ball(Color.CYAN);
        balls.add(Ball(Color.CYAN))
        Log.w("BouncingBallLog", "Just added another bouncing ball")

        // To enable keypad
        this.setFocusable(true)
        this.requestFocus()
        // To enable touch mode
        this.setFocusableInTouchMode(true)
    }

    // called when button is pressed
    fun RussButtonPressed() {
        Log.d("BouncingBallView  BUTTON", "User tapped the  button ... VIEW")

        //get half of the width and height as we are working with a circle
        val viewWidth = this.getMeasuredWidth()
        val viewHeight = this.getMeasuredHeight()

        // make random x,y, velocity
        val x = rand.nextInt(viewWidth)
        val y = rand.nextInt(viewHeight)
        val dx = rand.nextInt(50)
        val dy = rand.nextInt(20)

        balls.add(Ball(Color.RED, x, y, dx, dy)) // add ball at every touch event
    }

    fun RectangleButtonPressed() {
        Log.d("BouncingBallView  BUTTON", "User tapped the rectangle button ... VIEW")

        //get half of the width and height as we are working with a circle
        val viewWidth = this.getMeasuredWidth()
        val viewHeight = this.getMeasuredHeight()

        // make random x,y, velocity
        val x = rand.nextInt(viewWidth)
        val y = rand.nextInt(viewHeight)
        val dx = rand.nextInt(50)
        val dy = rand.nextInt(20)

        rectangles.add(
            Rectangle(
                getContext(),
                R.drawable.dvd,
                x,
                y,
                dx,
                dy
            )
        ) // add ball at every touch event
    }
}
