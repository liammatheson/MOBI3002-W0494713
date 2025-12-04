package com.example.m03_bounce

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

// Found tutorial to do put buttons over view here:
// https://code.tutsplus.com/tutorials/android-sdk-creating-custom-views--mobile-14548
class MainActivity : AppCompatActivity() {
    // bbView is our bouncing ball view
    private var bbView: BouncingBallView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the view object so we can reference it later
        bbView = findViewById<View?>(R.id.custView) as BouncingBallView
    }

    // button action
    fun onRussButtonClick(v: View?) {
        Log.d("MainActivity  BUTTON", "User tapped the  button ... MAIN")

        // let the view do something
        bbView.RussButtonPressed()
    }

    fun onRectangleButtonClick(v: View?) {
        Log.d("MainActivity  BUTTON", "User tapped the  button ... rectangle")

        // let the view do something
        bbView.RectangleButtonPressed()
    }
}

