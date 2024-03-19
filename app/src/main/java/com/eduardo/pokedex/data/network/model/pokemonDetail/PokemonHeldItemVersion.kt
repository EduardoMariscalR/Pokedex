package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource

data class PokemonHeldItemVersion(
    val version: NamedApiResource,
    val rarity: Int
)