package com.example.m03_bounce

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import java.util.Random

/**
 * Created by Russ on 08/04/2014.
 */
class Rectangle {
    var height: Float = 75f
    var width: Float = 100f
    var x: Float // Ball's center (x,y)
    var y: Float
    var speedX: Float // Ball's speed
    var speedY: Float
    private var bounds: RectF? = null // Needed for Canvas.drawOval
    private var paint: Paint? = null // The paint style, color used for drawing

    // Add accelerometer
    // Add ... implements SensorEventListener
    private var ax = 0.0
    private var ay = 0.0
    private var az = 0.0 // acceration from different axis

    fun setAcc(ax: Double, ay: Double, az: Double) {
        this.ax = ax
        this.ay = ay
        this.az = az
    }

    var r: Random = Random() // seed random number generator

    // Constructor
    constructor(color: Int) {
        bounds = RectF()
        paint = Paint()
        paint!!.setColor(color)

        // random position and speed
        x = height + width + r.nextInt(800)
        y = height + width + r.nextInt(800)
        speedX = (r.nextInt(10) - 5).toFloat()
        speedY = (r.nextInt(10) - 5).toFloat()
    }

    // Constructor
    constructor(color: Int, x: Float, y: Float, speedX: Float, speedY: Float) {
        bounds = RectF()
        paint = Paint()
        paint!!.setColor(color)

        // use parameter position and speed
        this.x = x
        this.y = y
        this.speedX = speedX + 10 // super fast super slow
        this.speedY = speedY + 10
    }

    fun moveWithCollisionDetection(box: Box) {
        // Get new (x,y) position
        x += speedX
        y += speedY

        // Add acceleration to speed
        speedX += ax.toFloat()
        speedY += ay.toFloat()

        // Detect collision and react
        if (x + width / 2 > box.xMax) {
            speedX = -speedX
            x = box.xMax - width / 2
        } else if (x - width / 2 < box.xMin) {
            speedX = -speedX
            x = box.xMin + width / 2
        }
        if (y + height / 2 > box.yMax) {
            speedY = -speedY
            y = box.yMax - height / 2
        } else if (y - height / 2 < box.yMin) {
            speedY = -speedY
            y = box.yMin + height / 2
        }
    }

    fun isCollidingWith(other: Rectangle): Boolean { // rectangle on rectangle
        return !(this.x + this.width / 2 < other.x - other.width / 2 || this.x - this.width / 2 > other.x + other.width / 2 || this.y + this.height / 2 < other.y - other.height / 2 || this.y - this.height / 2 > other.y + other.height / 2)
    }

    fun isCollidingWith(other: Square): Boolean { // rectangle on square
        return !(this.x + this.width / 2 < other.x - other.radius || this.x - this.width / 2 > other.x + other.radius || this.y + this.height / 2 < other.y - other.radius || this.y - this.height / 2 > other.y + other.radius)
    }

    fun isCollidingWith(other: Ball): Boolean { // rectangle on circle
        return !(this.x + this.width / 2 < other.x - other.radius || this.x - this.width / 2 > other.x + other.radius || this.y + this.height / 2 < other.y - other.radius || this.y - this.height / 2 > other.y + other.radius)
    }

    //    public void draw_rectangle(Canvas canvas) {
    //        bounds.set(x - width/2, y - height/2, x + width/2, y + height/2);
    //        canvas.drawRect(bounds, paint);
    //    }
    private var bitmap: Bitmap? = null

    constructor(context: Context, resId: Int, x: Float, y: Float, speedX: Float, speedY: Float) {
        this.x = x
        this.y = y
        this.speedX = speedX
        this.speedY = speedY
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dvd)
    }


    fun draw_rectangle(canvas: Canvas) {
        canvas.drawBitmap(bitmap!!, x - bitmap!!.getWidth() / 2, y - bitmap!!.getHeight() / 2, null)
    }
}
