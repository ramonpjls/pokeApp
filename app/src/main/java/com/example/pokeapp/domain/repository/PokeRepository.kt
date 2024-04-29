package com.example.pokeapp.domain.repository

import com.example.pokeapp.domain.model.PokeItem
import com.example.pokeapp.util.Result
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    suspend fun getPokeResult(
        word: String
    ): Flow<Result<PokeItem>>
}