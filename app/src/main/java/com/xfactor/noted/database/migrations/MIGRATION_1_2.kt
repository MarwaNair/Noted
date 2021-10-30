package com.xfactor.noted.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MIGRATION_1_2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE 'listItem' ('uid' INTEGER NOT NULL, 'value' TEXT NOT NULL, 'listId' INTEGER NOT NULL , PRIMARY KEY ('uid')) ")
    }

}