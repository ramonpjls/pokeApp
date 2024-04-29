package com.example.pokeapp.presentation

import com.example.pokeapp.domain.model.PokeItem

data class MainState(
    val isLoading: Boolean = false,
    val searchString: String = "",

    val pokeItem: PokeItem? = null
)
