package com.example.contractiontimer.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contraction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contractionDao(): ContractionDao
}