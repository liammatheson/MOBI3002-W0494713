package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

// Found tutorial to do put buttons over view here:
// https://code.tutsplus.com/tutorials/android-sdk-creating-custom-views--mobile-14548

public class MainActivity extends AppCompatActivity {


    // bbView is our bouncing ball view
    private BouncingBallView bbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the view object so we can reference it later
        bbView = (BouncingBallView) findViewById(R.id.custView);
    }

    // button action

    public void onRussButtonClick(View v) {

        Log.d("MainActivity  BUTTON", "User tapped the  button ... MAIN");

        // let the view do something
        bbView.RussButtonPressed();

    }

    EditText  dtext;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.d("debug", "onkeydown...toString " + event.toString());
//        Log.d("debug", "onkeydown...getAction " + event.getAction());
//        Log.d("debug", "onkeydown...getDeviceId " + event.getDeviceId());
//        Log.d("debug", "onkeydown...getDeviceId " + event.getSource());

        dtext   = (EditText)findViewById(R.id.text_debug);
        dtext.setText("getDeviceId " + event.getDeviceId());


        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                Log.d("debug", "up");
                bbView.upPressed();
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                Log.d("debug", "down");
                bbView.downPressed();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Log.d("debug", "right");
                bbView.rightPressed();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                Log.d("debug", "left");
                bbView.leftPressed();
                break;
            case KeyEvent.KEYCODE_BACK:
                Log.d("debug", "back");
                bbView.clearAll();
                break;
            case KeyEvent.KEYCODE_ESCAPE:
                Log.d("debug", "esc");
                bbView.clearAll();
                Toast.makeText(MainActivity.this, "ESC!", Toast.LENGTH_SHORT).show();
                break;
            default:
                bbView.clearAll();
                return false;
        }
        return true;
    }
}