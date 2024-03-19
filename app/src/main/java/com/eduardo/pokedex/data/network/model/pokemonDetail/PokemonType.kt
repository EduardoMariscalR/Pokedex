package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)