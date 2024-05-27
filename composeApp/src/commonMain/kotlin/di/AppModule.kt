package di

import database.UserDao
import database.UserDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.module
import repository.UserRepository
import repository.UserRepositoryImp

val appModule = module {
//    single {
//        getUserDatabase()
//    }

    single<UserDao> {
        val db = get<UserDatabase>()
        db.userDao()
    }

    single<UserRepositoryImp> {
        UserRepository(get())
    }
}

fun initKoin() {
    startKoin {
        modules(
            appModule
        )
    }
}