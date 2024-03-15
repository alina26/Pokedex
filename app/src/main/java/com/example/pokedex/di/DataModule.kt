package com.example.pokedex.di

import com.example.data.NetworkPokemonRepository
import com.example.data.PokedexApiService
import com.example.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun providePokemonRepository(dataSource: PokedexApiService): PokemonRepository {
        return NetworkPokemonRepository(dataSource = dataSource)
    }

    @Provides
    fun providePokedexApiService(): PokedexApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokedexApiService::class.java)
    }
}