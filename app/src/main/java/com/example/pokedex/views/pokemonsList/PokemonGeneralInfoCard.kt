package com.example.pokedex.views.pokemonsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.R
import com.example.pokedex.models.PokemonItem
import com.example.pokedex.ui.theme.BrightYellowGreen
import com.example.pokedex.ui.theme.DarkGreenText
import com.example.pokedex.ui.theme.DarkMintGreen
import com.example.pokedex.ui.theme.DarkYellowGreenText
import com.example.pokedex.ui.theme.GreenBg
import com.example.pokedex.ui.theme.GreenGrass
import com.example.pokedex.ui.theme.LightMintGreenText
import com.example.pokedex.ui.theme.LightYellowGreen
import com.example.pokedex.ui.theme.LightYellowText

val colorsBgToText = listOf(
    Pair(GreenGrass, LightYellowText),
    Pair(DarkMintGreen, LightMintGreenText),
    Pair(BrightYellowGreen, DarkGreenText),
    Pair(LightYellowGreen, DarkYellowGreenText),
)

@Composable
fun PokemonGeneralInfoCard(
    pokemon: PokemonItem,
    modifier: Modifier,
    paint: Boolean = true
) {
    val residue = pokemon.id.toIntOrNull()?.rem(4) ?: 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = colorsBgToText[residue].first)
            .padding(vertical = 15.dp)
    ) {
        Image(
            painter = if (paint) rememberAsyncImagePainter(pokemon.image) else painterResource(R.drawable.ic_launcher_background),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(132.dp)
        )
        Text(
            text = pokemon.name.uppercase(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = colorsBgToText[residue].second,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun Preview() {
    val pokemon = PokemonItem(
        id = "1",
        name = "Pokemon",
        image = ""
    )
    PokemonGeneralInfoCard(
        pokemon = pokemon,
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBg),
        paint = false
    )
}