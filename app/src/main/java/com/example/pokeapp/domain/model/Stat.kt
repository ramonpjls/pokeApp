package com.example.pokeapp.domain.model

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: List<StatList>
)
