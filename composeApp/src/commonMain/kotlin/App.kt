import Model.User
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.RoomDatabase
import database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<UserDatabase>) {
    // Building the database
    val database = remember { databaseBuilder.build() }



    MaterialTheme {
        MainContent(database)
        /*var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }*/
    }
}

@Composable
fun MainContent(database: UserDatabase) {
    val scope = rememberCoroutineScope()
    // Creating UserDao instance and passing in other screens
    val userDao = remember { database.userDao() }

    val userList = remember { mutableStateListOf<User>() }
    val list :MutableList<User> = ArrayList()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            userList.addAll(userDao.getAllUsers())
        }
    }

    ScreenContent(userList) {
        userList.clear()
        scope.launch(Dispatchers.IO) {
           userDao.insert(
                User(
                    name = "Mahmoud ${Random.nextInt()}",
                )
            )
           userList.addAll(userDao.getAllUsers())
        }
    }
}

@Preview
@Composable
fun ScreenContent(usersList: MutableList<User>, onAdd: (() -> Unit)? = null) {
    usersList
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
        LazyColumn(modifier = Modifier.fillMaxSize()){
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

val fakeList = listOf(
    User(name="Mahmoud"),
    User(name="Mahmoud"),
    User(name="Mahmoud"),
    User(name="Mahmoud"),
)