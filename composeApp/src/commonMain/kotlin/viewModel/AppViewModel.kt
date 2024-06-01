package viewModel

import model.User
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import repository.local.UserRepository

class AppViewModel(private val userRepo: UserRepository) : ScreenModel {
    val usersList = mutableListOf<User>()
    val usersListMD = MutableStateFlow(emptyList<User>())

    init{
        getUsers()
    }

    fun insertUser(user: User) {
        screenModelScope.launch {
            userRepo.insertUser(user)
            getUsers()
        }
    }

    private fun getUsers() = screenModelScope.launch {
        userRepo.getAllUsers().let { users ->
            if (users.isNotEmpty()) usersList.addAll(users)
            usersListMD.value = users
        }
    }
}