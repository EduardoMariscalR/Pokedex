package com.eduardo.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eduardo.pokedex.data.local.model.TypeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeItemDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(typeItem: TypeItem)

    @Update
    suspend fun update(typeItem: TypeItem)

    @Delete
    suspend fun delete(typeItem: TypeItem)

    @Query("SELECT * FROM type_items WHERE id = :id")
    fun getTypeItem(id: Int): Flow<TypeItem>

    @Query("SELECT * FROM type_items ORDER BY id ASC")
    suspend fun getAllTypeItems(): List<TypeItem>

}
