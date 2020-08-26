package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Riddle5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle5)

        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener{
            val intent = Intent(this, Riddle6::class.java)
            startActivity(intent)
        }
    }
}