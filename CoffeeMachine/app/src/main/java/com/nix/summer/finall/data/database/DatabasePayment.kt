package com.nix.summer.finall.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class DatabasePayment (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "column_amount") val amount: Double,
    @ColumnInfo(name = "column_currency") val currency: String
)
