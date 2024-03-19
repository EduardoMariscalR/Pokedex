package com.eduardo.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eduardo.pokedex.data.local.model.ListItem

@Dao
interface ListItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allListItem: ListItem)

    @Update
    suspend fun update(allListItem: ListItem)

    @Delete
    suspend fun delete(allListItem: ListItem)

    @Query("SELECT * FROM api_resource_list")
    fun getAllList(): List<ListItem>

    @Query("SELECT * FROM api_resource_list WHERE category = :category")
    fun getListSortByCategory(category: String): List<ListItem>

}