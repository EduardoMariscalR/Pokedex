package com.eduardo.pokedex.model

import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: AnyName,
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int
)