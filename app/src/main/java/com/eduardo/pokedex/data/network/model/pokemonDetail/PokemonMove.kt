package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonMove(
    val move: NamedApiResource,
    @SerializedName("version_group_details") val versionGroupDetails: List<PokemonMoveVersion>
)