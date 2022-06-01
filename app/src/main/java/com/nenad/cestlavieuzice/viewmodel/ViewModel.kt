package com.nenad.cestlavieuzice.viewmodel

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.nenad.cestlavieuzice.database.Localdatasource
import com.nenad.cestlavieuzice.database.Repository
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


    val dishes: LiveData<List<Dish>> = repository.getDishes().asLiveData()

    val orders: LiveData<List<Order>> = repository.getOrders().asLiveData()

    fun insertDish(dish: Dish) {
        viewModelScope.launch {
            repository.insertDish(dish)
        }
    }

    fun deleteAllDishes() {
        viewModelScope.launch {
            repository.deleteAllDishes()
        }
    }

    fun deleteDish(dish: Dish) {
        viewModelScope.launch {
            repository.deleteDish(dish)
        }
    }

    fun getDishes() {
        viewModelScope.launch {
            repository.getDishes()
        }
    }

    val dishTotal = Transformations.map(dishes) { dishes ->
        var priceTotal = 0
        for (dish in dishes) {
            priceTotal += dish.priceSmall!!

        }
        Dish(null, "", null, null, true, true, 1, priceTotal, priceTotal, "")
    }


    fun insertOrder(order: Order) {
        viewModelScope.launch {
            repository.insertOrder(order)
        }
    }

    fun deleteAllOrders() {
        viewModelScope.launch {
            repository.deleteAllOrders()
        }


    }
    fun deleteOrder(order: Order) {
        viewModelScope.launch {
            repository.deleteOrder(order)
        }
    }


}