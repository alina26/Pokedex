package com.example.pokedex.models

import androidx.annotation.StringRes
import com.example.pokedex.R

sealed class NavigationDestination(val dest: String, @StringRes val title: Int) {
    object ListOfPokemons : NavigationDestination(dest = "pokemon", title = R.string.feed)
    object PokemonDetail :
        NavigationDestination(dest = "pokemon/{id}", title = R.string.pokemon_details)

    companion object {
        private val allDestinations = listOf(ListOfPokemons, PokemonDetail)
        fun findByDest(dest: String): NavigationDestination =
            allDestinations.find { it.dest == dest } ?: ListOfPokemons
    }
}