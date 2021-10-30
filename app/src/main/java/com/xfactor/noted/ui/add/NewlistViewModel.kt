package com.xfactor.noted.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xfactor.noted.database.ListItem
import com.xfactor.noted.database.ListItemRepository
import com.xfactor.noted.database.ListRepository
import com.xfactor.noted.database.ListWithListItems


class NewlistViewModel : ViewModel() {

    private val dataRepo = ListRepository()

    private val listItemRepo = ListItemRepository()

    private val lastId = if(dataRepo.getAll().isNotEmpty())  dataRepo.getAll().last().list.uid else 0

    private var lastElementId = if (listItemRepo.getAll().isNotEmpty()) listItemRepo.getAll().last().uid else 0

    private  var indexInList = 0

    private var _listItem = MutableLiveData<ListWithListItems>().apply {
        value = ListWithListItems(com.xfactor.noted.database.List(lastId + 1, "Example title"), mutableListOf())
    }
    var listItem: LiveData<ListWithListItems> = _listItem
    fun setTitle(title:String) {
        _listItem.postValue(ListWithListItems(com.xfactor.noted.database.List(_listItem.value!!.list.uid, title), _listItem.value!!.listItems))
    }
    fun addItem(item:String){
        val currentVal = _listItem.value ?: return
        val elements = currentVal.listItems
        lastElementId += 1
        _listItem.postValue(ListWithListItems(com.xfactor.noted.database.List(currentVal.list.uid, currentVal.list.title), elements.plus(ListItem(
            lastElementId + 1, currentVal.list.uid, item , indexInList))))
        indexInList +=1
        Log.e("list uid", currentVal.list.uid.toString())
        Log.e("list element uid", (listItemRepo.getAll().size + 1).toString())
    }


    fun commit() {
        dataRepo.insert(listItem.value!!)
    }
}