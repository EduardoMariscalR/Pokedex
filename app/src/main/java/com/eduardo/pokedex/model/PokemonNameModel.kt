package com.eduardo.pokedex.model

import com.eduardo.pokedex.data.network.model.PokemonCardData

data class PokemonNameModel (
    val results : List<PokemonCardData>,
)