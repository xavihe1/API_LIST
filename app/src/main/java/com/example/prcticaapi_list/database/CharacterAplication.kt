package com.example.prcticaapi_list.database

import android.app.Application
import androidx.room.Room

class CharacterApplication: Application() {
    companion object {
        lateinit var database: CharacterDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            CharacterDatabase::class.java,
            "CharacterDatabase").build()
    }
}



