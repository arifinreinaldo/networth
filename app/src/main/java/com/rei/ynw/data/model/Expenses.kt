package com.rei.ynw.data.model

import androidx.room.Entity

@Entity(tableName = "t_expense")
data class Expenses(
    val id: Int,
    val desc: String,
    val transaction_date: String,
    val value: Double,
    val type: String,
    val created_at: String,
    val updated_at: String
)