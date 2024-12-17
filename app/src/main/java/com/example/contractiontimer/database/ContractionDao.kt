package com.example.contractiontimer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContractionDao {
    @Query("SELECT * FROM contractions ORDER BY id DESC")
    fun getAll(): List<Contraction>

    @Insert
    fun insert(contraction: Contraction)

    @Delete
    fun delete(contraction: Contraction)
}