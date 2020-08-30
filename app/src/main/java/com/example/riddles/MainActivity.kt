package com.example.riddles

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        var miliseconds: Long = 10000
    }

    var longitude: TextView? = null
    var latitude: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        longitude = findViewById<View>(R.id.longitude_textview2) as TextView
        latitude = findViewById<View>(R.id.latitude_textview2) as TextView

        LocationHelper().startListeningUserLocation( //ten kod wykonuje sie pozniej od kodu po tej funkcji
            this,
            object : LocationHelper.MyLocationListener {
                override fun onLocationChanged(location: Location) {
                    longitude!!.text = location.longitude.toString()
                    latitude!!.text = location.latitude.toString()

                    if (abs(location.latitude) < 30) {
                        miliseconds = 5000
                    } else if (abs(location.latitude) < 60) {
                        miliseconds = 8000
                    } else if (abs(location.latitude) >= 60) {
                        miliseconds = 10000
                    }

                    if (abs(location.longitude) < 60) {
                        miliseconds -= 500
                    } else if (abs(location.longitude) < 120) {
                        miliseconds += 500
                    } else if (abs(location.longitude) <= 180) {
                        miliseconds += 1000
                    }
                }
            })
        button.setOnClickListener{
            val intent = Intent(this, Riddle1::class.java)
            startActivity(intent)
            Log.d("tag0",miliseconds.toString())
        }
    }
    }
