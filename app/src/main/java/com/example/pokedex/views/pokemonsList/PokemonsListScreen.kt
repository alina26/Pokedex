package com.example.pokedex.views.pokemonsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.data.NetworkPokemonRepository
import com.example.data.createPokedexApiService
import com.example.pokedex.R
import com.example.pokedex.views.pokemonsList.models.PokemonListViewState

@Composable
fun PokemonsListScreen(
    onItemClicked: (String) -> Unit,
    modifier: Modifier,
    viewModel: PokemonsListViewModel = PokemonsListViewModel(NetworkPokemonRepository(dataSource = createPokedexApiService())),
) {
    LaunchedEffect(Unit) {
        viewModel.fetch()
    }
    val pokemonListViewState by remember { viewModel.pokemonListViewState }

    when (pokemonListViewState) {
        PokemonListViewState.Loading -> {
            Text(
                text = stringResource(R.string.loading),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        is PokemonListViewState.Content -> {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
            )
            {
                items((pokemonListViewState as PokemonListViewState.Content).items) { item ->
                    PokemonGeneralInfoCard(
                        pokemon = item,
                        modifier = modifier.clickable(enabled = true) { onItemClicked(item.id) })
                }
            }
        }

        is PokemonListViewState.Error -> {
            Text(
                text = stringResource(R.string.error),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }

}