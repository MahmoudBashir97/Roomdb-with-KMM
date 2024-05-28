package di

import ViewModel.AppViewModel
import database.UserDao
import database.UserDatabase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.UserRepository
import repository.UserRepositoryImp

val appModule = module {
    single<UserDao> {
        val db = get<UserDatabase>()
        db.userDao()
    }

    single {
        UserRepository(get())
    }
}

val screenModels = module {
    factory { AppViewModel(get()) }
}

fun createAppModule(database: UserDatabase): Module{
    return module {
        single<UserDao> {
            val db = database
            db.userDao()
        }

        single {
            UserRepository(get())
        }
    }
}

fun initKoin(database: UserDatabase) {
    startKoin {
        modules(
            createAppModule(database),
            screenModels
        )
    }
}