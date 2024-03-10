package com.example.pokedex.views.pokemonDetails.models

import com.example.pokedex.models.PokemonItem

sealed class PokemonDetailsViewState {

    object Loading : PokemonDetailsViewState()

    data class Content(val pokemon: PokemonItem) : PokemonDetailsViewState()

    data class Error(val message: String) : PokemonDetailsViewState()
}