package com.example.pokedex

import com.example.domain.PokemonEntity
import com.example.pokedex.models.PokemonItem

fun replaceName(originalString: String, replacement: String): String {
    return originalString.replace("{${ARGUMENT_ID}}", replacement)
}

fun PokemonEntity.toItem() = PokemonItem(id, name, previewUrl, abilities)