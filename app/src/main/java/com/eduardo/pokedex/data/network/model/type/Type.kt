package com.eduardo.pokedex.data.network.model.type

import com.eduardo.pokedex.data.network.model.GenerationGameIndex
import com.eduardo.pokedex.data.network.model.Name
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.google.gson.annotations.SerializedName

data class Type(
    val id: Int,
    val name: String,
    @SerializedName("damage_relations") val damageRelations: TypeRelations,
    @SerializedName("game_indices") val gameIndices: List<GenerationGameIndex>,
    val generation: NamedApiResource,
    @SerializedName("move_damage_class") val moveDamageClass: NamedApiResource?,
    val names: List<Name>,
    val pokemon: List<TypePokemon>,
    val moves: List<NamedApiResource>
)
