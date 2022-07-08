package com.example.assignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao{
    //Like문을 이용하여 입력한 문구가 들어가는 코인(cointitle을 이용)을 가져온다.
    @Query("SELECT * FROM Data WHERE cointitle LIKE '%' || :query || '%'")
    suspend fun getAll(query: String): List<Data>

    //하나의 데이터를 읽어온다.
    @Query("SELECT * FROM Data WHERE cointitle = :coin")
    suspend fun getItem(coin: String): Data

    @Query("SELECT * FROM Data")
    suspend fun getAllData(): List<Data>

    //모든 데이터를 읽어와서 ROOM에 insert시킨다
    @Insert(onConflict = OnConflictStrategy.REPLACE) //충돌처리 방식
    suspend fun insertAll(vararg dataSet: Data) //가변인자

    @Query("DELETE FROM data")
    suspend fun deleteAll()

}