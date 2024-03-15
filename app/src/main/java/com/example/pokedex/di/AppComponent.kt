package com.example.pokedex.di

import com.example.pokedex.di.viewModels.DaggerViewModelFactory
import dagger.Component
import javax.inject.Scope

@Component(modules = [AppModule::class, DataModule::class])
@PokemonScreenScope
interface MainScreenComponent {
    fun viewModelFactory(): DaggerViewModelFactory
}

@Scope
annotation class PokemonScreenScope