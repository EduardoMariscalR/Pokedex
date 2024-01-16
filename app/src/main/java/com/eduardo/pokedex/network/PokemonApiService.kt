package com.eduardo.pokedex.network

import com.eduardo.pokedex.model.PokemonModel
import com.eduardo.pokedex.model.PokemonNameModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): PokemonModel

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 900,
        @Query("offset") offset: Int = 0
    ) : PokemonNameModel

}
