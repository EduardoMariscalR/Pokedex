package com.eduardo.pokedex.data.network

import com.eduardo.pokedex.data.network.model.NamedApiResourceList
import com.eduardo.pokedex.data.network.model.ability.Ability
import com.eduardo.pokedex.data.network.model.pokemonDetail.Pokemon
import com.eduardo.pokedex.data.network.model.species.PokemonSpecies
import com.eduardo.pokedex.data.network.model.type.Type
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon/{id}") // * Pokemon Detail
    suspend fun getPokemonDetail(@Path("id") id: Int): Pokemon

    @GET("pokemon-species/{id}") // * Pokemon Species
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpecies

    @GET("type/{id}")
    suspend fun getPokemonType(@Path("id") id: Int): Type

    @GET("ability/{id}")
    suspend fun getPokemonAbility(@Path("id") id: Int): Ability

    @GET("pokemon")
    suspend fun getPokemonDetailList(
        @Query("limit") limit: Int = 1025,
        @Query("offset") offset: Int = 0
    ): NamedApiResourceList

    @GET("pokemon-species")
    suspend fun getPokemonSpeciesList(
        @Query("limit") limit: Int = 1025,
        @Query("offset") offset: Int = 0
    ): NamedApiResourceList

    @GET("ability")
    suspend fun getPokemonAbilityList(
        @Query("limit") limit: Int = 307,
        @Query("offset") offset: Int = 0
    ): NamedApiResourceList


}
