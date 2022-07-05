package com.example.assignment

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DataDao{
    @Query("SELECT * FROM Data")
    fun getAll(): List<Data>

}