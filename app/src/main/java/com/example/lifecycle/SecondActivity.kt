package com.example.lifecycle

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var resultTV: TextView
    private lateinit var resultRateTV: TextView
    private lateinit var imageIV: ImageView
    private lateinit var contentTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultTV = findViewById(R.id.resultTV)
        resultRateTV = findViewById(R.id.resultRateTV)
        imageIV = findViewById(R.id.imageIV)
        contentTV = findViewById(R.id.contentTV)
        val imt = intent.getStringExtra("imt")
        resultTV.text = imt
        val selector = imt?.toDouble()!!
        if (imt?.toDouble()!! < 18.6) {
            resultRateTV.text = resources.getString(R.string.imt_is_under)
            imageIV.setImageResource(R.drawable.skinny)
            contentTV.text = resources.getString(R.string.imt_is_under_recommendation)
        } else {
            if (imt?.toDouble()!! > 25.0) {
                resultRateTV.text = resources.getString(R.string.imt_is_over)
                imageIV.setImageResource(R.drawable.fat)
                contentTV.text = resources.getString(R.string.imt_is_over_recommendation)
            } else {
                resultRateTV.text = resources.getString(R.string.imt_is_normal)
                imageIV.setImageResource(R.drawable.normal)
                contentTV.text = resources.getString(R.string.imt_is_normal_recommendation)
            }
        }
    }
}