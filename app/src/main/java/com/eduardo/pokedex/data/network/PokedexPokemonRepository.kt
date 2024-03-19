package com.eduardo.pokedex.data.network

import com.eduardo.pokedex.data.local.model.ListItem
import com.eduardo.pokedex.data.local.model.PokemonCardItem
import com.eduardo.pokedex.data.local.model.TypeItem
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.eduardo.pokedex.data.network.model.ability.Ability
import com.eduardo.pokedex.data.network.model.pokemonDetail.Pokemon
import com.eduardo.pokedex.data.network.model.species.PokemonSpecies
import com.eduardo.pokedex.data.network.model.type.Type
import com.eduardo.pokedex.ui.home.PokemonCardDataUi
import kotlinx.coroutines.flow.Flow

interface PokedexPokemonRepository {


    //Retrofit
    // * Detail request
    suspend fun getPokemonDetail(id: Int): Pokemon
    suspend fun  getPokemonSpecies(id: Int): PokemonSpecies
    suspend fun getPokemonType(id: Int): Type
    suspend fun getPokemonAbility(id: Int): Ability

    // ? List request
    suspend fun getPokemonDetailList(): List<NamedApiResource>
    suspend fun getPokemonSpeciesList(): List<NamedApiResource>
    suspend fun getPokemonAbilityList(): List<NamedApiResource>


    //Room
    // * Detail request Type
    suspend fun insertTypeItem(typeItem: TypeItem)
    suspend fun updateTypeItem(typeItem: TypeItem)
    suspend fun deleteTypeItem(typeItem: TypeItem)
    suspend fun getAllTypeItems(): List<TypeItem>

    // ? List request Item
    suspend fun insertListItem(listItem: ListItem)
    suspend fun updateListItem(listItem: ListItem)
    suspend fun deleteListItem(listItem: ListItem)
    suspend fun getListSortByCategory(category: String): List<ListItem>




    suspend fun getPokemonCardItemStream(id: Int): Flow<PokemonCardItem>
    suspend fun getAllPokemonCardItemsStream(): List<PokemonCardDataUi>
    suspend fun insertPokemonCardItem(pokemonCardItem: PokemonCardItem)
    suspend fun updatePokemonCardItem(pokemonCardItem: PokemonCardItem)
    suspend fun deletePokemonCardItem(pokemonCardItem: PokemonCardItem)

}

