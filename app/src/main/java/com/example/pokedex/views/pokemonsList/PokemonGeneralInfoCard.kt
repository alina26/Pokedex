package com.example.pokedex.views.pokemonsList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.models.PokemonItem

@Composable
fun PokemonGeneralInfoCard(
    pokemon: PokemonItem,
    modifier: Modifier,
){
    Text(text = pokemon.name, modifier.padding(8.dp).size(20.dp))
}