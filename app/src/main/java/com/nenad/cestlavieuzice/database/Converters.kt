package com.nenad.cestlavieuzice.database

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.nenad.cestlavieuzice.database.model.Dish
import java.util.*
import kotlin.collections.ArrayList


class Converters {

    @TypeConverter
    fun toList(strings: String?): ArrayList<String> {
        val list = ArrayList<String>()
        val array = strings?.split(",")
        if (array != null) {
            for (s in array) {
                list.add(s)
            }
        }
        return list
    }

    @TypeConverter
    fun toString(strings: ArrayList<String>?): String {
        var result = ""
        strings?.forEachIndexed { index, element ->
            result += element
            if(index != (strings.size.minus(1))){
                result += ","
            }
        }
        return result
    }


    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<Dish?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Dish?>?>() {}.type
        return gson.fromJson<List<Dish?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Dish?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }



}