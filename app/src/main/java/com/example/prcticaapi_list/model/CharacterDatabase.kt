package com.example.prcticaapi_list.model

import androidx.room.Database
import androidx.room.TypeConverters

@Database(entities = arrayOf(Character::class), version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase {
    abstract fun characterDao(): CharacterDao
}