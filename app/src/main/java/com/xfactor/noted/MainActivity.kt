package com.xfactor.noted

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xfactor.noted.database.AppDatabase
import com.xfactor.noted.database.migrations.MIGRATION_1_2
import com.xfactor.noted.database.migrations.MIGRATION_2_3


lateinit var appDatabase: AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "noted-database").allowMainThreadQueries().addMigrations(
            MIGRATION_1_2 , MIGRATION_2_3).build()

        // Setting ActionBar logo
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.ic_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        setContentView(R.layout.activity_main)

        // Setup navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_delete, R.id.navigation_listcontainer, R.id.navigation_newlist))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val listDao = appDatabase.listDao()
        val listItemDao = appDatabase.listItemDao()

    /**  listDao.insertAll(com.xfactor.noted.database.List(1,"Favorite Restaurants"))
        listItemDao.insertAll(ListItem(1,1,"Le Sultan",1))
        listItemDao.insertAll(ListItem(2,1,"HotSpot",2))
        listItemDao.insertAll(ListItem(3,1,"FitCoffee",3))

        listDao.insertAll(com.xfactor.noted.database.List(2,"Favorite Songs"))
        listItemDao.insertAll(ListItem(4, 2,"Easy on me",1))
        listItemDao.insertAll(ListItem(5,2,"Une souris verte",2))
        listItemDao.insertAll(ListItem(6,2,"Claire de la lune",3))

        listDao.insertAll(com.xfactor.noted.database.List(3,"Names"))
        listItemDao.insertAll(ListItem(7, 3,"Angela Yu",1))
        listItemDao.insertAll(ListItem(8,3,"Nair Marwa",2))
        listItemDao.insertAll(ListItem(9,3,"Jim Wilson",3)) */






    }
}




