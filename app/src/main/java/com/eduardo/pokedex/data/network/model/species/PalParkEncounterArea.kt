package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PalParkEncounterArea(
    @SerializedName("base_score") val baseScore: Int,
    val rate: Int,
    val area: NamedApiResource
)