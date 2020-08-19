package com.rei.ynw.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rei.ynw.data.model.Expenses

@Dao
interface ExpenseDAO {
    @Query("SELECT * FROM t_expense")
    fun getExpensesLive(): LiveData<List<Expenses>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Expenses)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<Expenses>)

    @Query("DELETE FROM t_expense")
    suspend fun delete()

    @Delete
    suspend fun deleteData(data: Expenses)
}