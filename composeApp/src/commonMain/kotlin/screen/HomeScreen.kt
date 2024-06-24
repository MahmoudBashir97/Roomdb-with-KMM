package screen

import Model.User
import ViewModel.AppViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.abs
import kotlin.random.Random

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AppViewModel = getScreenModel()

        MainHomeContent(viewModel)
    }
}

@Composable
fun MainHomeContent(viewModel: AppViewModel) {
    val scope = rememberCoroutineScope()

    val userList = remember { viewModel.usersList }

    viewModel.usersListMD.collectAsState().value.apply {
        userList.clear()
        userList.addAll(this)
    }

    ScreenContent(viewModel.usersListMD.collectAsState().value.toMutableList()) {
        scope.launch(Dispatchers.IO) {
            viewModel.insertUser(
                User(
                    name = "Mahmoud ${abs(Random.nextInt())}",
                )
            )
        }
    }
}


@Preview
@Composable
fun ScreenContent(usersList: MutableList<User>, onAdd: (() -> Unit)? = null) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.Black,
                onClick = {
                    onAdd?.invoke()
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add button",
                    tint = Color.White
                )
            }
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(usersList) {
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(30.dp)
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        it.name, style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}