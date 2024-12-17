//package com.example.contractiontimer
//
//data class Contraction(
//    val number: Int,        // Contraction number
//    val duration: String,   // Duration of the contraction
//    val frequency: String,  // Frequency between contractions
//    val startTime: String,  // Start time of the contraction
//    val endTime: String     // End time of the contraction
//)

package com.example.contractiontimer.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contractions")
data class Contraction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: Int,        // Contraction number
    val duration: String,   // Duration of the contraction
    val frequency: String,  // Frequency between contractions
    val startTime: String,  // Start time of the contraction
    val endTime: String     // End time of the contraction
)