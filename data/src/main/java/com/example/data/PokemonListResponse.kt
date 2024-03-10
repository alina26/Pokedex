package com.example.data

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonPartialResponse>,
)

data class PokemonPartialResponse(
    val name: String,
    val url: String
)
