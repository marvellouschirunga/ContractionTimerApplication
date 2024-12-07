package com.example.contractiontimer

data class Contraction(
    val number: Int,        // Contraction number
    val duration: String,   // Duration of the contraction
    val frequency: String,  // Frequency between contractions
    val startTime: String,  // Start time of the contraction
    val endTime: String     // End time of the contraction
)