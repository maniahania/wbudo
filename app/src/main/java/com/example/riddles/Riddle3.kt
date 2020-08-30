package com.example.riddles

import android.content.Context
import android.content.Intent
import android.graphics.Point
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
import kotlinx.android.synthetic.main.activity_riddle3.*
import java.util.*
import kotlin.math.abs


class Riddle3 : AppCompatActivity(), SensorEventListener {
    var timer: Timer? = null
    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    var imgGreen: ImageView? = null
    var imgRed: ImageView? = null
    var maxX: Int? = null
    var maxY: Int? = null
    private val MOVEMENT = 20
    private val DISTANCE = 150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle3)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        imgGreen = findViewById<View>(R.id.imageView2) as ImageView
        imgRed = findViewById<View>(R.id.imageView3) as ImageView

        val mdisp = windowManager.defaultDisplay
        val mdispSize = Point()
        mdisp.getSize(mdispSize)
        maxX = mdispSize.x
        maxY = mdispSize.y

        val mili = MainActivity.miliseconds

        val button4 = findViewById<Button>(R.id.button4)
        button4.visibility = View.INVISIBLE
        button4.setOnClickListener{
            val intent = Intent(this, Riddle4::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }

        if (sensor == null) {
            Toast.makeText(this, "The device has no Accelerometer !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Riddle4::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }

        timer = Timer()
        timer!!.schedule(timerTask(),mili)
    }

    override fun onAccuracyChanged(arg0: Sensor, arg1: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        if (!(((imgGreen!!.x < imgRed!!.x + DISTANCE) && (imgGreen!!.x > imgRed!!.x - DISTANCE)) && ((imgGreen!!.y < imgRed!!.y + DISTANCE) && (imgGreen!!.y > imgRed!!.y - DISTANCE)))) {
            if (abs(x) > abs(y)) {
            if (x < 0 && imgGreen!!.x+ MOVEMENT <= maxX!!) {
                imgGreen!!.x += MOVEMENT
            } else if (x<0 && imgGreen!!.x+ MOVEMENT > maxX!!) {
                imgGreen!!.x = maxX!!.toFloat()
            }
            if (x > 0 && imgGreen!!.x- MOVEMENT >=0) {
                imgGreen!!.x -= MOVEMENT
            } else if(x>0 && imgGreen!!.x- MOVEMENT <0)
            {
                imgGreen!!.x = 0F
            }
        } else {
            if (y < 0 && imgGreen!!.y- MOVEMENT >=0) {
                imgGreen!!.y -= MOVEMENT
            } else if (y<0 && imgGreen!!.y- MOVEMENT <0) {
                imgGreen!!.y = 0F
            }
            if (y > 0 && imgGreen!!.y+ MOVEMENT <=maxY!!) {
                imgGreen!!.y += MOVEMENT
            } else if (y>0 && imgGreen!!.y+ MOVEMENT > maxY!!) {
                imgGreen!!.y = maxY!!.toFloat()
            }
        } }
        /*if (x > -2 && x < 2 && y > -2 && y < 2) {
            textView!!.text = "Not tilt device"
        }*/
        if (((imgGreen!!.x < imgRed!!.x + DISTANCE) && (imgGreen!!.x > imgRed!!.x - DISTANCE)) && ((imgGreen!!.y < imgRed!!.y + DISTANCE) && (imgGreen!!.y > imgRed!!.y - DISTANCE))) {
            button4.visibility = View.VISIBLE
        }
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
            val intent = Intent(this@Riddle3, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}