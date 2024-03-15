package com.example.pokedex.views

import PokemonDetailsScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.ARGUMENT_ID
import com.example.pokedex.di.MainScreenComponent
import com.example.pokedex.models.NavigationDestination
import com.example.pokedex.replaceName
import com.example.pokedex.ui.theme.GreenBg
import com.example.pokedex.views.pokemonDetails.PokemonDetailsViewModel
import com.example.pokedex.views.pokemonsList.PokemonsListScreen
import com.example.pokedex.views.pokemonsList.PokemonsListViewModel


@Composable
fun MainScreen(
    component: MainScreenComponent,
    navController: NavHostController = rememberNavController()
) {
    val pokemonsListViewModel: PokemonsListViewModel =
        viewModel(factory = component.viewModelFactory())
    val pokemonDetailsViewModel: PokemonDetailsViewModel =
        viewModel(factory = component.viewModelFactory())

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationDestination.findByDest(
        backStackEntry?.destination?.route ?: NavigationDestination.ListOfPokemons.dest
    )

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.ListOfPokemons.dest,
            modifier = Modifier.padding(innerPadding).background(GreenBg),
        ) {
            composable(
                route = NavigationDestination.ListOfPokemons.dest
            ) { backStackEntry ->
                PokemonsListScreen(
                    onItemClicked = {
                        navController.navigate(
                            replaceName(
                                NavigationDestination.PokemonDetail.dest,
                                it
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    viewModel = pokemonsListViewModel
                )
            }

            composable(
                route = NavigationDestination.PokemonDetail.dest,
                arguments = listOf(navArgument(ARGUMENT_ID) {
                    defaultValue = "0"; type = NavType.StringType
                })
            ) { backStackEntry ->
                val context = LocalContext.current
                PokemonDetailsScreen(
                    pokemonId = backStackEntry.arguments?.getString(ARGUMENT_ID) ?: "",
                    modifier = Modifier.fillMaxSize(),
                    viewModel = pokemonDetailsViewModel
                )
            }
        }
    }
}