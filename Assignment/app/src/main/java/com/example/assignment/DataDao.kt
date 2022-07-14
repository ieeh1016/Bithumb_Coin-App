package com.example.assignment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    //Like문을 이용하여 입력한 문구가 들어가는 코인(cointitle을 이용)을 가져온다.
    @Query("SELECT * FROM Data WHERE date = (SELECT max(date) FROM Data) and cointitle LIKE '%' || :query || '%'")
    suspend fun getAll(query: String): List<Data>

    //코인이름에 해당하는 행을 내림차순으로 가져온다.
    @Query("SELECT * FROM Data WHERE cointitle = :query ORDER BY date DESC")
    suspend fun getQueryAll(query: String): List<Data>

    //해당되는 코인이름 모든 data가져와 List에 저장한다.
    @Query("SELECT * FROM Data WHERE cointitle = :coin")
    suspend fun getItem(coin: String): List<Data>

    //모든 행을 INSERT 한다.
    @Insert
    suspend fun insertAll(vararg dataSet: Data) //가변인자

}
