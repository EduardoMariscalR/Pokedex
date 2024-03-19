package com.eduardo.pokedex.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eduardo.pokedex.R
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
fun GetPokemonTypeCard(pokemonType: String, modifier: Modifier) {
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
                textAlign = TextAlign.Center,
                color = getPokemonTextColor(pokemonType)
            )
        }
    }
}

fun getPokemonTextColor(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonTypeList.Normal.toString() -> Color.Black
        PokemonTypeList.Fighting.toString() -> Color.White
        PokemonTypeList.Flying.toString() -> Color.Black
        PokemonTypeList.Poison.toString() -> Color.Black
        PokemonTypeList.Ground.toString() -> Color.Black
        PokemonTypeList.Rock.toString() -> Color.Black
        PokemonTypeList.Bug.toString() -> Color.Black
        PokemonTypeList.Ghost.toString() -> Color.White
        PokemonTypeList.Steel.toString() -> Color.Black
        PokemonTypeList.Fire.toString() -> Color.Black
        PokemonTypeList.Water.toString() -> Color.Black
        PokemonTypeList.Grass.toString() -> Color.Black
        PokemonTypeList.Electric.toString() -> Color.Black
        PokemonTypeList.Psychic.toString() -> Color.Black
        PokemonTypeList.Ice.toString() -> Color.Black
        PokemonTypeList.Dragon.toString() -> Color.White
        PokemonTypeList.Dark.toString() -> Color.White
        PokemonTypeList.Fairy.toString() -> Color.Black
        else -> {
            Color.White
        }
    }
}

fun getPokemonColor(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonTypeList.Normal.toString() -> Normal
        PokemonTypeList.Fighting.toString() -> Fighting
        PokemonTypeList.Flying.toString() -> Flying
        PokemonTypeList.Poison.toString() -> Poison
        PokemonTypeList.Ground.toString() -> Ground
        PokemonTypeList.Rock.toString() -> Rock
        PokemonTypeList.Bug.toString() -> Bug
        PokemonTypeList.Ghost.toString() -> Ghost
        PokemonTypeList.Steel.toString() -> Steel
        PokemonTypeList.Fire.toString() -> Fire
        PokemonTypeList.Water.toString() -> Water
        PokemonTypeList.Grass.toString() -> Grass
        PokemonTypeList.Electric.toString() -> Electric
        PokemonTypeList.Psychic.toString() -> Psychic
        PokemonTypeList.Ice.toString() -> Ice
        PokemonTypeList.Dragon.toString() -> Dragon
        PokemonTypeList.Dark.toString() -> Dark
        PokemonTypeList.Fairy.toString() -> Fairy
        else -> {
            Color.Black
        }
    }
}

fun getPokemonColorTransparent(pokemonType: String): Color {
    return when (pokemonType) {
        PokemonTypeList.Normal.toString() -> NormalTransparent
        PokemonTypeList.Fighting.toString() -> FightingTransparent
        PokemonTypeList.Flying.toString() -> FlyingTransparent
        PokemonTypeList.Poison.toString() -> PoisonTransparent
        PokemonTypeList.Ground.toString() -> GroundTransparent
        PokemonTypeList.Rock.toString() -> RockTransparent
        PokemonTypeList.Bug.toString() -> BugTransparent
        PokemonTypeList.Ghost.toString() -> GhostTransparent
        PokemonTypeList.Steel.toString() -> SteelTransparent
        PokemonTypeList.Fire.toString() -> FireTransparent
        PokemonTypeList.Water.toString() -> WaterTransparent
        PokemonTypeList.Grass.toString() -> GrassTransparent
        PokemonTypeList.Electric.toString() -> ElectricTransparent
        PokemonTypeList.Psychic.toString() -> PsychicTransparent
        PokemonTypeList.Ice.toString() -> IceTransparent
        PokemonTypeList.Dragon.toString() -> DragonTransparent
        PokemonTypeList.Dark.toString() -> DarkTransparent
        PokemonTypeList.Fairy.toString() -> FairyTransparent
        else -> {
            Color.Black
        }
    }
}

@Composable
fun getPokemonIcon(pokemonType: String): Painter {
    return when (pokemonType) {
        PokemonTypeList.Normal.toString() -> painterResource(id = R.drawable.ic_type_normal)
        PokemonTypeList.Fighting.toString() -> painterResource(id = R.drawable.ic_type_fighting)
        PokemonTypeList.Flying.toString() -> painterResource(id = R.drawable.ic_type_flying)
        PokemonTypeList.Poison.toString() -> painterResource(id = R.drawable.ic_type_poison)
        PokemonTypeList.Ground.toString() -> painterResource(id = R.drawable.ic_type_ground)
        PokemonTypeList.Rock.toString() -> painterResource(id = R.drawable.ic_type_rock)
        PokemonTypeList.Bug.toString() -> painterResource(id = R.drawable.ic_type_bug)
        PokemonTypeList.Ghost.toString() -> painterResource(id = R.drawable.ic_type_ghost)
        PokemonTypeList.Steel.toString() -> painterResource(id = R.drawable.ic_type_steel)
        PokemonTypeList.Fire.toString() -> painterResource(id = R.drawable.ic_type_fire)
        PokemonTypeList.Water.toString() -> painterResource(id = R.drawable.ic_type_water)
        PokemonTypeList.Grass.toString() -> painterResource(id = R.drawable.ic_type_grass)
        PokemonTypeList.Electric.toString() -> painterResource(id = R.drawable.ic_type_electric)
        PokemonTypeList.Psychic.toString() -> painterResource(id = R.drawable.ic_type_psychic)
        PokemonTypeList.Ice.toString() -> painterResource(id = R.drawable.ic_type_ice)
        PokemonTypeList.Dragon.toString() -> painterResource(id = R.drawable.ic_type_dragon)
        PokemonTypeList.Dark.toString() -> painterResource(id = R.drawable.ic_type_dark)
        PokemonTypeList.Fairy.toString() -> painterResource(id = R.drawable.ic_type_fairy)
        else -> {
            painterResource(id = R.drawable.ic_type_normal)
        }
    }
}