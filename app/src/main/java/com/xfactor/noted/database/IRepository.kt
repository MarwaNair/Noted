package com.xfactor.noted.database

interface IRepository {

    fun getAll () : List
    fun insert (value : Any)
    fun remove(index : Int)
    fun query(query : String) : List
}