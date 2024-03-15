package com.example.pokedex.di

import androidx.lifecycle.ViewModel
import com.example.pokedex.di.viewModels.ViewModelKey
import com.example.pokedex.views.pokemonDetails.PokemonDetailsViewModel
import com.example.pokedex.views.pokemonsList.PokemonsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokemonsListViewModel::class)
    abstract fun pokemonsListViewModel(viewModel: PokemonsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailsViewModel::class)
    abstract fun pokemonDetailsViewModel(viewModel: PokemonDetailsViewModel): ViewModel
}
