package com.example.prcticaapi_list.database

import androidx.room.Database
import androidx.room.TypeConverters
import com.example.prcticaapi_list.model.Character

@Database(entities = arrayOf(Character::class), version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase {
    abstract fun characterDao(): CharacterDao
}