package com.example.m03_bounce

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * Created by Russ on 08/04/2014.
 */
class Box(color: Int) {
    var xMin: Int = 0
    var xMax: Int = 0
    var yMin: Int = 0
    var yMax: Int = 0
    private val paint: Paint // paint style and color
    private val bounds: Rect

    init {
        paint = Paint()
        paint.setColor(color)
        bounds = Rect()
    }

    fun set(x: Int, y: Int, width: Int, height: Int) {
        xMin = x
        xMax = x + width - 1
        yMin = y
        yMax = y + height - 1
        // The box's bounds do not change unless the view's size changes
        bounds.set(xMin, yMin, xMax, yMax)
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(bounds, paint)
    }
}
