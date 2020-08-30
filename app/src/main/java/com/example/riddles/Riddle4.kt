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
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_riddle4.*
import java.util.*

class Riddle4 : AppCompatActivity(),SensorEventListener {
    var timer: Timer? = null
    var sensorManager: SensorManager? = null
    var proximitySensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle4)

        val mili = MainActivity.miliseconds

        val button5 = findViewById<Button>(R.id.button5)
        button5.visibility = View.INVISIBLE
        button5.setOnClickListener {
            val intent = Intent(this, Riddle5::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        timer = Timer()
        timer!!.schedule(timerTask(),mili)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0].compareTo(1.5) <= 0)
                button5.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        sensorManager!!.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(this)
        super.onPause()
    }

    inner class timerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@Riddle4, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}