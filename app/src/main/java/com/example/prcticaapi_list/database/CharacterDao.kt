package com.example.prcticaapi_list.database

import androidx.room.Delete
import androidx.room.Insert
import com.example.prcticaapi_list.model.Character
import retrofit2.http.Query

interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): MutableList<Character>
    @Query("SELECT * FROM CharacterEntity where id = :characterId")
    suspend fun getCharacterById(characterId: Int): MutableList<Character>
    @Insert
    suspend fun addCharacter(character: Character)
    @Delete
    suspend fun deleteCharacter(character: Character)
}