package com.eduardo.pokedex.data.network.model.species

import com.eduardo.pokedex.data.network.model.NamedApiResource

data class Genus(
    val genus: String,
    val language: NamedApiResource
)