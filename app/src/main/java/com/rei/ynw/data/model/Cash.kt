package com.rei.ynw.data.model

import androidx.room.Entity

@Entity(tableName = "t_cash")
data class Cash(
    val id: Int,
    val desc: String,
    val source: String,
    val transaction_date: String,
    val value: Double,
    val type: String,
    val ref_expense: Int = -1,
    val ref_asset: Int = -1,
    val created_at: String,
    val updated_at: String
)