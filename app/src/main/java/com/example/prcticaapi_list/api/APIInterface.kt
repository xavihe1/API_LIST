package com.example.prcticaapi_list.api

import com.example.prcticaapi_list.model.Ability
import com.example.prcticaapi_list.model.Data
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {

    @GET("characters")
    suspend fun getCharacters(): Response<List<Data>>

    @GET("characters/{id}")
    suspend fun getSpecificCharacter(@Path("id") idCharacter: String): Response<Data>

    @GET("ability")
    suspend fun getAbility(@Path("id") idAbility: String): Response<Ability>

    companion object {
        val BASE_URL = "https://valorant-api.com/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}