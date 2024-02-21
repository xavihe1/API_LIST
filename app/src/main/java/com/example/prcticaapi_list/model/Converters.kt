package com.example.prcticaapi_list.model

import androidx.room.TypeConverter

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