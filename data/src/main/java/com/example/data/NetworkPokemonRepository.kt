package com.example.data

import com.example.domain.PokemonEntity
import com.example.domain.PokemonRepository
import com.example.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkPokemonRepository(private val dataSource: PokedexApiService) : PokemonRepository {

    override suspend fun getPokemonList(): Result<List<PokemonEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val pokemonNames = dataSource.fetchPokemonList().results.map { it.name }
                val pokemonListWithDetails = pokemonNames.map { name ->
                    dataSource.fetchPokemonDetails(name).toEntity()
                }
                Result.Success(pokemonListWithDetails)
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

    override suspend fun getPokemonByName(name: String): Result<PokemonEntity> =
        withContext(Dispatchers.IO) {
            try {
                val pokemon = dataSource.fetchPokemonDetails(name).toEntity()
                Result.Success(pokemon)
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}