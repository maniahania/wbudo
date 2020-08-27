package com.example.riddles

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_riddle4.*

class Riddle4 : AppCompatActivity() {
    var mySensorManager: SensorManager? = null
    var myProximitySensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle4)

        val button5 = findViewById<Button>(R.id.button5)
        button5.visibility = View.INVISIBLE
        button5.setOnClickListener{
            val intent = Intent(this, Riddle5::class.java)
            startActivity(intent)
        }

        mySensorManager = getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager
        myProximitySensor = mySensorManager!!.getDefaultSensor(
            Sensor.TYPE_PROXIMITY
        )
            mySensorManager!!.registerListener(
                proximitySensorEventListener,
                myProximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
    }

    var proximitySensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(
            sensor: Sensor,
            accuracy: Int
        ) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0].compareTo(1.5) <= 0)
                    button5.visibility = View.VISIBLE
            }
        }
    }
}