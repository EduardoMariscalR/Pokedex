package com.eduardo.pokedex.ui.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eduardo.pokedex.R
import com.eduardo.pokedex.core.GetPokemonTypeCard
import com.eduardo.pokedex.core.getPokemonColor
import com.eduardo.pokedex.core.getPokemonColorTransparent
import com.eduardo.pokedex.core.getPokemonIcon
import com.eduardo.pokedex.ui.home.PokemonCardDataUi

@Composable
fun PokemonCard(
    pokemon: PokemonCardDataUi,
    modifier: Modifier = Modifier
) {
    val id = pokemon.id
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = getPokemonColorTransparent(pokemon.type1.capitalize()),
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = 4.dp, top = 3.dp, bottom = 3.dp, end = 2.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "N°" + String.format("%03d", id),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = pokemon.name.capitalize().replace("-"," "),
                    fontWeight = FontWeight.SemiBold,
                    lineHeight= 1.01.em,
                    fontSize = 32.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    GetPokemonTypeCard(
                        pokemon.type1.capitalize(),
                        Modifier.weight(1f)
                    )
                    if (pokemon.type2 != null) {
                        Spacer(modifier = Modifier.size(2.dp))
                        GetPokemonTypeCard(
                            pokemon.type2.capitalize(),
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                }
            }
            Card(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = getPokemonColor(pokemon.type1.capitalize()),
                ),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = getPokemonIcon(pokemon.type1.capitalize()),
                        contentDescription = stringResource(R.string.pokemon_icon),
                        alpha = 0.5f,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .align(Alignment.Center)
                    )
                    val url =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(url)
                            .crossfade(true).build(),
                        error = painterResource(R.drawable.ic_broken_image),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = stringResource(R.string.pokemon_image),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}



