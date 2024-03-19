package com.eduardo.pokedex.ui.home.widget

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eduardo.pokedex.ui.theme.PokedexTheme
import com.eduardo.pokedex.ui.theme.PokemonBlue
import com.eduardo.pokedex.ui.theme.PokemonRed

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = PokemonRed,
        trackColor = PokemonBlue,
        strokeWidth = 30.dp,
        strokeCap= StrokeCap.Round,
        modifier = modifier,
    )
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = stringResource(R.string.loading)
//    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    PokedexTheme {
        LoadingScreen()
    }
}