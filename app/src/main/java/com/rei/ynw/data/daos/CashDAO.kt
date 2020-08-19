package com.rei.ynw.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rei.ynw.data.model.Cash

@Dao
interface CashDAO {
    @Query("SELECT * FROM t_cash")
    fun getCashLive(): LiveData<List<Cash>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Cash)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<Cash>)

    @Query("DELETE FROM t_cash")
    suspend fun delete()

    @Delete
    suspend fun deleteData(data: Cash)
}