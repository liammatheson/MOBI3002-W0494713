package com.russ.spaceship;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

        dtext   = (EditText)findViewById(R.id.text_debug);
        bbView.setTextArea(dtext);
    }


    Set<Integer> devices = new HashSet<>();

    EditText  dtext;

    //int deviceID = 0;   //Debug
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        int deviceID = event.getDeviceId();

        // deviceID = (deviceID + 1) % 3;     // Debug

//        dtext   = (EditText)findViewById(R.id.text_debug);
//        dtext.setText("Dev=" + deviceID + " code="+keyCode);

        if (devices.contains(deviceID)) {
            // already seen this device, use the one created
            bbView.shipEvent(deviceID, keyCode);
        } else {
            // new device, create it and use it
            devices.add(deviceID);
            bbView.newShip(deviceID);
        }

        return true;
    }
}