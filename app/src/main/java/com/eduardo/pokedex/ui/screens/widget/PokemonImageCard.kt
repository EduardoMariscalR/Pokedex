package com.eduardo.pokedex.ui.screens.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eduardo.pokedex.R
import com.eduardo.pokedex.core.PokemonType
import com.eduardo.pokedex.model.PokemonModel
import com.eduardo.pokedex.ui.theme.Bug
import com.eduardo.pokedex.ui.theme.BugTransparent
import com.eduardo.pokedex.ui.theme.Dark
import com.eduardo.pokedex.ui.theme.DarkTransparent
import com.eduardo.pokedex.ui.theme.Dragon
import com.eduardo.pokedex.ui.theme.DragonTransparent
import com.eduardo.pokedex.ui.theme.Electric
import com.eduardo.pokedex.ui.theme.ElectricTransparent
import com.eduardo.pokedex.ui.theme.Fairy
import com.eduardo.pokedex.ui.theme.FairyTransparent
import com.eduardo.pokedex.ui.theme.Fighting
import com.eduardo.pokedex.ui.theme.FightingTransparent
import com.eduardo.pokedex.ui.theme.Fire
import com.eduardo.pokedex.ui.theme.FireTransparent
import com.eduardo.pokedex.ui.theme.Flying
import com.eduardo.pokedex.ui.theme.FlyingTransparent
import com.eduardo.pokedex.ui.theme.Ghost
import com.eduardo.pokedex.ui.theme.GhostTransparent
import com.eduardo.pokedex.ui.theme.Grass
import com.eduardo.pokedex.ui.theme.GrassTransparent
import com.eduardo.pokedex.ui.theme.Ground
import com.eduardo.pokedex.ui.theme.GroundTransparent
import com.eduardo.pokedex.ui.theme.Ice
import com.eduardo.pokedex.ui.theme.IceTransparent
import com.eduardo.pokedex.ui.theme.Normal
import com.eduardo.pokedex.ui.theme.NormalTransparent
import com.eduardo.pokedex.ui.theme.Poison
import com.eduardo.pokedex.ui.theme.PoisonTransparent
import com.eduardo.pokedex.ui.theme.Psychic
import com.eduardo.pokedex.ui.theme.PsychicTransparent
import com.eduardo.pokedex.ui.theme.Rock
import com.eduardo.pokedex.ui.theme.RockTransparent
import com.eduardo.pokedex.ui.theme.Steel
import com.eduardo.pokedex.ui.theme.SteelTransparent
import com.eduardo.pokedex.ui.theme.Water
import com.eduardo.pokedex.ui.theme.WaterTransparent

@Composable
fun PokemonImageCard(pokemon: PokemonModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = getPokemonColorTransparent(pokemon.pokemonTypes[0].type.name.capitalize()),
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "N°" + String.format("%03d", pokemon.pokemonId),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = pokemon.name.capitalize(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row {
                    GetPokemonTypeCard(
                        pokemon.pokemonTypes[0].type.name.capitalize(),
                    )

                    if (pokemon.pokemonTypes.size > 1) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        GetPokemonTypeCard(
                            pokemon.pokemonTypes[1].type.name.capitalize(),
                        )
                    }
                }
            }
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = getPokemonColor(pokemon.pokemonTypes[0].type.name.capitalize()),
                ),
            ) {
                Box(modifier = Modifier.fillMaxHeight()) {
                    Image(
                        painter = getPokemonIcon(pokemon.pokemonTypes[0].type.name.capitalize()),
                        contentDescription = stringResource(R.string.pokemon_icon),
                        alpha = 0.5f,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(4.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data((pokemon.images.imageRvCard))
                            .crossfade(true).build(),
                        error = painterResource(R.drawable.ic_broken_image),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = stringResource(R.string.pokemon_image),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun GetPokemonTypeCard(pokemonType: String) {
    Card(
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = getPokemonColor(pokemonType),
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp, 3.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    painter = getPokemonIcon(pokemonType),
                    contentDescription = stringResource(R.string.pokemon_icon),
                    tint = getPokemonColor(pokemonType),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                )
            }
            Text(
                text = pokemonType,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(5.dp),
                color = getPokemonTextColor(pokemonType)
            )
        }
    }
}

@Composable
fun getPokemonTextColor(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonType.Normal.toString() -> Color.Black
        PokemonType.Fighting.toString() -> Color.White
        PokemonType.Flying.toString() -> Color.Black
        PokemonType.Poison.toString() -> Color.Black
        PokemonType.Ground.toString() -> Color.Black
        PokemonType.Rock.toString() -> Color.Black
        PokemonType.Bug.toString() -> Color.Black
        PokemonType.Ghost.toString() -> Color.White
        PokemonType.Steel.toString() -> Color.Black
        PokemonType.Fire.toString() -> Color.Black
        PokemonType.Water.toString() -> Color.Black
        PokemonType.Grass.toString() -> Color.Black
        PokemonType.Electric.toString() -> Color.Black
        PokemonType.Psychic.toString() -> Color.Black
        PokemonType.Ice.toString() -> Color.Black
        PokemonType.Dragon.toString() -> Color.White
        PokemonType.Dark.toString() -> Color.White
        PokemonType.Fairy.toString() -> Color.Black
        else -> {
            Color.Black
        }
    }
}

@Composable
fun getPokemonColor(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonType.Normal.toString() -> Normal
        PokemonType.Fighting.toString() -> Fighting
        PokemonType.Flying.toString() -> Flying
        PokemonType.Poison.toString() -> Poison
        PokemonType.Ground.toString() -> Ground
        PokemonType.Rock.toString() -> Rock
        PokemonType.Bug.toString() -> Bug
        PokemonType.Ghost.toString() -> Ghost
        PokemonType.Steel.toString() -> Steel
        PokemonType.Fire.toString() -> Fire
        PokemonType.Water.toString() -> Water
        PokemonType.Grass.toString() -> Grass
        PokemonType.Electric.toString() -> Electric
        PokemonType.Psychic.toString() -> Psychic
        PokemonType.Ice.toString() -> Ice
        PokemonType.Dragon.toString() -> Dragon
        PokemonType.Dark.toString() -> Dark
        PokemonType.Fairy.toString() -> Fairy
        else -> {
            Color.Black
        }
    }
}

@Composable
fun getPokemonColorTransparent(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonType.Normal.toString() -> NormalTransparent
        PokemonType.Fighting.toString() -> FightingTransparent
        PokemonType.Flying.toString() -> FlyingTransparent
        PokemonType.Poison.toString() -> PoisonTransparent
        PokemonType.Ground.toString() -> GroundTransparent
        PokemonType.Rock.toString() -> RockTransparent
        PokemonType.Bug.toString() -> BugTransparent
        PokemonType.Ghost.toString() -> GhostTransparent
        PokemonType.Steel.toString() -> SteelTransparent
        PokemonType.Fire.toString() -> FireTransparent
        PokemonType.Water.toString() -> WaterTransparent
        PokemonType.Grass.toString() -> GrassTransparent
        PokemonType.Electric.toString() -> ElectricTransparent
        PokemonType.Psychic.toString() -> PsychicTransparent
        PokemonType.Ice.toString() -> IceTransparent
        PokemonType.Dragon.toString() -> DragonTransparent
        PokemonType.Dark.toString() -> DarkTransparent
        PokemonType.Fairy.toString() -> FairyTransparent
        else -> {
            Color.Black
        }
    }
}

@Composable
fun getPokemonIcon(pokemonType: String): Painter {
    return when (pokemonType) {
        PokemonType.Normal.toString() -> painterResource(id = R.drawable.ic_type_normal)
        PokemonType.Fighting.toString() -> painterResource(id = R.drawable.ic_type_fighting)
        PokemonType.Flying.toString() -> painterResource(id = R.drawable.ic_type_flying)
        PokemonType.Poison.toString() -> painterResource(id = R.drawable.ic_type_poison)
        PokemonType.Ground.toString() -> painterResource(id = R.drawable.ic_type_ground)
        PokemonType.Rock.toString() -> painterResource(id = R.drawable.ic_type_rock)
        PokemonType.Bug.toString() -> painterResource(id = R.drawable.ic_type_bug)
        PokemonType.Ghost.toString() -> painterResource(id = R.drawable.ic_type_ghost)
        PokemonType.Steel.toString() -> painterResource(id = R.drawable.ic_type_steel)
        PokemonType.Fire.toString() -> painterResource(id = R.drawable.ic_type_fire)
        PokemonType.Water.toString() -> painterResource(id = R.drawable.ic_type_water)
        PokemonType.Grass.toString() -> painterResource(id = R.drawable.ic_type_grass)
        PokemonType.Electric.toString() -> painterResource(id = R.drawable.ic_type_electric)
        PokemonType.Psychic.toString() -> painterResource(id = R.drawable.ic_type_psychic)
        PokemonType.Ice.toString() -> painterResource(id = R.drawable.ic_type_ice)
        PokemonType.Dragon.toString() -> painterResource(id = R.drawable.ic_type_dragon)
        PokemonType.Dark.toString() -> painterResource(id = R.drawable.ic_type_dark)
        PokemonType.Fairy.toString() -> painterResource(id = R.drawable.ic_type_fairy)
        else -> {
            painterResource(id = R.drawable.ic_type_normal)
        }
    }
}