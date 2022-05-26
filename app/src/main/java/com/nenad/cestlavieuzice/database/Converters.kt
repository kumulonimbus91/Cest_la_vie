package com.nenad.cestlavieuzice.database

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


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














//    @TypeConverter
//    fun fromString(value: String?): ArrayList<String> {
//        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: ArrayList<String?>?): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
}