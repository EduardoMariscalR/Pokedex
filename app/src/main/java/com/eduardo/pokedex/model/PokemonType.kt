package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot") val slotType: Int,
    @SerializedName("type") val type: APIResource
)