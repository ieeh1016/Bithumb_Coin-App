package com.example.assignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao{
    @Query("SELECT * FROM Data WHERE cointitle LIKE '%' || :query || '%'")
    suspend fun getAll(query: String): List<Data>

    @Query("SELECT * FROM Data WHERE cointitle = :coin")
    suspend fun getItem(coin: String): Data

    @Query("SELECT * FROM Data")
    suspend fun getAllData(): List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //충돌처리 방식
    suspend fun insertAll(vararg dataSet: Data) //가변인자

    @Query("DELETE FROM data")
    suspend fun deleteAll()

}