package com.example.pokedex.models

data class PokemonItem(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val abilities: List<String> = listOf(),
)
