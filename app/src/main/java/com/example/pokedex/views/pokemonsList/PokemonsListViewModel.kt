package com.example.pokedex.views.pokemonsList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.PokemonRepository
import com.example.domain.Result
import com.example.pokedex.toItem
import com.example.pokedex.views.pokemonsList.models.PokemonListViewState
import kotlinx.coroutines.launch

class PokemonsListViewModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonListViewState: MutableState<PokemonListViewState> =
        mutableStateOf(PokemonListViewState.Loading)
    val pokemonListViewState: State<PokemonListViewState> get() = _pokemonListViewState

    fun fetch() {
        viewModelScope.launch {
            val result = repository.getPokemonList()
            when (result) {
                is Result.Success -> {
                    val pokemonItemList = result.data.map { it.toItem() }
                    _pokemonListViewState.value = PokemonListViewState.Content(pokemonItemList)
                }

                is Result.Error -> {
                    _pokemonListViewState.value =
                        PokemonListViewState.Error(result.exception.message ?: "Error")
                }
            }
        }
    }
}