package com.eduardo.pokedex.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val pokemonType :List<PokemonType>
)
