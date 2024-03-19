package com.eduardo.pokedex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eduardo.pokedex.PokedexTopAppBar
import com.eduardo.pokedex.R
import com.eduardo.pokedex.core.PokemonTypeList
import com.eduardo.pokedex.core.getPokemonColor
import com.eduardo.pokedex.core.getPokemonColorTransparent
import com.eduardo.pokedex.core.getPokemonIcon
import com.eduardo.pokedex.core.getPokemonTextColor
import com.eduardo.pokedex.ui.AppViewModelProvider
import com.eduardo.pokedex.ui.home.widget.ErrorScreen
import com.eduardo.pokedex.ui.home.widget.LoadingScreen
import com.eduardo.pokedex.ui.home.widget.PokemonCard
import com.eduardo.pokedex.ui.navigation.NavigationDestination
import com.eduardo.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToPokemonDetail: (Int) -> Unit,
//    pokemonUiState: PokemonUiState,
//    retryAction: () -> Unit,
//    searchPokemon: (String,String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val filter = viewModel::filterPokemon
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    when (val homeUiState = viewModel.homeUiState) {
        is PokemonUiState.Loading ->
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PokedexTopAppBar(
                        title = stringResource(HomeDestination.titleRes),
                        canNavigateBack = false,
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


        is PokemonUiState.Success ->
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    SearchBar(
                        query = searchText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        onQueryChange = {
                            searchText = it
                            filter(it, "")
//                            if (searchText.isNotEmpty()) {
//                            }
                        },
                        onSearch = {
                            active = false
                        },
                        active = active,
                        onActiveChange = {
                            active = it
                        },
                        leadingIcon = { Icon(Icons.Default.Search, "search icon") },
                        placeholder = { Text(text = "Search Pokemon") },
                        shape = RoundedCornerShape(50.dp)
                    ) {

                        LazyColumn(
                            state = LazyListState(firstVisibleItemIndex = 0),
                            modifier = modifier,
                            contentPadding = PaddingValues(6.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = homeUiState.pokemon.value,
                                key = { pokemon -> pokemon.name }
                            ) { pokemon ->
                                Row(
                                    modifier = Modifier
                                        .border(
                                            1.dp,
                                            getPokemonColor(pokemon.type1.capitalize()),
                                            RoundedCornerShape(50.dp)
                                        )
                                        .background(
                                            getPokemonColorTransparent(pokemon.type1.capitalize()),
                                            RoundedCornerShape(50.dp)
                                        )
                                        .clickable(enabled = true, onClick = {
                                            searchText = pokemon.name
                                            if (searchText.isNotEmpty()) {
                                                filter(pokemon.name, "")
                                            }
                                            active = false
                                        })
                                        .fillMaxWidth(), horizontalArrangement = Arrangement.Start
                                ) {
                                    Spacer(modifier = Modifier.size(10.dp))
                                    val url =
                                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"
                                    AsyncImage(
                                        model = ImageRequest.Builder(context = LocalContext.current)
                                            .data(url).crossfade(true).build(),
                                        error = painterResource(R.drawable.ic_broken_image),
                                        placeholder = painterResource(R.drawable.loading_img),
                                        contentDescription = stringResource(R.string.pokemon_image),
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(60.dp, 60.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        text = "N°" + String.format("%03d", pokemon.id),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .align(Alignment.CenterVertically)
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        pokemon.name.capitalize(),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                        modifier = modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    PokemonGridScreen(
                        homeUiState.pokemon,
                        searchText,
                        filter,
                        filterType = viewModel::setFilterType,
                        onItemClick = navigateToPokemonDetail,
                        modifier = modifier
                            .fillMaxWidth()
                    )
                }
            }


        is PokemonUiState.Error ->
            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    PokedexTopAppBar(
                        title = stringResource(HomeDestination.titleRes),
                        canNavigateBack = false,
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
                        retryAction = viewModel::getPokemonList,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonGridScreen(
    pokemons: StateFlow<List<PokemonCardDataUi>>,
    searchtext: String,
    filter: (String, String) -> Unit,
    filterType: (String) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetStateType = rememberModalBottomSheetState()
    val sheetStateOrder = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheetType by remember { mutableStateOf(false) }
    var showBottomSheetOrder by remember { mutableStateOf(false) }
    var stringTypeResource by remember { mutableStateOf("All Types") }
    var iconOrderResource by remember { mutableIntStateOf(R.drawable.sort_numeric_ascending) }
    var sortOrder by remember {
        mutableStateOf("NumericAscending")
    }
    if (showBottomSheetType) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheetType = false
            }, sheetState = sheetStateType
        ) {
            // Sheet content
            Text(
                text = "Select a type",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = LazyListState(),
                modifier = modifier,
                contentPadding = PaddingValues(6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Button(
                        onClick = {
                            filterType("")
                            filter(searchtext, sortOrder)
                            stringTypeResource = "All Types"
                            scope.launch { sheetStateType.hide() }.invokeOnCompletion {
                                if (!sheetStateType.isVisible) {
                                    showBottomSheetType = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("All Types", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    }
                }
                items(items = PokemonTypeList.entries, key = { type -> type.name }) { type ->
                    Button(
                        onClick = {
                            filterType(type.name)
                            filter(searchtext, sortOrder)
                            stringTypeResource = type.name.capitalize()
                            scope.launch { sheetStateType.hide() }.invokeOnCompletion {
                                if (!sheetStateType.isVisible) {
                                    showBottomSheetType = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = getPokemonColor(type.name)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(0.5f))
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color.White, CircleShape)
                        ) {
                            Icon(
                                painter = getPokemonIcon(type.name),
                                contentDescription = stringResource(R.string.pokemon_icon),
                                tint = getPokemonColor(type.name),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(3.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(
                            type.name.capitalize(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(5.dp),
                            color = getPokemonTextColor(type.name)
                        )

                    }
                }
            }
        }
    }
    if (showBottomSheetOrder) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheetOrder = false
            }, sheetState = sheetStateOrder
        ) {
            // Sheet content
            Text(
                text = "Sort by",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    sortOrder = "NumericAscending"
                    filter(searchtext, sortOrder)
                    iconOrderResource = R.drawable.sort_numeric_ascending
                    scope.launch { sheetStateOrder.hide() }.invokeOnCompletion {
                        if (!sheetStateOrder.isVisible) {
                            showBottomSheetOrder = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.9f))
                Icon(
                    painterResource(R.drawable.sort_numeric_ascending),
                    "Sort numeric ascending Icon"
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    "Id",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    sortOrder = "NumericDescending"
                    filter(searchtext, sortOrder)
                    iconOrderResource = R.drawable.sort_numeric_descending
                    scope.launch { sheetStateOrder.hide() }.invokeOnCompletion {
                        if (!sheetStateOrder.isVisible) {
                            showBottomSheetOrder = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.9f))
                Icon(
                    painterResource(R.drawable.sort_numeric_descending),
                    "Sort numeric descending Icon"
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    "Id",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    sortOrder = "AlphabeticalAscending"
                    filter(searchtext, sortOrder)
                    iconOrderResource = R.drawable.sort_alphabetical_ascending
                    scope.launch { sheetStateOrder.hide() }.invokeOnCompletion {
                        if (!sheetStateOrder.isVisible) {
                            showBottomSheetOrder = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.7f))
                Icon(
                    painterResource(R.drawable.sort_alphabetical_ascending),
                    "Sort alphabetical ascending Icon"
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    "Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    sortOrder = "AlphabeticalDescending"
                    filter(searchtext, sortOrder)
                    iconOrderResource = R.drawable.sort_alphabetical_descending
                    scope.launch { sheetStateOrder.hide() }.invokeOnCompletion {
                        if (!sheetStateOrder.isVisible) {
                            showBottomSheetOrder = false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.7f))
                Icon(
                    painterResource(R.drawable.sort_alphabetical_descending),
                    "Sort alphabetical descending Icon"
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    "Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { showBottomSheetType = true }, colors = ButtonDefaults.buttonColors(
                    containerColor = getPokemonColor(
                        stringTypeResource
                    )
                ), modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = stringTypeResource,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = getPokemonTextColor(stringTypeResource)
                )
                if (stringTypeResource == "All Types") {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "KeyboardArrowDown Icon"
                    )
                } else {
                    Spacer(modifier = Modifier.size(5.dp))
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            painter = getPokemonIcon(stringTypeResource),
                            contentDescription = stringResource(R.string.pokemon_icon),
                            tint = getPokemonColor(stringTypeResource),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Button(
                onClick = { showBottomSheetOrder = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Short by",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painterResource(iconOrderResource), contentDescription = "Sort Icon"
                )
            }
        }
        LazyColumn(
            state = LazyListState(
                firstVisibleItemIndex = 0, firstVisibleItemScrollOffset = 0
            ),
            modifier = modifier,
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = pokemons.value, key = { pokemon -> pokemon.name }) { pokemon ->
                PokemonCard(
                    pokemon, modifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(2.5f)
                        .clickable { onItemClick(pokemon.id) }
                )
            }
        }
    }
}

@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    PokedexTheme {
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}