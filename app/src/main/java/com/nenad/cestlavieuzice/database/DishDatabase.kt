package com.nenad.cestlavieuzice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nenad.cestlavieuzice.database.model.Dish

@Database(entities = [Dish::class], version = 1)
abstract class DishDatabase: RoomDatabase() {

    abstract fun getDao(): Dao
}