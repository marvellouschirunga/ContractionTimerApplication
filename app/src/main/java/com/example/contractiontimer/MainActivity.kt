//package com.example.contractiontimer
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import java.text.DateFormat
//import java.util.*
//
//class MainActivity : AppCompatActivity() {
//
//    private var startTime: Long = 0
//    private var isTiming: Boolean = false
//    private val handler = Handler(Looper.getMainLooper())
//    private lateinit var runnable: Runnable
//    private var lastContractionTime: Long = 0
//
//    private val durations = mutableListOf<Long>()
//    private val contractionTimes = mutableListOf<Long>()
//    private val contractions = mutableListOf<Contraction>()
//    private lateinit var contractionAdapter: ContractionAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Initialize views
//        val buttonStartStop: Button = findViewById(R.id.button_start_stop)
//        val rvContractions: RecyclerView = findViewById(R.id.contraction_list)
//        val tvAvgDuration: TextView = findViewById(R.id.tv_avg_duration)
//        val tvAvgFrequency: TextView = findViewById(R.id.tv_avg_frequency)
//        val tvContractionsPerHour: TextView = findViewById(R.id.tv_contractions_per_hour)
//
//        // Initialize RecyclerView
//        contractionAdapter = ContractionAdapter(contractions)
//        rvContractions.layoutManager = LinearLayoutManager(this)
//        rvContractions.adapter = contractionAdapter
//
//        // Define the timer runnable
//        runnable = object : Runnable {
//            override fun run() {
//                val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
//                val minutes = elapsedSeconds / 60
//                val seconds = elapsedSeconds % 60
//                buttonStartStop.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
//                handler.postDelayed(this, 1000)
//            }
//        }
//
//        // Handle start/stop button functionality
//        buttonStartStop.setOnClickListener {
//            if (!isTiming) {
//                // Start the timer
//                isTiming = true
//                startTime = System.currentTimeMillis()
//                buttonStartStop.text = getString(R.string.contraction_stopped)
//                handler.post(runnable)
//            } else {
//                // Stop the timer
//                isTiming = false
//                handler.removeCallbacks(runnable)
//
//                // Calculate duration
//                val duration = (System.currentTimeMillis() - startTime) / 1000
//                durations.add(duration)
//
//                // Calculate frequency
//                val frequency = if (lastContractionTime != 0L) {
//                    (startTime - lastContractionTime) / 1000
//                } else {
//                    0
//                }
//                lastContractionTime = startTime
//
//                // Add new contraction
//                val startTimeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(Date(startTime))
//                val endTimeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(Date(System.currentTimeMillis()))
//                contractions.add(0, Contraction(contractions.size + 1, formatTime(duration), formatTime(frequency), startTimeFormatted, endTimeFormatted))
//                contractionTimes.add(startTime)
//                contractionAdapter.notifyItemInserted(0)
//
//                // Update averages and metrics
//                updateMetrics(tvAvgDuration, tvAvgFrequency, tvContractionsPerHour)
//
//                // Reset button text
//                buttonStartStop.text = getString(R.string.contraction_started)
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_OK) {
//            val contractionNumber = data?.getIntExtra("contraction_number", -1) ?: -1
//            if (contractionNumber != -1) {
//                val index = contractions.indexOfFirst { it.number == contractionNumber }
//                if (index != -1) {
//                    contractions.removeAt(index)
//                    contractionAdapter.notifyItemRemoved(index)
//                    // Update averages and metrics
//                    updateMetrics(findViewById(R.id.tv_avg_duration), findViewById(R.id.tv_avg_frequency), findViewById(R.id.tv_contractions_per_hour))
//                }
//            }
//        }
//    }
//
//    private fun updateMetrics(tvAvgDuration: TextView, tvAvgFrequency: TextView, tvContractionsPerHour: TextView) {
//        // Calculate average duration
//        val avgDuration = if (durations.isNotEmpty()) durations.average().toLong() else 0
//        tvAvgDuration.text = formatTime(avgDuration)
//
//        // Calculate average frequency
//        val frequencies = contractionTimes.zipWithNext { a, b -> (b - a) / 1000 }
//        val avgFrequency = if (frequencies.isNotEmpty()) frequencies.average().toLong() else 0
//        tvAvgFrequency.text = formatTime(avgFrequency)
//
//        // Calculate contractions per hour
//        val currentTime = System.currentTimeMillis()
//        val oneHourAgo = currentTime - (60 * 60 * 1000) // 60 minutes in milliseconds
//        val contractionsInLastHour = contractionTimes.filter { it > oneHourAgo }.size
//        tvContractionsPerHour.text = contractionsInLastHour.toString()
//    }
//
//    private fun formatTime(seconds: Long): String {
//        val minutes = seconds / 60
//        val remainingSeconds = seconds % 60
//        return String.format(Locale.getDefault(), "%d:%02d", minutes, remainingSeconds)
//    }
//
//    companion object {
//        const val REQUEST_CODE_DELETE = 1
//    }
//}


package com.example.contractiontimer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var startTime: Long = 0
    private var isTiming: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private var lastContractionTime: Long = 0

    private val durations = mutableListOf<Long>()
    private val contractionTimes = mutableListOf<Long>()
    private val contractions = mutableListOf<Contraction>()
    private lateinit var contractionAdapter: ContractionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        val buttonStartStop: Button = findViewById(R.id.button_start_stop)
        val rvContractions: RecyclerView = findViewById(R.id.contraction_list)
        val tvAvgDuration: TextView = findViewById(R.id.tv_avg_duration)
        val tvAvgFrequency: TextView = findViewById(R.id.tv_avg_frequency)
        val tvContractionsPerHour: TextView = findViewById(R.id.tv_contractions_per_hour)

        // Initialize RecyclerView
        contractionAdapter = ContractionAdapter(contractions, this)
        rvContractions.layoutManager = LinearLayoutManager(this)
        rvContractions.adapter = contractionAdapter

        // Define the timer runnable
        runnable = object : Runnable {
            override fun run() {
                val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
                val minutes = elapsedSeconds / 60
                val seconds = elapsedSeconds % 60
                buttonStartStop.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                handler.postDelayed(this, 1000)
            }
        }

        // Handle start/stop button functionality
        buttonStartStop.setOnClickListener {
            if (!isTiming) {
                // Start the timer
                isTiming = true
                startTime = System.currentTimeMillis()
                buttonStartStop.text = getString(R.string.contraction_stopped)
                handler.post(runnable)
            } else {
                // Stop the timer
                isTiming = false
                handler.removeCallbacks(runnable)

                // Calculate duration
                val duration = (System.currentTimeMillis() - startTime) / 1000
                durations.add(duration)

                // Calculate frequency
                val frequency = if (lastContractionTime != 0L) {
                    (startTime - lastContractionTime) / 1000
                } else {
                    0
                }
                lastContractionTime = startTime

                // Add new contraction
                val startTimeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(Date(startTime))
                val endTimeFormatted = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(Date(System.currentTimeMillis()))
                contractions.add(0, Contraction(contractions.size + 1, formatTime(duration), formatTime(frequency), startTimeFormatted, endTimeFormatted))
                contractionTimes.add(startTime)
                contractionAdapter.notifyItemInserted(0)

                // Update averages and metrics
                updateMetrics(tvAvgDuration, tvAvgFrequency, tvContractionsPerHour)

                // Reset button text
                buttonStartStop.text = getString(R.string.contraction_started)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_OK) {
            val contractionNumber = data?.getIntExtra("contraction_number", -1) ?: -1
            if (contractionNumber != -1) {
                val index = contractions.indexOfFirst { it.number == contractionNumber }
                if (index != -1) {
                    contractions.removeAt(index)
                    contractionAdapter.notifyItemRemoved(index)
                    // Update averages and metrics
                    updateMetrics(findViewById(R.id.tv_avg_duration), findViewById(R.id.tv_avg_frequency), findViewById(R.id.tv_contractions_per_hour))
                }
            }
        }
    }

    private fun updateMetrics(tvAvgDuration: TextView, tvAvgFrequency: TextView, tvContractionsPerHour: TextView) {
        // Calculate average duration
        val avgDuration = if (durations.isNotEmpty()) durations.average().toLong() else 0
        tvAvgDuration.text = formatTime(avgDuration)

        // Calculate average frequency
        val frequencies = contractionTimes.zipWithNext { a, b -> (b - a) / 1000 }
        val avgFrequency = if (frequencies.isNotEmpty()) frequencies.average().toLong() else 0
        tvAvgFrequency.text = formatTime(avgFrequency)

        // Calculate contractions per hour
        val currentTime = System.currentTimeMillis()
        val oneHourAgo = currentTime - (60 * 60 * 1000) // 60 minutes in milliseconds
        val contractionsInLastHour = contractionTimes.filter { it > oneHourAgo }.size
        tvContractionsPerHour.text = contractionsInLastHour.toString()
    }

    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format(Locale.getDefault(), "%d:%02d", minutes, remainingSeconds)
    }

    companion object {
        const val REQUEST_CODE_DELETE = 1
    }
}