package com.eduardo.pokedex.data.network.model.ability

import com.eduardo.pokedex.data.network.model.Name
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.eduardo.pokedex.data.network.model.VerboseEffect
import com.google.gson.annotations.SerializedName

data class Ability(
    val id: Int,
    val name: String,
    @SerializedName("is_main_series") val isMainSeries: Boolean,
    val generation: NamedApiResource,
    val names: List<Name>,
    @SerializedName("effect_entries") val effectEntries: List<VerboseEffect>,
    @SerializedName("effect_changes") val effectChanges: List<AbilityEffectChange>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<AbilityFlavorText>,
    val pokemon: List<AbilityPokemon>
)