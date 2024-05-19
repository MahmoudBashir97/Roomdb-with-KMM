package com.mahmoudbashir.setuproomdb2.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.UserDatabase
import kotlinx.coroutines.Dispatchers

fun getUserDatabase(context: Context): RoomDatabase.Builder<UserDatabase> {
    val dbFile = context.getDatabasePath("user.db")

    return Room.databaseBuilder<UserDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    ).fallbackToDestructiveMigration(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}