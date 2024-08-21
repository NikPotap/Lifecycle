package com.example.lifecycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var inputHeightET: EditText
    private lateinit var inputWeightET: EditText
    private lateinit var goNextBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var resultIMT = ""
        var height = 0.0
        var weight = 0.0
        inputHeightET = findViewById(R.id.inputHeightET)
        inputWeightET = findViewById(R.id.inputWeightET)
        goNextBTN = findViewById(R.id.goNextBTN)
        goNextBTN.setOnClickListener { View ->
            val imm =
                View.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(View.windowToken, 0)
            if (inputHeightET.text.isEmpty() || inputWeightET.text.isEmpty()) {
                Snackbar.make(View, resources.getString(R.string.error_null), Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (inputHeightET.text.toString()
                    .toDoubleOrNull() == null || inputWeightET.text.toString()
                    .toDoubleOrNull() == null
            ) {
                Snackbar.make(
                    View,
                    resources.getString(R.string.error_not_digit),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }
            height = inputHeightET.text.toString().toDouble() / 100
            weight = inputWeightET.text.toString().toDouble()
            if (height < 0.5 || height > 2.5) {
                Snackbar.make(
                    View,
                    resources.getString(R.string.error_of_height),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }
            if (weight < 10 || weight.toInt() > 300) {
                Snackbar.make(
                    View,
                    resources.getString(R.string.error_of_weight),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }
            resultIMT = (weight / (height * height)).toString().take(4).toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("imt", resultIMT)
            startActivity(intent)
        }
    }
}