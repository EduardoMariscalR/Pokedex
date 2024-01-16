package com.eduardo.pokedex.data

import com.eduardo.pokedex.model.PokemonModel
import com.eduardo.pokedex.model.PokemonNameModel
import com.eduardo.pokedex.network.PokemonApiService

interface  PokedexPokemonRepository {
    suspend fun getPokemonInfo(name: String): PokemonModel

    suspend fun getPokemonList(): PokemonNameModel

}

class NetworkPokedexPokemonRepository(
    private val pokemonApiService: PokemonApiService
):PokedexPokemonRepository{
    override suspend fun getPokemonInfo(name: String): PokemonModel = pokemonApiService.getPokemonInfo(name)
    override suspend fun getPokemonList(): PokemonNameModel = pokemonApiService.getPokemonList()

}