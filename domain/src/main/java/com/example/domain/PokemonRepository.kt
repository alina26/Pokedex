package com.example.domain

interface PokemonRepository {
    suspend fun getPokemonList(): Result<List<PokemonEntity>>
    suspend fun getPokemonByName(name: String): Result<PokemonEntity>
}