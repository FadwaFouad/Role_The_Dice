package com.example.rolethedice.data.Quote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * from results")
    fun getAll (): List<Result>

    @Insert
    suspend fun insert(result: Result)

    @Insert
    suspend fun insertResults(results: List<Result>)

    @Query("DELETE  from results")
    suspend fun deleteAll ()
}
