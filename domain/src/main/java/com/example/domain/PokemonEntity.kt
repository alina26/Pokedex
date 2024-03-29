package com.example.domain

data class PokemonEntity(
    val id: String,
    val name: String,
    val previewUrl: String,
    val abilities: List<String> = emptyList()
)
