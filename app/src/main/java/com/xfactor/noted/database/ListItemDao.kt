package com.xfactor.noted.database


import androidx.room.*
import kotlin.collections.List


@Dao
interface ListItemDao {

    @Query("SELECT * FROM listItem")
    fun getAll () : List<com.xfactor.noted.database.ListItem>


    @Insert
    fun insertAll( vararg listItems:  com.xfactor.noted.database.ListItem)

    @Delete
    fun delete(listItem : com.xfactor.noted.database.ListItem)

    @Update
    fun update(listitem: ListItem)
}