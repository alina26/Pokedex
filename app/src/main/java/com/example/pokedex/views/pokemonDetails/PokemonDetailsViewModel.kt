package com.example.pokedex.views.pokemonDetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.PokemonRepository
import com.example.domain.Result
import com.example.pokedex.toItem
import com.example.pokedex.views.pokemonDetails.models.PokemonDetailsViewState
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonDetailsViewState: MutableState<PokemonDetailsViewState> =
        mutableStateOf(PokemonDetailsViewState.Loading)
    val pokemonDetailsViewState: State<PokemonDetailsViewState> get() = _pokemonDetailsViewState

    fun loadPokemon(name: String) {
        viewModelScope.launch {
            val pokemon = repository.getPokemonByName(name)
            when (pokemon) {
                is Result.Error -> {
                    _pokemonDetailsViewState.value =
                        PokemonDetailsViewState.Error(pokemon.exception.message ?: "error")
                }

                is Result.Success -> {
                    _pokemonDetailsViewState.value =
                        PokemonDetailsViewState.Content(pokemon.data.toItem())
                }
            }
        }
    }
}