package com.example.pokeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.domain.repository.PokeRepository
import com.example.pokeapp.util.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    private val searchJob: Job? = null

    fun onEvent(mainUsEvents: MainUsEvents) {
        when (mainUsEvents) {
            MainUsEvents.OnSearchClick -> {
                loadWordResult()
            }

            is MainUsEvents.onSearchWordChange -> {
                _mainState.update {
                    it.copy(
                        searchString = mainUsEvents.newWord.lowercase()
                    )
                }
            }
        }
    }


    private fun loadWordResult() {
        viewModelScope.launch {
            pokeRepository.getPokeResult(
                mainState.value.searchString
            ).collect { result ->
                when (result) {
                    is Result.Error -> Unit

                    is Result.Loading -> {
                        _mainState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Result.Success -> {
                        result.data?.let { pokeItem ->
                            _mainState.update {
                                it.copy(
                                    pokeItem = pokeItem
                                )
                            }
                        }

                    }

                }
            }
        }
    }

}