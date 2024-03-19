package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesFlavorText(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResource,
    val version: NamedApiResource
)