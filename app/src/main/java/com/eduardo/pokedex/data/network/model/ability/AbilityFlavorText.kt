package com.eduardo.pokedex.data.network.model.ability

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class AbilityFlavorText(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResource,
    @SerializedName("version_group") val versionGroup: NamedApiResource
)