package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.ApiResource
import com.eduardo.pokedex.data.network.model.Description
import com.eduardo.pokedex.data.network.model.Name
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    @SerializedName("gender_rate") val genderRate: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("is_baby") val isBaby: Boolean,
    @SerializedName("is_legendary") val isLegendary: Boolean,
    @SerializedName("is_mythical") val isMythical: Boolean,
    @SerializedName("hatch_counter") val hatchCounter: Int,
    @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean,
    @SerializedName("forms_switchable") val formsSwitchable: Boolean,
    @SerializedName("growth_rate") val growthRate: NamedApiResource,
    @SerializedName("pokedex_numbers") val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    @SerializedName("egg_groups") val eggGroups: List<NamedApiResource>,
    val color: NamedApiResource,
    val shape: NamedApiResource,
    @SerializedName("evolves_from_species") val evolvesFromSpecies: NamedApiResource?,
    @SerializedName("evolution_chain") val evolutionChain: ApiResource,
    val habitat: NamedApiResource?,
    val generation: NamedApiResource,
    val names: List<Name>,
    @SerializedName("pal_park_encounters") val palParkEncounters: List<PalParkEncounterArea>,
    @SerializedName("form_descriptions") val formDescriptions: List<Description>,
    val genera: List<Genus>,
    val varieties: List<PokemonSpeciesVariety>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<PokemonSpeciesFlavorText>
)