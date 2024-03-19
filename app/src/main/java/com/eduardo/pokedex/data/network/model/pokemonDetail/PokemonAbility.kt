package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonAbility(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)