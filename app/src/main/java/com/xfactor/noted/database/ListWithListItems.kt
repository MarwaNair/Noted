package com.xfactor.noted.database

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithListItems (
    @Embedded val list: List,
    @Relation (
            parentColumn =  "uid",
            entityColumn = "listId")
    val listItems : kotlin.collections.List<ListItem>
        )

fun getSubItems(item: ListWithListItems):String {
    val inListForm = item.listItems.mapIndexed {idx, value -> (idx+1).toString().plus(". ").plus(value.value)}
    return inListForm.joinToString("\n")
}
