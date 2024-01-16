package com.eduardo.pokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eduardo.pokedex.R
import com.eduardo.pokedex.model.PokemonModel
import com.eduardo.pokedex.ui.screens.widget.PokemonImageCard
import com.eduardo.pokedex.ui.theme.PokedexTheme
import com.eduardo.pokedex.ui.theme.PokemonBlue
import com.eduardo.pokedex.ui.theme.PokemonRed


@Composable
fun HomeScreen(
    pokemonUiState: PokemonUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (pokemonUiState) {
        is PokemonUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is PokemonUiState.Success -> PokemonGridScreen(
            pokemonUiState.pokemon, modifier = modifier.fillMaxWidth()
        )
        is PokemonUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = PokemonRed,
        trackColor = PokemonBlue,
        strokeWidth = 30.dp,
        strokeCap= StrokeCap.Round,
        modifier = Modifier
            .padding(horizontal = 100.dp, vertical = 250.dp),
    )
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = stringResource(R.string.loading)
//    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun PokemonGridScreen(
    pokemons: List<PokemonModel>,
    modifier: Modifier = Modifier,
    ) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = pokemons, key = { pokemon -> pokemon.pokemonId }) { pokemon ->
            PokemonImageCard(
                pokemon,
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(2.5f)
            )
        }
    }
}




@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    PokedexTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    PokedexTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    PokedexTheme {
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}