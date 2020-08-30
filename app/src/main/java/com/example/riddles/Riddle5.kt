package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class Riddle5 : AppCompatActivity() {
    var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle5)

        var mili = MainActivity.miliseconds

        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }

        timer = Timer()
        timer!!.schedule(timerTask(), mili)
    }

    inner class timerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@Riddle5, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}