package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: APIResource,
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int
)