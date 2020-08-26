package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Riddle2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle2)

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            val intent = Intent(this, Riddle3::class.java)
            startActivity(intent)
        }
    }
}