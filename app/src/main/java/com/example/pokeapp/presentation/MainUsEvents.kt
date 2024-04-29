package com.example.pokeapp.presentation

sealed class MainUsEvents {
    data class onSearchWordChange(val newWord: String) : MainUsEvents()
    object OnSearchClick: MainUsEvents()
}