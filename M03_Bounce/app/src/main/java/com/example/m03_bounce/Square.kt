package com.example.m03_bounce

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import java.util.Random

/**
 * Created by Russ on 08/04/2014.
 */
class Square {
    var radius: Float = 50f // Ball's radius
    var x: Float // Ball's center (x,y)
    var y: Float
    var speedX: Float // Ball's speed
    var speedY: Float
    private val bounds: RectF // Needed for Canvas.drawOval
    private val paint: Paint // The paint style, color used for drawing

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
        paint.setColor(color)

        // random position and speed
        x = radius + r.nextInt(800)
        y = radius + r.nextInt(800)
        speedX = (r.nextInt(10) - 5).toFloat()
        speedY = (r.nextInt(10) - 5).toFloat()
    }

    // Constructor
    constructor(color: Int, x: Float, y: Float, speedX: Float, speedY: Float) {
        bounds = RectF()
        paint = Paint()
        paint.setColor(color)

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
        if (x + radius > box.xMax) {
            speedX = -speedX
            x = box.xMax - radius
        } else if (x - radius < box.xMin) {
            speedX = -speedX
            x = box.xMin + radius
        }
        if (y + radius > box.yMax) {
            speedY = -speedY
            y = box.yMax - radius
        } else if (y - radius < box.yMin) {
            speedY = -speedY
            y = box.yMin + radius
        }
    }

    fun draw_square(canvas: Canvas) {
        bounds.set(x - radius, y - radius, x + radius, y + radius)
        canvas.drawRect(bounds, paint)
    }
}
