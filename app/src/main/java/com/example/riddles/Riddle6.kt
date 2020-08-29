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


class Riddle6 : AppCompatActivity() {

    var longitude: TextView? = null
    var latitude: TextView? = null
    var timer: Timer? = null
    var lat: Double = 0.0
    var long: Double = 0.0
    var taskDone: Int = 1
    var miliseconds: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riddle6)
        val button7 = findViewById<Button>(R.id.button7)
        button7.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            timer!!.cancel()
            finish()
        }
        longitude = findViewById<View>(R.id.longitude_textview) as TextView
        latitude = findViewById<View>(R.id.latitude_textview) as TextView
        timer = Timer()

        LocationHelper().startListeningUserLocation( //ten kod wykonuje sie pozniej od kodu po tej funkcji
            this,
            object : LocationHelper.MyLocationListener {
                override fun onLocationChanged(location: Location) {
                    Log.d("first0", "lat: ")
                    longitude!!.text = location.longitude.toString()
                    latitude!!.text = location.latitude.toString()
                    Log.d("first1", "lat: " + lat.toString())
                    lat = location.latitude
                    Log.d("first", "lat: " + lat.toString())
                    long = location.longitude
                }
            })

        Log.d("second", "lat: " + lat.toString())
        Log.d("first", "long: " + long.toString())

        if (lat < 30) {
            Log.d("nowe", "lat < 30 stare: " + miliseconds.toString())
            miliseconds = 1000
            Log.d("nowe", "lat < 30 nowe: " + miliseconds.toString())
        } else if (lat < 60) {
            miliseconds = 6000
        } else if (lat >= 60) {
            miliseconds = 10000
        }

        if (long < 60) {
            Log.d("nowe", "long < 60 stare" + miliseconds.toString())
            miliseconds -= 500
            Log.d("nowe", "long < 60 nowe" + miliseconds.toString())
        } else if (long < 120) {
            miliseconds += 500
        } else if (long <= 180) {
            miliseconds += 1000
        }
        Log.d("nowe", "timer: " + miliseconds.toString())
        Log.d("nowe", "timer lat: " + lat.toString())
        timer!!.schedule(timerTask(),miliseconds)
    }
    inner class timerTask:TimerTask(){
        override fun run() {
            val intent = Intent(this@Riddle6, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        }


}