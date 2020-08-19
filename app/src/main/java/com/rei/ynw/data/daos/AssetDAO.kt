package com.rei.ynw.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rei.ynw.data.model.Asset

@Dao
interface AssetDAO {
    @Query("SELECT * FROM t_asset")
    fun getAssetLive(): LiveData<List<Asset>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Asset)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<Asset>)

    @Query("DELETE FROM t_asset")
    suspend fun delete()

    @Delete
    suspend fun deleteData(data: Asset)
}