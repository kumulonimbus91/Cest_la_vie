package com.nenad.cestlavieuzice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nenad.cestlavieuzice.database.model.Dish

@Database(entities = [Dish::class], version = 1)
@TypeConverters(Converters::class)
abstract class DishDatabase: RoomDatabase() {

    abstract fun getDao(): Dao
}