package com.nix.summer.finall.data.database

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson

object Database {

    private const val DB_NAME = "coffee_database"

    fun provideDao(context: Context): PaymentDao = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    )   .build()
        .paymentDao()
}