package com.example.prcticaapi_list.api

import com.example.prcticaapi_list.model.Character
import com.example.prcticaapi_list.model.CharacterApplication

class Repository {
    val apiInterface = APIInterface.create()
    val daoInterfase = CharacterApplication.database.characterDao()
    suspend fun getAllCharacters() = apiInterface.getCharacters("")

    //Database functions
    suspend fun saveAsFavorite(character: Character) = daoInterfase.addCharacter(character)
    suspend fun deleteFavorite(character: Character) = daoInterfase.deleteCharacter(character)
    suspend fun isFavorite(character: Character) = daoInterfase.getCharacterById(character.id).isNotEmpty()
    suspend fun getFavorites() = daoInterfase.getAllCharacters()
}