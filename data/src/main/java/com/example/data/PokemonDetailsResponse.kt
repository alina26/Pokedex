package com.example.data

data class PokemonDetailsResponse(
    val id: String,
    val name: String,
    val abilities: List<PokemonAbilityData>
)

data class PokemonAbilityData(
    val ability: PokemonAbilityDetailsData,
    val is_hidden: Boolean,
    val slot: Int,
)

data class PokemonAbilityDetailsData(
    val name: String,
    val url: String
)
