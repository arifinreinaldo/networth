package com.rei.ynw.data

import android.content.Context
import com.rei.ynw.data.daos.AssetDAO
import com.rei.ynw.data.daos.CashDAO
import com.rei.ynw.data.daos.ExpenseDAO
import com.rei.ynw.data.model.Asset


object Repository {
    private lateinit var db: LocalDatabase
    private lateinit var cashDAO: CashDAO
    private lateinit var assetDAO: AssetDAO
    private lateinit var expenseDAO: ExpenseDAO

    fun init(ctx: Context) {
        db = LocalDatabase.getDatabase(ctx)
        cashDAO = db.cashDAO()
        assetDAO = db.assetDAO()
        expenseDAO = db.expenseDAO()
    }

    suspend fun insertAsset(data: Asset) {
        assetDAO.insert(data)
    }

    suspend fun deleteAsset(data: Asset) {
        assetDAO.deleteData(data)
    }
}