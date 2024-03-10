package com.example.data

import com.example.domain.PokemonEntity

fun PokemonDetailsResponse.toEntity() =
    PokemonEntity(
        id = id,
        name = name,
        previewUrl = generateUrlFromId(id),
        abilities = abilities.map { it.ability.name })

fun generateUrlFromId(id: String): String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"