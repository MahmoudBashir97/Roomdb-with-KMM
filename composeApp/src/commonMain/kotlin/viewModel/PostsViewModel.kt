package viewModel

import model.Post
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import repository.network.ApiStatus
import repository.network.NetworkRepository

class PostsViewModel(private val repo: NetworkRepository) : ScreenModel {

//    private val _homeState = MutableStateFlow(HomeState())
//    private val _homeViewState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState.Loading)
//    val homeViewState = _homeViewState.asStateFlow()

    val postsFlow = MutableStateFlow(emptyList<Post>())

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        screenModelScope.launch(Dispatchers.IO) {
            repo.getPostsList().collect { response ->
                when(response.status){
                    ApiStatus.LOADING -> {}
                    ApiStatus.ERROR -> {}
                    ApiStatus.SUCCESS -> {
                        response.data?.let { posts -> postsFlow.value = posts }
                    }
                }

            }
        }
    }
}

//sealed class HomeScreenState {
//    data object Loading: HomeScreenState()
//    data class Error(val errorMessage: String):HomeScreenState()
//    data class Success(val responseData: ApiResponse):HomeScreenState()
//}
//private data class HomeState(
//    val isLoading:Boolean = false,
//    val errorMessage: String?=null,
//    val responseData: ApiResponse?=null
//) {
//    fun toUiState(): HomeScreenState {
//        return if (isLoading) {
//            HomeScreenState.Loading
//        } else if(errorMessage?.isNotEmpty()==true) {
//            HomeScreenState.Error(errorMessage)
//        } else {
//            HomeScreenState.Success(responseData!!)
//        }
//    }
//}