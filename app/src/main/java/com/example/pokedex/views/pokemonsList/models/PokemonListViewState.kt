package com.example.pokedex.views.pokemonsList.models

import com.example.pokedex.models.PokemonItem

sealed class PokemonListViewState {

    object Loading: PokemonListViewState()
    data class Error(val message: String): PokemonListViewState()
    data class Content(val items: List<PokemonItem>): PokemonListViewState()
}