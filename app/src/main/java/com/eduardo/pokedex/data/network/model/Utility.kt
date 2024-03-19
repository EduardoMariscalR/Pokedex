package com.eduardo.pokedex.data.network.model

import com.google.gson.annotations.SerializedName

data class Language(
    val id: Int,
    val name: String,
    val official: Boolean,
    val iso639: String,
    val iso3166: String,
    val names: List<Name>
)

data class Description(
    val description: String,
    val language: NamedApiResource
)

data class Effect(
    val effect: String,
    val language: NamedApiResource
)

data class Encounter(
    val minLevel: Int,
    val maxLevel: Int,
    val conditionValues: List<NamedApiResource>,
    val chance: Int,
    val method: NamedApiResource
)

data class FlavorText(
    val flavorText: String,
    val language: NamedApiResource
)

data class GenerationGameIndex(
    @SerializedName("game_index") val gameIndex: Int,
    val generation: NamedApiResource
)

data class MachineVersionDetail(
    val machine: ApiResource,
    val versionGroup: NamedApiResource
)

data class Name(
    val name: String,
    val language: NamedApiResource
)

data class VerboseEffect(
    val effect: String,
    @SerializedName("short_effect") val shortEffect: String,
    val language: NamedApiResource
)

data class VersionEncounterDetail(
    val version: NamedApiResource,
    val maxChance: Int,
    val encounterDetails: List<Encounter>
)

data class VersionGameIndex(
    @SerializedName("game_index") val gameIndex: Int,
    val version: NamedApiResource
)

data class VersionGroupFlavorText(
    val text: String,
    val language: NamedApiResource,
    val versionGroup: NamedApiResource
)