package repository.local

import model.User
import database.UserDao

interface UserRepositoryImp {
    suspend fun insertUser(user: User): Long
    suspend fun getAllUsers(): List<User>
}

class UserRepository(private val userDao: UserDao) : UserRepositoryImp {
    override suspend fun insertUser(user: User): Long = userDao.insert(user)

    override suspend fun getAllUsers(): List<User> = userDao.getAllUsers()

}