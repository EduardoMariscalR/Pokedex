package com.eduardo.pokedex.data.network.model.ability

import com.eduardo.pokedex.data.network.model.Effect
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class AbilityEffectChange(
    @SerializedName("effect_entries") val effectEntries: List<Effect>,
    @SerializedName("version_group") val versionGroup: NamedApiResource
)