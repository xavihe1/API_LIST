package com.example.prcticaapi_list.database

import androidx.room.Delete
import androidx.room.Insert
import com.example.prcticaapi_list.model.Character
import androidx.room.Query
import androidx.room.Dao

interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): MutableList<Character>
    @Query("SELECT * FROM CharacterEntity WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): MutableList<Character>
    @Insert
    suspend fun addCharacter(character: Character)
    @Delete
    suspend fun deleteCharacter(character: Character)
}