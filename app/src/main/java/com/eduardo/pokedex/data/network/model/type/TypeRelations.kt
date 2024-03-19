package com.eduardo.pokedex.data.network.model.type

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class TypeRelations(
    @SerializedName("no_damage_to") val noDamageTo: List<NamedApiResource>,
    @SerializedName("half_damage_to") val halfDamageTo: List<NamedApiResource>,
    @SerializedName("double_damage_to") val doubleDamageTo: List<NamedApiResource>,
    @SerializedName("no_damage_from") val noDamageFrom: List<NamedApiResource>,
    @SerializedName("half_damage_from") val halfDamageFrom: List<NamedApiResource>,
    @SerializedName("double_damage_from") val doubleDamageFrom: List<NamedApiResource>
)