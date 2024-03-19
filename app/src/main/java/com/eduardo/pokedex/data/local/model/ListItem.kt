package com.eduardo.pokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_resource_list")
data class ListItem(
    @PrimaryKey
    val url: String,
    val name: String,
    val id: Int,
    val category: String,
)