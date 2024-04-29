package com.example.pokeapp.data.api

import com.example.pokeapp.data.dto.PokeResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {


    @GET("{word}")
    suspend fun getApiResults(
        @Path("word") word: String
    ): PokeResultDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"
    }


}