package com.nenad.cestlavieuzice.database

import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@ViewModelScoped
class Repository @Inject constructor(val local: Localdatasource) {

       fun getDishes() = local.getDishes()

       suspend fun insertDish(dish: Dish) = local.insertDish(dish)

       suspend fun deleteAllDishes() = local.deleteAllDishes()

       suspend fun deleteDish(dish: Dish) = local.deleteDish(dish)

       fun getOrders() = local.getOrders()

       suspend fun insertOrder(order: Order) = local.insertOrder(order)


       suspend fun deleteAllOrders() = local.deleteAllOrders()



}