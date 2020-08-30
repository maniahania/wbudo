package com.example.riddles

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_riddle3.*
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.abs


class Riddle3 : AppCompatActivity(), SensorEventListener {
    var timer: Timer? = null
    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    var imgGreen: ImageView? = null
    var imgRed: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle3)

        var mili = MainActivity.miliseconds

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        imgGreen = findViewById<View>(R.id.imageView2) as ImageView
        imgRed = findViewById<View>(R.id.imageView3) as ImageView

        val button4 = findViewById<Button>(R.id.button4)
       // button4.visibility = View.INVISIBLE
        button4.setOnClickListener{
            val intent = Intent(this, Riddle4::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }

        timer = Timer()
        timer!!.schedule(timerTask(), mili)
    }

    override fun onAccuracyChanged(arg0: Sensor, arg1: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        if (abs(x) > abs(y)) {
            if (x < 0) {
                imgGreen!!.x += 20
            }
            if (x > 0) {
                imgGreen!!.x -= 20
            }
        } else {
            if (y < 0) {
                imgGreen!!.y -= 20
            }
            if (y > 0) {
                imgGreen!!.y += 20
            }
        }
        /*if (x > -2 && x < 2 && y > -2 && y < 2) {
            textView!!.text = "Not tilt device"
        }*/
        if (((imgGreen!!.x < imgRed!!.x+100) && (imgGreen!!.x > imgRed!!.x-100)) && ((imgGreen!!.y < imgRed!!.y+100) && (imgGreen!!.y > imgRed!!.y-100)))
            button4.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    inner class timerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@Riddle3, Riddle4::class.java)
            startActivity(intent)
            finish()
        }
    }
}