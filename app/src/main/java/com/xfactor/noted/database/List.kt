package com.xfactor.noted.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xfactor.noted.database.ListItem


@Entity
data class List (
        @PrimaryKey val uid :   Int,
        @ColumnInfo (name = "title") val title: String?
       // @ColumnInfo (name="elements") val elements : MutableList<ListItem>
        )