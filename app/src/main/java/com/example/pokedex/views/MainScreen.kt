package com.example.pokedex.views

import PokemonDetailsScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.ARGUMENT_ID
import com.example.pokedex.R
import com.example.pokedex.replaceName
import com.example.pokedex.views.pokemonsList.PokemonsListScreen

enum class PokemonScreen(val dest: String, @StringRes val title: Int) {
    ListOfPokemons(dest = "pokemon", title = R.string.feed),
    PokemonDetail(dest = "pokemon/{id}", title = R.string.pokemon_details),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: PokemonScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PokemonScreen.entries.find { it.dest == backStackEntry?.destination?.route }
        ?: PokemonScreen.ListOfPokemons

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
            startDestination = PokemonScreen.ListOfPokemons.dest,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(
                route = PokemonScreen.ListOfPokemons.dest
            ) { backStackEntry ->
                PokemonsListScreen(
                    onItemClicked = {
                        navController.navigate(replaceName(PokemonScreen.PokemonDetail.dest, it))
                    }, modifier = Modifier.fillMaxSize()
                )
            }

            composable(
                route = PokemonScreen.PokemonDetail.dest,
                arguments = listOf(navArgument(ARGUMENT_ID) {
                    defaultValue = "0"; type = NavType.StringType
                })
            ) { backStackEntry ->
                val context = LocalContext.current
                PokemonDetailsScreen(
                    pokemonId = backStackEntry.arguments?.getString(ARGUMENT_ID) ?: "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}