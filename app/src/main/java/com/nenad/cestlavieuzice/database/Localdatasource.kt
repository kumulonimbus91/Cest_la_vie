package com.nenad.cestlavieuzice.database

import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Localdatasource @Inject constructor(private val dao: Dao) {


    suspend fun insertDish(dish: Dish) {
        dao.insertDish(dish)
    }

    fun getDishes(): Flow<List<Dish>> {
        return dao.getDishes()
    }
    suspend fun deleteAllDishes()  {
        dao.deleteAllDishes()
    }
    suspend fun deleteDish(dish:Dish) {
        dao.deleteDish(dish)
    }
    //////

    fun getOrders(): Flow<List<Order>> {
        return dao.getOrders()
    }

    suspend fun insertOrder(order: Order) {
        dao.insertOrder(order)
    }
    suspend fun deleteAllOrders() {
        dao.deleteAllOrders()
    }

    suspend fun deleteOrder(order: Order) {
        dao.deleteOrder(order)
    }







}