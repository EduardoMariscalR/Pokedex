package com.eduardo.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eduardo.pokedex.data.local.model.PokemonCardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonCardItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemonCardItem: PokemonCardItem)

    @Update
    suspend fun update(pokemonCardItem: PokemonCardItem)

    @Delete
    suspend fun delete(pokemonCardItem: PokemonCardItem)

    @Query("SELECT * FROM pokemon_card_items WHERE id = :id")
    fun getPokemonCardItem(id: Int): Flow<PokemonCardItem>

    @Query("SELECT * FROM pokemon_card_items ORDER BY id ASC")
    suspend fun getAllPokemonCardItems(): List<PokemonCardItem>

//    @Upsert
//    suspend fun upsert(pokemonTypeList: PokemonTypeListEntity)


}