package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName


data class PokemonModel(
    @SerializedName("id") val pokemonId: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    @SerializedName("sprites") val images: Sprites,
    val stats: List<Stat>,
    @SerializedName("types") val pokemonTypes: List<PokemonType>,
)
