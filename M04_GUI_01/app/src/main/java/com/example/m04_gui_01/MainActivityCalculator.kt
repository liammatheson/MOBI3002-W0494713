package com.example.m04_gui_01

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivityCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity_screen_size)

        // Action when "Add" button is pressed
        val button = findViewById<View?>(R.id.b_Add) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("M01_Calculator ADD BUTTON", "User tapped the Add button")
                Log.d("M01_Calculator ADD BUTTON", "button =>" + button.toString())
                Log.d("M01_Calculator ADD BUTTON", "button =>" + button.getText())
                Log.d("M01_Calculator ADD BUTTON", "button =>" + button.getId())

                var d1 = 0.0
                var d2 = 0.0
                var answer = 0.0

                val textN1 = findViewById<View?>(R.id.editText_field_1) as EditText
                val textN2 = findViewById<View?>(R.id.editText_field_2) as EditText
                // we actually don't need to get ans from screen
                val textANS = findViewById<View?>(R.id.editTextNumAns) as EditText

                try {
                    d1 = textN1.getText().toString().toDouble()
                    d2 = textN2.getText().toString().toDouble()
                    answer = d1 + d2
                } catch (e: Exception) {
                    Log.w("M01_Calculator ADD BUTTON", "Add Selected with no inputs ... " + answer)
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString())

                // log what we are doing
                Log.w(
                    "M01_Calculator ADD BUTTON",
                    "Add Selected with => " + d1 + " + " + d2 + "=" + answer
                )
            }
        })

        // subtract
        val button2 = findViewById<View?>(R.id.b_Subtract) as Button
        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("M01_Calculator SUBTRACT BUTTON", "User tapped the Subtract button")
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>" + button2.toString())
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>" + button2.getText())
                Log.d("M01_Calculator SUBTRACT BUTTON", "button =>" + button2.getId())

                var d1 = 0.0
                var d2 = 0.0
                var answer = 0.0

                val textN1 = findViewById<View?>(R.id.editText_field_1) as EditText
                val textN2 = findViewById<View?>(R.id.editText_field_2) as EditText
                // we actually don't need to get ans from screen
                val textANS = findViewById<View?>(R.id.editTextNumAns) as EditText

                try {
                    d1 = textN1.getText().toString().toDouble()
                    d2 = textN2.getText().toString().toDouble()
                    answer = d1 - d2
                } catch (e: Exception) {
                    Log.w(
                        "M01_Calculator SUBTRACT BUTTON",
                        "Subtract Selected with no inputs ... " + answer
                    )
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString())

                // log what we are doing
                Log.w(
                    "M01_Calculator SUBTRACT BUTTON",
                    "Subtract Selected with => " + d1 + " - " + d2 + "=" + answer
                )
            }
        })

        // multiply
        val button3 = findViewById<View?>(R.id.b_Multiply) as Button
        button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("M01_Calculator MULTIPLY BUTTON", "User tapped the Multiply button")
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>" + button3.toString())
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>" + button3.getText())
                Log.d("M01_Calculator MULTIPLY BUTTON", "button =>" + button3.getId())

                var d1 = 0.0
                var d2 = 0.0
                var answer = 0.0

                val textN1 = findViewById<View?>(R.id.editText_field_1) as EditText
                val textN2 = findViewById<View?>(R.id.editText_field_2) as EditText
                // we actually don't need to get ans from screen
                val textANS = findViewById<View?>(R.id.editTextNumAns) as EditText

                try {
                    d1 = textN1.getText().toString().toDouble()
                    d2 = textN2.getText().toString().toDouble()
                    answer = d1 * d2
                } catch (e: Exception) {
                    Log.w(
                        "M01_Calculator MULTIPLY BUTTON",
                        "Multiply Selected with no inputs ... " + answer
                    )
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString())

                // log what we are doing
                Log.w(
                    "M01_Calculator MULTIPLY BUTTON",
                    "Multiply Selected with => " + d1 + " * " + d2 + "=" + answer
                )
            }
        })

        // divide
        val button4 = findViewById<View?>(R.id.b_Divide) as Button
        button4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("M01_Calculator DIVIDE BUTTON", "User tapped the Divide button")
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>" + button4.toString())
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>" + button4.getText())
                Log.d("M01_Calculator DIVIDE BUTTON", "button =>" + button4.getId())

                var d1 = 0.0
                var d2 = 0.0
                var answer = 0.0

                val textN1 = findViewById<View?>(R.id.editText_field_1) as EditText
                val textN2 = findViewById<View?>(R.id.editText_field_2) as EditText
                // we actually don't need to get ans from screen
                val textANS = findViewById<View?>(R.id.editTextNumAns) as EditText

                try {
                    d1 = textN1.getText().toString().toDouble()
                    d2 = textN2.getText().toString().toDouble()
                    answer = d1 / d2
                } catch (e: Exception) {
                    Log.w(
                        "M01_Calculator DIVIDE BUTTON",
                        "Divide Selected with no inputs ... " + answer
                    )
                }

                // Set the Answer into the the answer field
                textANS.setText(answer.toString())

                // log what we are doing
                Log.w(
                    "M01_Calculator DIVIDE BUTTON",
                    "Divide Selected with => " + d1 + " / " + d2 + "=" + answer
                )
            }
        })


        //        Log.v();
    }
}
