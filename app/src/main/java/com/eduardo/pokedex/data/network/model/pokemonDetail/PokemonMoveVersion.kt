package com.eduardo.pokedex.data.network.model.pokemonDetail

import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class PokemonMoveVersion(
    @SerializedName("move_learn_method") val moveLearnMethod: NamedApiResource,
    @SerializedName("version_group") val versionGroup: NamedApiResource,
    @SerializedName("level_learned_at") val levelLearnedAt: Int
)