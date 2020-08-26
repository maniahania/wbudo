package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Riddle4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle4)

        val button5 = findViewById<Button>(R.id.button5)
        button5.setOnClickListener{
            val intent = Intent(this, Riddle5::class.java)
            startActivity(intent)
        }
    }
}