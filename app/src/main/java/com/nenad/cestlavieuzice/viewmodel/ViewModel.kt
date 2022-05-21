package com.nenad.cestlavieuzice.viewmodel

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.*
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

    //val readArt: LiveData<List<Article>> = repository.local.readArticles().asLiveData()

    val dishes: LiveData<List<Dish>> = repository.getDishes().asLiveData()

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



//    val foodTotal = Transformations.map(foods) { foods ->
//        var gramsTotal = 0.0
//        var carbsTotal = 0.0
//        var proteinsTotal = 0.0
//        var fatsTotal = 0.0
//        var kcalTotal = 0.0
//
//        for (food in foods) {
//            gramsTotal += food.grams
//            carbsTotal += food.carbs
//            proteinsTotal += food.proteins
//            fatsTotal += food.fats
//            kcalTotal += food.kcal
//        }
//
//        FoodModel(
//            name = "",
//            grams = gramsTotal,
//            carbs = carbsTotal,
//            proteins = proteinsTotal,
//            fats = fatsTotal,
//            kcal = kcalTotal.toInt(),
//            date = ""
//
//        )
//
//    }




}