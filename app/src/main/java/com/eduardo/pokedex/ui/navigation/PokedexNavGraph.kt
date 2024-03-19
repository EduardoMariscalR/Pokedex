package com.eduardo.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eduardo.pokedex.ui.home.HomeDestination
import com.eduardo.pokedex.ui.home.HomeScreen
import com.eduardo.pokedex.ui.home.PokemonDetailDestination
import com.eduardo.pokedex.ui.home.PokemonDetailScreen

@Composable
fun PokedexNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToPokemonDetail = { navController.navigate("${PokemonDetailDestination.route}/${it}") }
            )
        }
        composable(
            route = PokemonDetailDestination.routedWithArgs,
            arguments = listOf(navArgument(PokemonDetailDestination.pokemonIdArg) {
                type = NavType.IntType
            })
        ) {
            PokemonDetailScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}