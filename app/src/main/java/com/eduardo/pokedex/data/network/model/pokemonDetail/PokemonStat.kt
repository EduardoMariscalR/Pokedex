package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    @SerializedName("base_stat") val baseStat: Int
)