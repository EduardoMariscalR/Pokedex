package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDexEntry(
    @SerializedName("entry_number") val entryNumber: Int,
    val pokedex: NamedApiResource
)