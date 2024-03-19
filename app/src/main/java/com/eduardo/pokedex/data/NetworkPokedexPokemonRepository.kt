package com.eduardo.pokedex.data

import android.util.Log
import com.eduardo.pokedex.core.PokemonTypeList
import com.eduardo.pokedex.data.local.dao.ListItemDao
import com.eduardo.pokedex.data.local.dao.PokemonCardItemDao
import com.eduardo.pokedex.data.local.dao.TypeItemDao
import com.eduardo.pokedex.data.local.model.ListItem
import com.eduardo.pokedex.data.local.model.PokemonCardItem
import com.eduardo.pokedex.data.local.model.TypeItem
import com.eduardo.pokedex.data.mappers.toListItem
import com.eduardo.pokedex.data.mappers.toPokemonCardData
import com.eduardo.pokedex.data.mappers.toPokemonCardDataUi
import com.eduardo.pokedex.data.mappers.toPokemonCardItem
import com.eduardo.pokedex.data.mappers.toTypeItem
import com.eduardo.pokedex.data.network.PokedexPokemonRepository
import com.eduardo.pokedex.data.network.PokemonApiService
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.eduardo.pokedex.data.network.model.PokemonCardData
import com.eduardo.pokedex.data.network.model.ability.Ability
import com.eduardo.pokedex.data.network.model.pokemonDetail.Pokemon
import com.eduardo.pokedex.data.network.model.species.PokemonSpecies
import com.eduardo.pokedex.data.network.model.type.Type
import com.eduardo.pokedex.ui.home.PokemonCardDataUi
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class NetworkPokedexPokemonRepository(
    private val pokemonApiService: PokemonApiService,
    private val pokemonCardItemDao: PokemonCardItemDao,
    private val listItemDao: ListItemDao,
    private val typeItemDao: TypeItemDao,
) : PokedexPokemonRepository {

    //Retrofit
    // * Detail request
    override suspend fun getPokemonDetail(id: Int): Pokemon =
        pokemonApiService.getPokemonDetail(id)

    override suspend fun getPokemonSpecies(id: Int): PokemonSpecies =
        pokemonApiService.getPokemonSpecies(id)

    override suspend fun getPokemonType(id: Int): Type {
        Log.i("Eduardo", "Se esta solicitando el type $id")
        return pokemonApiService.getPokemonType(id)
    }
    override suspend fun getPokemonAbility(id: Int): Ability =
        pokemonApiService.getPokemonAbility(id)


    // ? List request
    override suspend fun getPokemonDetailList(): List<NamedApiResource> =
        pokemonApiService.getPokemonDetailList().results

    override suspend fun getPokemonSpeciesList(): List<NamedApiResource> =
        pokemonApiService.getPokemonSpeciesList().results

    override suspend fun getPokemonAbilityList(): List<NamedApiResource> =
        pokemonApiService.getPokemonAbilityList().results












    //Room
    // * TypeItemDao
    override suspend fun insertTypeItem(typeItem: TypeItem) =
        typeItemDao.insert(typeItem)

    override suspend fun updateTypeItem(typeItem: TypeItem) =
        typeItemDao.update(typeItem)

    override suspend fun deleteTypeItem(typeItem: TypeItem) =
        typeItemDao.delete(typeItem)

    override suspend fun getAllTypeItems(): List<TypeItem> {
        var localList = typeItemDao.getAllTypeItems()
        if (localList.isEmpty()) {
            val typeList: List<TypeItem> = PokemonTypeList.entries.map{ getPokemonType(it.id).toTypeItem() }
            typeList.forEach {
                insertTypeItem(it)
            }
            localList = typeItemDao.getAllTypeItems()
        }
        return localList
    }

    // * ListItemDao
    override suspend fun insertListItem(listItem: ListItem) =
        listItemDao.insert(listItem)

    override suspend fun updateListItem(listItem: ListItem) =
        listItemDao.update(listItem)

    override suspend fun deleteListItem(listItem: ListItem) =
        listItemDao.delete(listItem)

    override suspend fun getListSortByCategory(category: String): List<ListItem> {
        var localList = listItemDao.getListSortByCategory(category)
        if (localList.isEmpty()) {
            val remote: List<ListItem> =
                when (category) {
                    "pokemon" -> getPokemonDetailList().map { it.toListItem() }
                    "pokemon-species" -> getPokemonSpeciesList().map { it.toListItem() }
                    "ability" -> getPokemonAbilityList().map { it.toListItem() }
                    else -> {
                        emptyList()
                    }
                }
            remote.forEach { insertListItem(it) }
            localList = listItemDao.getListSortByCategory(category)
        }
        return localList
    }











    override suspend fun getPokemonCardItemStream(id: Int): Flow<PokemonCardItem> =
        pokemonCardItemDao.getPokemonCardItem(id)


    override suspend fun getAllPokemonCardItemsStream(): List<PokemonCardDataUi> {
        var localPokemons = pokemonCardItemDao.getAllPokemonCardItems()

        if (localPokemons.isEmpty()) {
            val remotePokemons = getPokemonRemote()
            remotePokemons.forEach {
                Log.i("Eduardo", "Pokemon en curso de grabado DATA ${it.name}")
                insertPokemonCardItem(it.toPokemonCardItem())
            }
            localPokemons = pokemonCardItemDao.getAllPokemonCardItems()
        }
        return localPokemons.map { it.toPokemonCardDataUi() }
    }

    override suspend fun insertPokemonCardItem(pokemonCardItem: PokemonCardItem) =
        pokemonCardItemDao.insert(pokemonCardItem)

    override suspend fun updatePokemonCardItem(pokemonCardItem: PokemonCardItem) =
        pokemonCardItemDao.update(pokemonCardItem)

    override suspend fun deletePokemonCardItem(pokemonCardItem: PokemonCardItem) =
        pokemonCardItemDao.delete(pokemonCardItem)











    private suspend fun getPokemonRemote(): List<PokemonCardData> {
        return try {
            val pokemonTypeList: MutableList<Type> = mutableListOf()
            PokemonTypeList.entries.forEach {
                val currentTye = getPokemonType(it.id)
                pokemonTypeList.add(currentTye)
            }
            pokemonTypeList.forEach{
                insertTypeItem(it.toTypeItem())
            }
            val pokemonList: List<PokemonCardData> =
                getPokemonDetailList().map { it.toPokemonCardData() }
            pokemonList.forEach { pokemon ->
                pokemonTypeList.forEach { pokemonTypeList ->
                    val currentType: String = pokemonTypeList.name
                    pokemonTypeList.pokemon.forEach {
                        if (it.pokemon.name == pokemon.name) {
                            if (it.slot == 1) {
                                pokemon.type1 = currentType
                            } else if (it.slot == 2) {
                                pokemon.type2 = currentType
                            }
                        }
                    }
                }
            }
            pokemonList
        } catch (e: IOException) {
            emptyList()
        } catch (e: HttpException) {
            emptyList()
        }
    }
}