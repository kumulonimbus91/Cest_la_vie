package com.nenad.cestlavieuzice.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "dishes")
data class Dish (@PrimaryKey(autoGenerate = true)
                    var id: Int? = null,
                    val title: String,
                    val ingredients: Array<String>?,
                    val defaultIngredients: Array<String>?,
                    val hasSize: Boolean,
                    val priceSmall: Int?,
                    val priceMedium: Int?,
                    val priceBig:Int?,
                    val urlToImage: String) : Parcelable