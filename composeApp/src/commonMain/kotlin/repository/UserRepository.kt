package repository

import Model.User
import database.UserDao

interface UserRepositoryImp {
    suspend fun getIsUsers(): List<User>
}

class UserRepository(private val userDao: UserDao) : UserRepositoryImp {
    override suspend fun getIsUsers(): List<User> {

        return userDao.getAllUsers()
    }
}