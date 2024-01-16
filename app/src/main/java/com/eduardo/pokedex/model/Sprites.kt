package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default") val imageRvCard: String,
)