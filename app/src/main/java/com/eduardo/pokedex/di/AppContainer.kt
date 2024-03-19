package com.eduardo.pokedex.di

import android.content.Context
import com.eduardo.pokedex.core.RetrofitServiceBuilder
import com.eduardo.pokedex.data.NetworkPokedexPokemonRepository
import com.eduardo.pokedex.data.local.PokemonDatabase
import com.eduardo.pokedex.data.network.PokedexPokemonRepository
import com.eduardo.pokedex.data.network.PokemonApiService

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val pokedexPokemonRepository: PokedexPokemonRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val retrofitService: PokemonApiService by lazy {
        RetrofitServiceBuilder.buildService(baseUrl, PokemonApiService::class.java)
    }

    override val pokedexPokemonRepository: PokedexPokemonRepository by lazy {
        NetworkPokedexPokemonRepository(
            retrofitService,
            PokemonDatabase.getDatabase(context).pokemonCardItemDao(),
            PokemonDatabase.getDatabase(context).listItemDao(),
            PokemonDatabase.getDatabase(context).typeItemDao()
        )
    }
}