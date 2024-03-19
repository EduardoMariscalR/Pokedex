package com.eduardo.pokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_card_items")
data class PokemonCardItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    var type1: String,
    var type2: String?,
    val favorite: Boolean
)

