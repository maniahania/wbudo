package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Riddle3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle3)

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener{
            val intent = Intent(this, Riddle4::class.java)
            startActivity(intent)
        }
    }
}