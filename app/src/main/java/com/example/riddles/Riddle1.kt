package com.example.riddles

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Riddle1 : AppCompatActivity() {
    private var sensorManager: SensorManager? = null
    private var gyroscopeSensor: Sensor? = null
    private var gyroscopeEventListener: SensorEventListener? = null
    var squareImage: ImageView? = null
    var wallImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle1)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        wallImage = findViewById<View>(R.id.wall) as ImageView

        val button = findViewById<Button>(R.id.btn)
        button.visibility= View.INVISIBLE
        button.setOnClickListener{
            val intent = Intent(this, Riddle2::class.java)
            startActivity(intent)
        }

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "The device has no Gyroscope !", Toast.LENGTH_SHORT).show()
            finish()
        }
        gyroscopeEventListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {

                if (sensorEvent.values[2] > 7f) {
                    wallImage!!.x -= 10
                } else if(sensorEvent.values[2] < -7f)
                    wallImage!!.x += 10
                if (wallImage!!.x < -wallImage!!.width||wallImage!!.x > wallImage!!.width)
                    button.visibility = View.VISIBLE

            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(gyroscopeEventListener)
    }
}