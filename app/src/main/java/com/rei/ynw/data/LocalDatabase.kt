package com.rei.ynw.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rei.ynw.data.daos.AssetDAO
import com.rei.ynw.data.daos.CashDAO
import com.rei.ynw.data.daos.ExpenseDAO

@Database(
    entities = [CashDAO::class, ExpenseDAO::class, AssetDAO::class],
    version = 1,
    exportSchema = true
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun cashDAO(): CashDAO
    abstract fun expenseDAO(): ExpenseDAO
    abstract fun assetDAO(): AssetDAO

    companion object {
        @Volatile
        var INSTANCE: LocalDatabase? = null

        fun getDatabase(ctx: Context): LocalDatabase {
            val tempInstance =
                INSTANCE
            tempInstance?.let {
                return it
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    LocalDatabase::class.java,
                    "mai_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}