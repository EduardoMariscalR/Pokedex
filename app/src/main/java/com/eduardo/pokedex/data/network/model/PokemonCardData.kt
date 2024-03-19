package com.eduardo.pokedex.data.network.model

data class PokemonCardData(
    val id: Int,
    val name: String,
    var type1: String?,
    var type2: String?,
    var favorite: Boolean = false
)