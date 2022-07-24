package com.nix.summer.finall.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PaymentDao {

    @Insert
    fun add(databasePayment: DatabasePayment)

    @Delete
    fun delete(databasePayment: DatabasePayment)

    @Query("SELECT * FROM payments ORDER BY id DESC LIMIT 1")
    fun getPaymentById(): DatabasePayment?
}
