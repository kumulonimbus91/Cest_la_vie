package com.nenad.cestlavieuzice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nenad.cestlavieuzice.database.model.Order

@Database(entities = [Order::class], version = 1)
abstract class OrdersDatabase: RoomDatabase() {

    abstract fun getDao(): Dao
}