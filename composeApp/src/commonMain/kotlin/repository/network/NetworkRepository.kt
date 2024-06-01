package repository.network

import model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

interface NetworkImplementation{
    fun getPostsList(): Flow<NetworkResult<List<Post>>>
}

class NetworkRepository(private val httpClient: HttpClient):NetworkImplementation {

    override fun getPostsList(): Flow<NetworkResult<List<Post>>> {
        return toResultFlow {
            val response =
                httpClient.get("https://jsonplaceholder.typicode.com/posts").body<List<Post>>()
            NetworkResult.Success(response)
        }
    }
}


