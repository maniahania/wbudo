package com.example.riddles

import android.content.Context
import  android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_riddle2.*

class Riddle2 : AppCompatActivity(),SensorEventListener {
    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle2)

        val button3 = findViewById<Button>(R.id.button3)
        button3.visibility = View.INVISIBLE
        button3.setOnClickListener{
            val intent = Intent(this, Riddle3::class.java)
            startActivity(intent)
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        if (event!!.sensor.type == Sensor.TYPE_LIGHT)
            if(event!!.values[0] <30 )
            {
                button3.visibility = View.VISIBLE
            } else {
                button3.visibility = View.INVISIBLE
            }
    }

    override fun onResume() {
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(this)
        super.onPause()
    }
}