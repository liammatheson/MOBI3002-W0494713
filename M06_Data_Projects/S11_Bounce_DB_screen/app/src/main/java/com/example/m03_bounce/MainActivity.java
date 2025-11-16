package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

// Found tutorial to do put buttons over view here:
// https://code.tutsplus.com/tutorials/android-sdk-creating-custom-views--mobile-14548

public class MainActivity extends AppCompatActivity {


    // bbView is our bouncing ball view
    private BouncingBallView bbView;
    private DBClass dbclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the view object so we can reference it later
        bbView = (BouncingBallView) findViewById(R.id.custView);

        dbclass = new DBClass(this);
    }

    // button action

    public void onRussButtonClick(View v) {

        Log.d("MainActivity  BUTTON", "User tapped the  button ... MAIN");

        EditText ball_name = findViewById(R.id.edit_ball_name);
        String name = ball_name.getText().toString();

        // get spinner values
        SeekBar seekbar_color_r = (SeekBar) findViewById(R.id.seekBar_Color_r);
        SeekBar seekbar_color_g = (SeekBar) findViewById(R.id.seekBar_Color_g);
        SeekBar seekbar_color_b = (SeekBar) findViewById(R.id.seekBar_Color_b);
        SeekBar seekbar_x = (SeekBar) findViewById(R.id.seekBar_X);
        SeekBar seekbar_y = (SeekBar) findViewById(R.id.seekBar_Y);
        SeekBar seekbar_dx = (SeekBar) findViewById(R.id.seekBar_DX);
        SeekBar seekbar_dy = (SeekBar) findViewById(R.id.seekBar_DY);

        int r = seekbar_color_r.getProgress();
        int g = seekbar_color_g.getProgress();
        int b = seekbar_color_b.getProgress();
        int string_x = seekbar_x.getProgress();
        int string_y = seekbar_y.getProgress();
        int string_dx = seekbar_dx.getProgress();
        int string_dy = seekbar_dy.getProgress();

        int viewWidth = bbView.getWidth(); int viewHeight = bbView.getHeight();
        float x = (float) (string_x * viewWidth) / seekbar_x.getMax();
        float y = (float) (string_y * viewHeight) / seekbar_y.getMax();

        float dx = (seekbar_dx.getProgress() / (float)seekbar_dx.getMax()) * 20;
        float dy = (seekbar_dy.getProgress() / (float)seekbar_dy.getMax()) * 20;

        int color = Color.rgb(r, g, b);

        Log.d("MainActivity  BUTTON", "Color=" +r+g+b+ " X="+string_x+" Y="+string_y+" DX="+string_dx+" DY="+string_dy);

        // let the view do something
        bbView.RussButtonPressed(color, x, y, dx, dy, name);

    }

    public void onClearButtonPressed(View v) {
        dbclass.clearAll();
        bbView.balls.clear();
        bbView.invalidate();       // redraw empty view
    }
}