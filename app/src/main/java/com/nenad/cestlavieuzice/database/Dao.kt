package com.nenad.cestlavieuzice.database

import androidx.room.*
import androidx.room.Dao
import com.nenad.cestlavieuzice.database.model.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM dishes")
    fun getDishes(): Flow<List<Dish>> //LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict determines if the article already exists
    suspend fun insertDish(dish: Dish): Long

    @Delete
    suspend fun deleteDish(dish: Dish)

    @Query("DELETE FROM dishes")
    suspend fun deleteAllDishes()

}