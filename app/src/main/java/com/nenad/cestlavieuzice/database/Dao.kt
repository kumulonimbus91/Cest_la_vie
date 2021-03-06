package com.nenad.cestlavieuzice.database

import androidx.room.*
import androidx.room.Dao
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import kotlinx.coroutines.flow.Flow


@Dao
@TypeConverters(Converters::class)
interface Dao {

    @Query("SELECT * FROM dishes")
    fun getDishes(): Flow<List<Dish>> //LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict determines if the article already exists
    suspend fun insertDish(dish: Dish): Long

    @Delete
    suspend fun deleteDish(dish: Dish)

    @Query("DELETE FROM dishes")
    suspend fun deleteAllDishes()

   ///////////////////////////////////////////////////

    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getOrders(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)




    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()


    @Delete
    suspend fun deleteOrder(order:Order)

}