package di

import viewModel.AppViewModel
import viewModel.PostsViewModel
import database.UserDao
import database.UserDatabase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.local.UserRepository
import repository.network.NetworkRepository

val screenModels = module {
    factory { AppViewModel(get()) }
    factory { PostsViewModel(get()) }
}

val provideHttpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}

fun createAppModule(database: UserDatabase): Module {
    return module {
        single<UserDao> {
            val db = database
            db.userDao()
        }

        single {
            UserRepository(get())
        }

        single {
            NetworkRepository(get())
        }
    }
}

fun initKoin(database: UserDatabase) {
    startKoin {
        modules(
            provideHttpClientModule,
            createAppModule(database),
            screenModels
        )
    }
}