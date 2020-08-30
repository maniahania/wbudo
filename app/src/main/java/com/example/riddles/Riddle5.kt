package com.example.riddles

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_riddle5.*

class Riddle5 : AppCompatActivity(), SensorEventListener {
    var needle: ImageView? = null
    private val mGravity = FloatArray(3)
    private val mGeomagnetic = FloatArray(3)
    private var azimuth = 0f
    private var currentAzimuth = 0f
    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle5)

        val button6 = findViewById<Button>(R.id.button6)
        button6.visibility = View.INVISIBLE
        needle = findViewById<View>(R.id.needleImage) as ImageView
        button6.setOnClickListener {
            val intent = Intent(this, Riddle6::class.java)
            startActivity(intent)
        }
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mSensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME)
        mSensorManager!!.registerListener(this, mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val alpha = 0.97f
        synchronized(this) {
            if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[0]
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * sensorEvent.values[1]
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * sensorEvent.values[2]
            }
            if (sensorEvent.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[0]
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * sensorEvent.values[1]
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * sensorEvent.values[2]
            }

            val R = FloatArray(9) //rotationMatrix
            val I = FloatArray(9) //InclinationMatrix
            val success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic)
            if (success) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(R, orientation)
                azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
                if ((azimuth < 5 && azimuth>=0)||azimuth>355)
                    button6.visibility = View.VISIBLE
                else button6.visibility = View.INVISIBLE
                azimuth = (azimuth + 360) % 360
                val anim: Animation = RotateAnimation(-currentAzimuth, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                currentAzimuth = azimuth
                anim.duration = 500
                anim.repeatCount = 0
                anim.fillAfter = true
                needleImage!!.startAnimation(anim)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
}