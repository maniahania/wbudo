package com.example.riddles

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class Riddle1 : AppCompatActivity() {

    var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle1)

        var mili = MainActivity.miliseconds

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Riddle2::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }
        timer = Timer()
        timer!!.schedule(timerTask(), mili)
    }

    inner class timerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@Riddle1, Riddle2::class.java)
            startActivity(intent)
            finish()
        }
    }
}