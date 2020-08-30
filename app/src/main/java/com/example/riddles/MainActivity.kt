package com.example.riddles

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    companion object {
        var miliseconds: Long = 10000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        LocationHelper().startListeningUserLocation(
            this,
            object : LocationHelper.MyLocationListener {
                override fun onLocationChanged(location: Location) {

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
        }
    }

}