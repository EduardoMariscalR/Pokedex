package com.eduardo.pokedex.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eduardo.pokedex.PokedexTopAppBar
import com.eduardo.pokedex.R
import com.eduardo.pokedex.core.GetPokemonTypeCard
import com.eduardo.pokedex.core.getPokemonColor
import com.eduardo.pokedex.core.getPokemonIcon
import com.eduardo.pokedex.data.network.model.pokemonDetail.Pokemon
import com.eduardo.pokedex.data.network.model.species.PokemonSpecies
import com.eduardo.pokedex.ui.AppViewModelProvider
import com.eduardo.pokedex.ui.home.widget.ErrorScreen
import com.eduardo.pokedex.ui.home.widget.LoadingScreen
import com.eduardo.pokedex.ui.navigation.NavigationDestination

object PokemonDetailDestination : NavigationDestination {
    override val route = "detail"
    override val titleRes = R.string.pokemon_detail
    const val pokemonIdArg = "pokemonId"
    val routedWithArgs = "$route/{$pokemonIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: PokemonDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    when (val pokemonDetailUiState = viewModel.pokemonDetailUiState) {
        is PokemonDetailUiState.Loading ->
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PokedexTopAppBar(
                        title = stringResource(PokemonDetailDestination.titleRes),
                        canNavigateBack = true,
                        navigateUp = navigateBack,
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    LoadingScreen(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 100.dp, vertical = 250.dp)
                    )
                }
            }

        is PokemonDetailUiState.Error ->
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PokedexTopAppBar(
                        title = stringResource(PokemonDetailDestination.titleRes),
                        canNavigateBack = true,
                        navigateUp = navigateBack,
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    ErrorScreen(
                        retryAction = { },
                        modifier = modifier.fillMaxSize()
                    )
                }
            }

        is PokemonDetailUiState.Success ->
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PokedexTopAppBar(
                        title = stringResource(PokemonDetailDestination.titleRes),
                        canNavigateBack = canNavigateBack,
                        navigateUp = onNavigateUp,
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    PokemonDetailBody(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
                        pokemonDetailUiState.pokemon,
                        pokemonDetailUiState.pokemonSpecies
                    )
                }
            }
    }
}


@Composable
fun PokemonDetailBody(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    pokemonSpecies: PokemonSpecies
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 4.dp, top = 3.dp, bottom = 3.dp, end = 2.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                translate(top = -250f) {
                    drawCircle(
                        getPokemonColor(pokemon.types[0].type.name.capitalize()),
                        radius = 300.dp.toPx(),
                        alpha = 0.9F,
                    )
                }
            }
            Image(
                painter = getPokemonIcon(pokemon.types[0].type.name.capitalize()),
                contentDescription = stringResource(R.string.pokemon_icon),
                alpha = 0.5f,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .align(Alignment.Center)
            )
            val url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png"
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(url)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.pokemon_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column {
            Text(
                text = pokemon.name.capitalize().replace("-", " "),
                fontWeight = FontWeight.SemiBold,
                lineHeight = 1.01.em,
                fontSize = 32.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "N°" + String.format("%03d", pokemon.id),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        var pokemonDescription = pokemonSpecies.flavorTextEntries[91].flavorText.replace("[\n\r]".toRegex(), " ")
        Text(
            text = pokemonDescription,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetPokemonTypeCard(
                pokemon.types[0].type.name.capitalize(),
                Modifier.weight(1f)
            )
            if (pokemon.types.size > 1) {
                Spacer(modifier = Modifier.size(20.dp))
                GetPokemonTypeCard(
                    pokemon.types[1].type.name.capitalize(),
                    Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_scale_24),
                        contentDescription = "Weight Icon"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Weight")
                }
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(30))
                        .padding(2.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${pokemon.weight} kg",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 32.sp
                    )
                }
            }
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Row {
                    Icon(Icons.Default.Build, contentDescription = "Weight Icon")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Height")
                }
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(30))
                        .padding(2.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${pokemon.height} m",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 32.sp
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Row {
                    Icon(Icons.Default.Build, contentDescription = "Category Icon")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Category")
                }
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(30))
                        .padding(2.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = pokemonSpecies.genera[7].genus, fontWeight = FontWeight.SemiBold, fontSize = 28.sp)
                }
            }
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Row {
                    Icon(Icons.Default.Build, contentDescription = "Ability Icon")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Ability")
                }
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(30))
                        .padding(2.dp)
                        .fillMaxWidth(),
                ) {
                    pokemon.abilities.forEach { ability ->
                        Text(
                            text = ability.ability.name.capitalize(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        Column {
            Text(
                text = "Gender",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            val pro: Float = pokemonSpecies.genderRate.div(8F)
            LinearProgressIndicator(
                progress = pro,
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth(),
                color = Color.Blue,
                trackColor = Color.Red,
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Icon(Icons.Default.Settings, contentDescription = "")
                Text(text = "${pro*100}%")
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Settings, contentDescription = "")
                Text(text = "${(1-pro)*100}%")
            }
        }
        val list = listOf("Grass", "Normal", "Dragon")
        Column {
            Text(text = "Dev", fontWeight = FontWeight.SemiBold, fontSize = 32.sp)
            list.forEach() {
                GetPokemonTypeCard(pokemonType = it, modifier = Modifier)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PokemonDetailScreenPreview() {
//    PokedexTheme {
//        PokemonDetailBody(
//            pokemon = Pokemon(
//                id = 1,
//                name = "bulbasaur",
//                baseExperience = 50,
//                height = 7,
//                isDefault = false,
//                order = 1,
//                weight = 69,
//                species = NamedApiResource("bulbasaur", ""),
//                abilities = listOf(
//                    PokemonAbility(
//                        ability = NamedApiResource("overgrow", ""),
//                        slot = 1,
//                        isHidden = false
//                    ),
//                    PokemonAbility(
//                        ability = NamedApiResource("chlorophyll", ""),
//                        slot = 2,
//                        isHidden = false
//                    )
//                ),
//                forms = listOf(
//                    NamedApiResource("bulbasaur", "")
//                ),
//                gameIndices = listOf(
//                    VersionGameIndex(0, NamedApiResource("", ""))
//                ),
//                heldItems = listOf(
//
//                ),
//                moves = listOf(),
//                stats = listOf(
//                    PokemonStat(
//                        baseStat = 46,
//                        effort = 0,
//                        stat = NamedApiResource(
//                            name = "hp",
//                            url = "https://pokeapi.co/api/v2/stat/1/"
//                        )
//                    )
//                ),
//                types = listOf(
//                    PokemonType(
//                        slot = 1,
//                        type = NamedApiResource(name = "grass", url = "")
//                    ),
//                    PokemonType(
//                        slot = 2,
//                        type = NamedApiResource(name = "poison", url = "")
//                    )
//                ),
//                sprites = PokemonSprites(
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//                )
//            )
//        )
//    }
//}