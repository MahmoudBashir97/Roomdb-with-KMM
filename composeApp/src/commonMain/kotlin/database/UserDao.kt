package database

import model.User
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>
}
