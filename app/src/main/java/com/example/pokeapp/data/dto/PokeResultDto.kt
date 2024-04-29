package com.example.pokeapp.data.dto

data class PokeResultDto(
    val height: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val order: Int? = null,
    val sprites: SpritesDto? = null,
    val stats: List<StatDto>? = null,
    val types: List<TypeDto>? = null,
    val weight: Int? = null
)