package screen

import model.Post
import viewModel.PostsViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class PostsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<PostsViewModel>()
        val postsList = remember {
            mutableListOf<Post>()
        }
        viewModel.postsFlow.collectAsState().value
            .let {
                postsList.addAll(it)
            }

        PostsScreenContent(postsList)
    }
}

@Composable
fun PostsScreenContent(postsList: MutableList<Post>) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(postsList) {
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        it.title, style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}
