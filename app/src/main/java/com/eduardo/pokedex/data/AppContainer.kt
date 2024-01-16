package com.eduardo.pokedex.data

import com.eduardo.pokedex.core.RetrofitServiceBuilder
import com.eduardo.pokedex.network.PokemonApiService

interface AppContainer {
    val pokedexPokemonRepository:PokedexPokemonRepository
}

class DefaultAppContainer : AppContainer{
    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val retrofitService: PokemonApiService by lazy {
        RetrofitServiceBuilder.buildService(baseUrl, PokemonApiService::class.java)
    }

    override val pokedexPokemonRepository: PokedexPokemonRepository by lazy {
        NetworkPokedexPokemonRepository(retrofitService)
    }
}