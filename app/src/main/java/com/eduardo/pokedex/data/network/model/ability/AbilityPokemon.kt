package com.eduardo.pokedex.data.network.model.ability

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class AbilityPokemon(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val pokemon: NamedApiResource
)