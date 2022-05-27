package com.nenad.cestlavieuzice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order

@Database(entities = [Dish::class, Order::class], version = 5)
@TypeConverters(Converters::class)
abstract class DishDatabase: RoomDatabase() {

    abstract fun getDao(): Dao


}