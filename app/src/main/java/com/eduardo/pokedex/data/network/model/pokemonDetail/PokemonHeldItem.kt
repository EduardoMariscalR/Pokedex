package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource

data class PokemonHeldItem(
    val item: NamedApiResource,
    val versionDetails: List<PokemonHeldItemVersion>
)