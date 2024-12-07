//package com.example.contractiontimer
//
//import android.os.Bundle
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.button.MaterialButton
//
//class ContractionDetailActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contraction_detail)
//
//        val contractionNumber = intent.getIntExtra("contraction_number", 0)
//        val contractionDuration = intent.getStringExtra("contraction_duration")
//        val contractionFrequency = intent.getStringExtra("contraction_frequency")
//        val contractionTime = intent.getStringExtra("contraction_time")
//        val contractionStart = intent.getStringExtra("contraction_start")
//        val contractionEnd = intent.getStringExtra("contraction_end")
//
//        findViewById<TextView>(R.id.tv_contraction_number).text = "Contraction Number: $contractionNumber"
//        findViewById<TextView>(R.id.tv_contraction_duration).text = "Duration: $contractionDuration"
//        findViewById<TextView>(R.id.tv_contraction_frequency).text = "Frequency: $contractionFrequency"
//        findViewById<TextView>(R.id.tv_contraction_time).text = "Time: $contractionTime"
//        findViewById<TextView>(R.id.tv_contraction_start).text = "Started: $contractionStart"
//        findViewById<TextView>(R.id.tv_contraction_end).text = "Ended: $contractionEnd"
//
//        findViewById<MaterialButton>(R.id.btn_delete_contraction).setOnClickListener {
//            // Handle deletion logic here
//            Toast.makeText(this, "Contraction deleted", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//    }
//}

package com.example.contractiontimer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ContractionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contraction_detail)

        val contractionNumber = intent.getIntExtra("contraction_number", 0)
        val contractionDuration = intent.getStringExtra("contraction_duration")
        val contractionFrequency = intent.getStringExtra("contraction_frequency")
        val contractionStart = intent.getStringExtra("contraction_start")
        val contractionEnd = intent.getStringExtra("contraction_end")

        findViewById<TextView>(R.id.tv_contraction_number).text = "Contraction Number: $contractionNumber"
        findViewById<TextView>(R.id.tv_contraction_duration).text = "Duration: $contractionDuration"
        findViewById<TextView>(R.id.tv_contraction_frequency).text = "Frequency: $contractionFrequency"
        findViewById<TextView>(R.id.tv_contraction_start).text = "Started: $contractionStart"
        findViewById<TextView>(R.id.tv_contraction_end).text = "Ended: $contractionEnd"

        findViewById<MaterialButton>(R.id.btn_delete_contraction).setOnClickListener {
            // Handle deletion logic here
            val resultIntent = Intent().apply {
                putExtra("contraction_number", contractionNumber)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            Toast.makeText(this, "Contraction deleted", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}