package com.xfactor.noted.ui.listcontainer

import androidx.lifecycle.ViewModel
import com.xfactor.noted.database.ListRepository

class ListContainerViewModel : ViewModel() {
    val dataRepo = ListRepository()
}