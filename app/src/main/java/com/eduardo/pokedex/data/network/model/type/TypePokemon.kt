package com.eduardo.pokedex.data.network.model.type

import com.eduardo.pokedex.data.network.model.NamedApiResource

data class TypePokemon(
    val slot: Int,
    val pokemon: NamedApiResource
)