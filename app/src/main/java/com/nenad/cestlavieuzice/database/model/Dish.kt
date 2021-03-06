package com.nenad.cestlavieuzice.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val ingredients: ArrayList<String>?,
    val defaultIngredients: String?,
    val hasSize: Boolean,
    val hasIngredients: Boolean,
    var numOfItems: Int,
    var priceSmall: Int?,
    var priceBig: Int?,
    val urlToImage: String
) : Parcelable