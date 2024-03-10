package com.example.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

fun createPokedexApiService(): PokedexApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(PokedexApiService::class.java)
}

interface PokedexApiService {
    /**
     * See for details: https://pokeapi.co/api/v2/pokemon
     */
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ) : PokemonListResponse

    /**
     * See for details: https://pokeapi.co/api/v2/pokemon/bulbasaur
     */
    @GET("pokemon/{name}")
    suspend fun fetchPokemonDetails(@Path("name") name: String): PokemonDetailsResponse
}
