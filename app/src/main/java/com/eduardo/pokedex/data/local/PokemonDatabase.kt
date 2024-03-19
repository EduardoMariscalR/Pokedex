package com.eduardo.pokedex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eduardo.pokedex.data.local.dao.ListItemDao
import com.eduardo.pokedex.data.local.dao.PokemonCardItemDao
import com.eduardo.pokedex.data.local.dao.TypeItemDao
import com.eduardo.pokedex.data.local.model.ListItem
import com.eduardo.pokedex.data.local.model.PokemonCardItem
import com.eduardo.pokedex.data.local.model.TypeItem

@Database(entities = [PokemonCardItem::class,ListItem::class,TypeItem::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonCardItemDao(): PokemonCardItemDao
    abstract fun listItemDao(): ListItemDao
    abstract fun typeItemDao(): TypeItemDao

    companion object{
        @Volatile
        private var Instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase{
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,PokemonDatabase::class.java, "pokedex")
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }

}