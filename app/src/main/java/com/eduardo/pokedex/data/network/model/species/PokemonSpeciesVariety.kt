package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesVariety(
    @SerializedName("is_default") val isDefault: Boolean,
    val pokemon: NamedApiResource
)