package com.example.prcticaapi_list.database

import androidx.room.TypeConverter
import com.example.prcticaapi_list.model.Ability

class Converters {
    @TypeConverter
    fun fromListToString(list: List<Ability>): String {
        return list.joinToString(",")
    }
    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split(",")
    }
}