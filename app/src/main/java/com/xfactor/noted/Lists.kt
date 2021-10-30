package com.xfactor.noted

import com.xfactor.noted.database.ListItem
import com.xfactor.noted.database.ListWithListItems


val ListsToCompare = mutableListOf<ListWithListItems>()

fun getLists() : List<ListWithListItems> {
    return appDatabase.listDao().getListWithListItems()
}

fun getListItems(): List<ListItem> {
    return appDatabase.listItemDao().getAll()
}

fun getFavoriteLists() : List<ListWithListItems> {
    return appDatabase.listDao().getAllFavorites()
}

fun searchLists (query: String): List<ListWithListItems> {
   return appDatabase.listDao().searchLists("%".plus(query).plus("%"))
}
fun addList(list: ListWithListItems) {
    appDatabase.listDao().insertAll(list.list)
    appDatabase.listItemDao().insertAll(*list.listItems.toTypedArray())
}

fun deleteList(list: ListWithListItems) {
    appDatabase.listDao().delete(list.list)
}

fun getSubItems(item: ListWithListItems):String {
    val inListForm = item.listItems.mapIndexed {idx, value -> (idx+1).toString().plus(". ").plus(value.value)}
    return inListForm.joinToString("\n")
}