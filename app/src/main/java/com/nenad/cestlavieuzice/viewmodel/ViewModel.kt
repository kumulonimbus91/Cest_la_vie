package com.nenad.cestlavieuzice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nenad.cestlavieuzice.database.Localdatasource
import com.nenad.cestlavieuzice.database.Repository
import com.nenad.cestlavieuzice.database.model.Dish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

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




}