package com.example.prcticaapi_list.api

class Repository {
    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
}