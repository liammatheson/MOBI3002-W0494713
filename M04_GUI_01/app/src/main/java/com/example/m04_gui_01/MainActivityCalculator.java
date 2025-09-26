package com.example.m04_gui_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_screen_size);

        // Action when "Add" button is pressed
        Button button = (Button) findViewById(R.id.b_Add);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("M01_Calculator ADD BUTTON", "User tapped the Add button");
                Log.d("M01_Calculator ADD BUTTON", "button =>"+button.toString());
                Log.d("M01_Calculator ADD BUTTON", "button =>"+button.getText());
                Log.d("M01_Calculator ADD BUTTON", "button =>"+button.getId());

                Double d1 = 0.0;
                Double d2 = 0.0;
                Double answer = 0.0;

                EditText textN1 = (EditText) findViewById(R.id.editText_field_1);
                EditText textN2 = (EditText) findViewById(R.id.editText_field_2);
                // we actually don't need to get ans from screen
                EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

                try {
                    d1 = Double.parseDouble(textN1.getText().toString());
                    d2 = Double.parseDouble(textN2.getText().toString());
                    answer = d1 + d2;
                }
                catch (Exception e) {
                    Log.w("M01_Calculator ADD BUTTON", "Add Selected with no inputs ... " + answer);
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString());

                // log what we are doing
                Log.w("M01_Calculator ADD BUTTON", "Add Selected with => " + d1 + " + " + d2 + "=" + answer);
            }
        });

        // subtract

        Button button2 = (Button) findViewById(R.id.b_Subtract);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("M01_Calculator SUBTRACT BUTTON", "User tapped the Subtract button");
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>"+button2.toString());
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>"+button2.getText());
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>"+button2.getId());

                Double d1 = 0.0;
                Double d2 = 0.0;
                Double answer = 0.0;

                EditText textN1 = (EditText) findViewById(R.id.editText_field_1);
                EditText textN2 = (EditText) findViewById(R.id.editText_field_2);
                // we actually don't need to get ans from screen
                EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

                try {
                    d1 = Double.parseDouble(textN1.getText().toString());
                    d2 = Double.parseDouble(textN2.getText().toString());
                    answer = d1 - d2;
                }
                catch (Exception e) {
                    Log.w("M01_Calculator SUBTRACT BUTTON", "Subtract Selected with no inputs ... " + answer);
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString());

                // log what we are doing
                Log.w("M01_Calculator SUBTRACT BUTTON", "Subtract Selected with => " + d1 + " - " + d2 + "=" + answer);
            }
        });

        // multiply

        Button button3 = (Button) findViewById(R.id.b_Multiply);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("M01_Calculator MULTIPLY BUTTON", "User tapped the Multiply button");
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>"+button3.toString());
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>"+button3.getText());
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>"+button3.getId());

                Double d1 = 0.0;
                Double d2 = 0.0;
                Double answer = 0.0;

                EditText textN1 = (EditText) findViewById(R.id.editText_field_1);
                EditText textN2 = (EditText) findViewById(R.id.editText_field_2);
                // we actually don't need to get ans from screen
                EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

                try {
                    d1 = Double.parseDouble(textN1.getText().toString());
                    d2 = Double.parseDouble(textN2.getText().toString());
                    answer = d1 * d2;
                }
                catch (Exception e) {
                    Log.w("M01_Calculator MULTIPLY BUTTON", "Multiply Selected with no inputs ... " + answer);
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString());

                // log what we are doing
                Log.w("M01_Calculator MULTIPLY BUTTON", "Multiply Selected with => " + d1 + " * " + d2 + "=" + answer);
            }
        });

        // divide

        Button button4 = (Button) findViewById(R.id.b_Divide);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("M01_Calculator DIVIDE BUTTON", "User tapped the Divide button");
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>"+button4.toString());
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>"+button4.getText());
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>"+button4.getId());

                Double d1 = 0.0;
                Double d2 = 0.0;
                Double answer = 0.0;

                EditText textN1 = (EditText) findViewById(R.id.editText_field_1);
                EditText textN2 = (EditText) findViewById(R.id.editText_field_2);
                // we actually don't need to get ans from screen
                EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

                try {
                    d1 = Double.parseDouble(textN1.getText().toString());
                    d2 = Double.parseDouble(textN2.getText().toString());
                    answer = d1 / d2;
                }
                catch (Exception e) {
                    Log.w("M01_Calculator DIVIDE BUTTON", "Divide Selected with no inputs ... " + answer);
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString());

                // log what we are doing
                Log.w("M01_Calculator DIVIDE BUTTON", "Divide Selected with => " + d1 + " / " + d2 + "=" + answer);
            }
        });

//        Log.v();


    }

}
