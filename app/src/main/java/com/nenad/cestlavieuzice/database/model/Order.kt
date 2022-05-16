package com.nenad.cestlavieuzice.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var dishes: MutableList<Dish>,
    var price: Int?) : Parcelable
