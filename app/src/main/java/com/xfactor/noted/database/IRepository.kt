package com.xfactor.noted.database

import kotlin.collections.List

interface IRepository<T> {

    fun getAll () : kotlin.collections.List<T>
    fun insert (value : T)
    fun remove(index : Int)
    fun query(query : String) : List<T>
}