package database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun getUserDatabase(): RoomDatabase.Builder<UserDatabase> {
    val dbFile = NSHomeDirectory() + "/user.db"
    return Room.databaseBuilder<UserDatabase>(
        name = dbFile,
        factory = { UserDatabase::class.instantiateImpl() } // This too will show error
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}