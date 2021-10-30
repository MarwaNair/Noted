package com.xfactor.noted.database

import androidx.room.*
import kotlin.collections.List


@Dao
interface ListDao {

    @Query("SELECT * FROM list")
    fun getAll () : List<com.xfactor.noted.database.List>

    @Query("SELECT * FROM list WHERE title LIKE '%Favorite%'")
    fun getAllFavorites () : List<ListWithListItems>

    @Query("SELECT * FROM list WHERE title LIKE :query")
    fun searchLists(query: String): List<ListWithListItems>

    @Transaction
    @Query("SELECT * FROM list")
    fun getListWithListItems() : List<ListWithListItems>

    @Insert
    fun insertAll( vararg lists : com.xfactor.noted.database.List)

    @Delete
    fun delete(list : com.xfactor.noted.database.List)

}