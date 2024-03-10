import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.data.NetworkPokemonRepository
import com.example.data.createPokedexApiService
import com.example.pokedex.R
import com.example.pokedex.views.pokemonDetails.PokemonDetailsViewModel
import com.example.pokedex.views.pokemonDetails.models.PokemonDetailsViewState

@Composable
fun PokemonDetailsScreen(
    pokemonId: String,
    viewModel: PokemonDetailsViewModel = PokemonDetailsViewModel(NetworkPokemonRepository(dataSource = createPokedexApiService())),
    modifier: Modifier,
) {
    LaunchedEffect(Unit) {
        viewModel.loadPokemon(pokemonId)
    }
    val pokemonDetailsViewState by remember { viewModel.pokemonDetailsViewState }

    when (pokemonDetailsViewState) {
        is PokemonDetailsViewState.Content -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val pokemon = (pokemonDetailsViewState as PokemonDetailsViewState.Content).pokemon
                Image(
                    painter = rememberAsyncImagePainter(pokemon.image),
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = pokemon.name,
                    fontSize = 30.sp
                )
                pokemon.abilities.forEach { ability ->
                    Text(
                        text = ability,
                        fontSize = 20.sp
                    )
                }
            }
        }

        is PokemonDetailsViewState.Error -> {
            Text(
                text = stringResource(R.string.loading),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        PokemonDetailsViewState.Loading -> {
            Text(
                text = stringResource(R.string.error),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "pokemon",
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "pokemon",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Text(
            text = "ability",
            fontSize = 20.sp
        )
    }
}