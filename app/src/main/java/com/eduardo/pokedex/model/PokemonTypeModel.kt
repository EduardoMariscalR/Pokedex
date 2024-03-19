package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonTypeModel(
    val id: Int,
    val name: String,
    @SerializedName("pokemon")
    val pokemonList: List<Pokemon>
)
